package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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

	public static int insert(String table, ArrayList<String> colonnes , ArrayList<String> valeurs ) {
		Connection connection = getMySQLConnection();
		String query = "INSERT INTO " + table + " ";

		query+= "(";	
		for (String i : colonnes) query += i + ",";
		query = query.substring(0, query.length()-1) + ") ";
		query+= "VALUES (";
		for (String i : valeurs) query+= "'" + i + "',";
		query = query.substring(0, query.length()-1) + ");";
		System.out.println(query);
		Statement st = co.createStatement();
		int r = st.executeUpdate(query);

		return r;
	}
}