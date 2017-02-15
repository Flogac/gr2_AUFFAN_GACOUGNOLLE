package bd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class SessionsTools{
	
	public static void error(String message,int num){
		System.err.println("erreur numero "+num+" "+message);
	}
	public static boolean userExists(String login) throws SQLException {
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("login");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(login);
		
		return DatabaseServices.exists("users", colonnes, valeurs) ;
	}

	public static boolean checkPassword(String login, String mdp) throws SQLException {
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("login");
		colonnes.add("password");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(login);
		valeurs.add( mdp );
		
		return DatabaseServices.exists("Users", colonnes, valeurs) ;
	}

	public static int getIdUser(String login) throws SQLException {
		return Integer.parseInt(login);
	}

	public static String insertSession(int id, boolean b) throws SQLException {
		Random rand = new Random();
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("id");
		colonnes.add("key");
		String key = "" + id +rand.nextInt() ;
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(""+id);
		valeurs.add( key );
		DatabaseServices.insert( "Session" , colonnes , valeurs );
		return key;
	}
	public static boolean checkSession(String login, String key) throws SQLException {
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("login");
		colonnes.add("key");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(login);
		valeurs.add( key );
		
		return DatabaseServices.exists("Session", colonnes, valeurs) ;
	}
	public static void supprSession(int id, String key) throws SQLException {
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("id");
		colonnes.add("key");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(""+id);
		valeurs.add( key );
		DatabaseServices.drop( "Session" , colonnes , valeurs );
		
	} 
}