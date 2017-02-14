package bd;

import java.sql.SQLException;

public class SessionsTools{

	public static void error(String message,int num){
		System.err.println("erreur numero "+num+" "+message);
	}
	public static boolean userExists(String login) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean checkPassword(String login, String mdp) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public static int getIdUser(String login) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public static String insertSession(int id, boolean b) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	public static boolean checkSession(String login, String key) {
		// TODO Auto-generated method stub
		return false;
	}
	public static void supprSession(int id, String key2) {
		// TODO Auto-generated method stub
		
	} 
}