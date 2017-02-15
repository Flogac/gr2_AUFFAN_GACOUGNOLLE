package bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersTools{

	public static boolean newUser(String login, String mdp, String prenom, String nom) throws SQLException{
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("login");
		colonnes.add("prenom");
		colonnes.add("nom");
		colonnes.add("Password");
		ArrayList<String> v = new ArrayList<String>();
		v.add(login);
		v.add(prenom);
		v.add(nom);
		v.add(mdp);
		
		if(DatabaseServices.insert("users", colonnes, v) > 0)
			return true;
		return false;
		
	}
	/*public static boolean userExists(String login){
		Connection conn=Database.getMySQLConnection();
		String query="select id from Users where login="+login;
		Statement st=conn.createStatement();
	}*/
}