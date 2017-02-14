package bd;

import java.sql.Connection;
import java.sql.Statement;

public class UsersTools{

	public static boolean newUser(String login, String mdp, String prenom, String nom) {
		return false;
		// TODO Auto-generated method stub
		
	}
	/*public static boolean userExists(String login){
		Connection conn=Database.getMySQLConnection();
		String query="select id from Users where login="+login;
		Statement st=conn.createStatement();
	}*/
}