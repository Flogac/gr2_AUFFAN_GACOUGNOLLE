package bd;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsersTools{

	public static boolean newUser(String login, String mdp, String prenom, String nom) throws SQLException{
		System.out.println("Entree dans newUser");
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("login");
		colonnes.add("prenom");
		colonnes.add("nom");
		colonnes.add("Password");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(login);
		valeurs.add(prenom);
		valeurs.add(nom);
		valeurs.add(mdp);
		System.out.println( "Valeurs et colonnes rentrées.");
		if(DatabaseServices.insert("Users", colonnes, valeurs) > 0)
			return true;
		return false;
		
	}
	/*public static boolean userExists(String login){
		Connection conn=Database.getMySQLConnection();
		String query="select id from Users where login="+login;
		Statement st=conn.createStatement();
	}*/
}