package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.Statement;

public class DatabaseServices{
	private DataSource dataSource;
	
	public DatabaseServices(String jndiname) throws SQLException{
		try{
			dataSource=(DataSource)new InitialContext().lookup("java:comp/env/"+jndiname);
		}
		catch(NamingException e){
			throw new SQLException(jndiname+" is missing in JNDI! : "+e.getMessage());
		}
	}
	
	public Connection getConnection() throws SQLException{
		return (Connection) DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + ":" + DBStatic.mysql_port + "/" + DBStatic.mysql_db, DBStatic.mysql_username, DBStatic.mysql_password);
	}
	public static Connection getMySQLConnection() throws SQLException{
		if(DBStatic.mysql_pooling==false){
			return(DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password));
		}
		return null;
	}

	public static int insert(String table, ArrayList<String> colonnes , ArrayList<String> valeurs ) throws SQLException {
		Connection connection = getMySQLConnection();
		String requete = "INSERT INTO " + table + " \n(";
		for (String i : colonnes) requete += i + ",";
		requete = requete.substring(0, requete.length()-1) + ") ";
		requete+= "VALUES (";
		for (String i : valeurs) requete+= "'" + i + "',";
		requete = requete.substring(0, requete.length()-1) + ");";
		Statement etat = (Statement) connection.createStatement();
		return etat.executeUpdate(requete);
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

	public static void drop(String table, ArrayList<String> colonnes, ArrayList<String> valeurs) throws SQLException {
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
	
}