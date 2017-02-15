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
		colonnes.add("id");
		int encode = Integer.parseInt(login);
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(login);
		valeurs.add(prenom);
		valeurs.add(nom);
		valeurs.add(mdp);
		valeurs.add(""+encode);
		
		if(DatabaseServices.insert("users", colonnes, valeurs) > 0)
			return true;
		return false;
		
	}
	/*public static boolean userExists(String login){
		Connection conn=Database.getMySQLConnection();
		String query="select id from Users where login="+login;
		Statement st=conn.createStatement();
	}*/
}