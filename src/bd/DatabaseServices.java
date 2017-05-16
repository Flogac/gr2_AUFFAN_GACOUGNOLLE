package bd;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mysql.jdbc.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DatabaseServices{
	private DataSource dataSource;
	
	public DatabaseServices(String jndiname) throws SQLException{
		try{
			this.setDataSource((DataSource)new InitialContext().lookup("java:comp/env/"+jndiname));
			}
		catch(NamingException e){
			throw new SQLException(jndiname+" is missing in JNDI! : " + e.getMessage());
		}
	}
	
	public Connection getConnection() throws SQLException{
		return (Connection) DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":" + DBStatic.mysql_port + "/" + DBStatic.mysql_db, DBStatic.mysql_username, DBStatic.mysql_password);
	}
	public static Connection getMySQLConnection() throws SQLException{
		//System.out.println("entree de getMySQLConnection de bd.DatabaseServices");
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception e){
			e.printStackTrace();
			//System.out.println("sortie probleme de getMySQLConnection de bd.DatabaseServices");
			return null;
		}
		//System.out.println("sortie ok de getMySQLConnection de bd.DatabaseServices");
		return(DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+":"+DBStatic.mysql_port+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password));
	}

	//TERMINEE
	public static int insert(String table,List<String> colonnes,List<String> valeurs){
		int res=0;
		try{
			Connection connection=getMySQLConnection();
			String requete="INSERT INTO "+table+"(";
			for(String i:colonnes)requete+=i+",";
			requete=requete.substring(0,requete.length()-1)+")";
			requete+= " VALUES (";
			for(String i:valeurs)requete+="\""+i+"\",";
			requete=requete.substring(0,requete.length()-1)+")";
			System.out.println( requete );
			Statement etat=(Statement)connection.createStatement();
			res=etat.executeUpdate(requete);
			connection.close();
		}
		catch(Exception e){}
		return res;
	}

	public static boolean exists(String table,List<String> colonnes,List<String> valeurs) throws SQLException{
		//System.out.println("entree de exists de bd.DatabaseServices");
		Connection connection = getMySQLConnection();
		java.sql.Statement etat =connection.createStatement();
		String requete = "SELECT * FROM " + table ;
		requete+=" WHERE "+colonnes.get(0)+" = \""+valeurs.get(0)+"\"";
		for( int i = 1 ; i < Math.min( colonnes.size() , valeurs.size() ) ; i++ )
			requete+=" AND "+colonnes.get(i)+" = \""+valeurs.get(i)+"\"";
		System.out.println(requete);
		ResultSet resultat = etat.executeQuery(requete);
		//System.out.println("sortie de exists de bd.DatabaseServices");
		if(resultat.next()) return true;
		return false;
	}
	public static void drop(String table, List<String> colonnes, List<String> valeurs) throws SQLException {
		Connection connection = getMySQLConnection();
		Statement etat = (Statement) connection.createStatement();
		String requete = "DELETE FROM " + table + " WHERE ";
		for( int i = 0 ; i < min( colonnes.size() , valeurs.size() ) ; i++ ){
			requete += colonnes.get(i) + " = \"" + valeurs.get(i)+"\""; 
			if( i != min( colonnes.size() , valeurs.size() ) - 1 ) requete += " AND ";
		}
		System.out.println(requete);
		etat.executeUpdate(requete);
	}

	public static ResultSet select(String table, ArrayList<String> select, ArrayList<String> colonnes,
			ArrayList<String> valeurs) throws SQLException {
		Connection connection = getMySQLConnection();
		Statement etat = (Statement) connection.createStatement();
		String requete = "SELECT ";
		if( select.size() == 0 ) requete +="* ";
		for( int i = 0 ; i < select.size() ; i++ ){
			requete += select.get(i) + " ";
			if( i != select.size() - 1 ) requete += ",";
		}
		requete += "FROM " + table + " WHERE ";
		for( int i = 0 ; i < min( colonnes.size() , valeurs.size() ) ; i++ ){
			requete += colonnes.get(i) + " = \"" + valeurs.get(i)+"\""; 
			if( i != min( colonnes.size() , valeurs.size() ) - 1 ) requete += " AND ";
		}
		System.out.println( requete);
		return etat.executeQuery(requete);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private static DB mongoCo() throws UnknownHostException{
		return new Mongo(DBStatic.mysql_host, DBStatic.mongoDB_port).getDB(DBStatic.mysql_db);
	}

	public static DBCollection getMongoCo(String table) throws UnknownHostException{
		return mongoCo().getCollection(table);
	}
	
	public static DBCursor find(String table, BasicDBObject o) throws UnknownHostException{
		return getMongoCo(table).find(o);
	}

	public static void add(String table, BasicDBObject o) throws UnknownHostException{
		getMongoCo(table).insert(o);
	}
	
	public static void dropMongo( String table, BasicDBObject o ) throws UnknownHostException{
		getMongoCo( table ).findAndRemove(o);
	}
}
