package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

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
		System.out.println("entree de getMySQLConnection de bd.DatabaseServices");
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("sortie probleme de getMySQLConnection de bd.DatabaseServices");
			return null;
		}
		System.out.println("sortie ok de getMySQLConnection de bd.DatabaseServices");
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
			for(String i:valeurs)requete+="'"+i+"',";
			requete=requete.substring(0,requete.length()-1)+")";
			Statement etat=(Statement)connection.createStatement();
			res=etat.executeUpdate(requete);
			connection.close();
		}
		catch(Exception e){}
		return res;
	}

	public static boolean exists(String table, ArrayList<String> colonnes, ArrayList<String> valeurs) throws SQLException {
		Connection connection = getMySQLConnection();
		Statement etat = (Statement) connection.createStatement();
		String requete = "SELECT * FROM" + table ;
		for( int i = 0 ; i < min( colonnes.size() , valeurs.size() ) ; i++ )
			requete += "WHERE"  + colonnes.get(i) + " = " + valeurs.get(i); 
		ResultSet resultat = etat.executeQuery(requete);
		if( resultat.next() ) return true;
		return false;
	}

	private static int min(int size, int size2) {
		if( size > size2) return size2;
		return size;
	}

	public static void drop(String table, List<String> colonnes, List<String> valeurs) throws SQLException {
		Connection connection = getMySQLConnection();
		Statement etat = (Statement) connection.createStatement();
		String requete = "DELETE FROM" + table + "WHERE";
		for( int i = 0 ; i < min( colonnes.size() , valeurs.size() ) ; i++ ){
			requete += colonnes.get(i) + " = " + valeurs.get(i); 
			if( i != min( colonnes.size() , valeurs.size() ) - 1 ) requete += " AND ";
		}
		etat.executeQuery(requete);
	}

	public static ResultSet select(String table, ArrayList<String> select, ArrayList<String> colonnes,
			ArrayList<String> valeurs) throws SQLException {
		Connection connection = getMySQLConnection();
		Statement etat = (Statement) connection.createStatement();
		String requete = "SELECT ";
		if( select.size() == 0 ) requete +="* ";
		for( int i = 0 ; i < select.size() ; i++ ){
			requete += select.get(i);
			if( i != select.size() - 1 ) requete += ",";
		}
		requete += "FROM " + table + " ";
		for( int i = 0 ; i < min( colonnes.size() , valeurs.size() ) ; i++ ){
			requete += colonnes.get(i) + " = " + valeurs.get(i); 
			if( i != min( colonnes.size() , valeurs.size() ) - 1 ) requete += " AND ";
		}
		return etat.executeQuery(requete);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
