package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SessionsTools{
	
	public static void error(String message,int num){
		System.err.println("erreur numero "+num+" "+message);
	}
	public static boolean userExists(String login) throws SQLException {
		System.out.println("entree dans userExists de bd.SessionsTools");
		List<String> colonnes = new ArrayList<String>();
		colonnes.add("login");
		List<String> valeurs = new ArrayList<String>();
		valeurs.add(login);
		System.out.println("sortie de userExists de bd.SessionsTools");
		return DatabaseServices.exists("Users", colonnes, valeurs) ;
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
	
	public static int getIdUser(String login) throws SQLException,IdNotFoundException{
		System.out.println("entree de bd.SessionsTools.getIdUser");
		List<String> colonnes=new ArrayList<String>();
		colonnes.add("login");
		List<String> valeurs=new ArrayList<String>();
		valeurs.add(login);
		List<String> select=new ArrayList<String>();
		select.add("id");
		ResultSet res=DatabaseServices.select("Users",select,colonnes,valeurs);
		if(res==null||res.next()==false)
			throw new IdNotFoundException();
		System.out.println("sortie de bd.SessionsTools.getIdUser");
		return res.getInt("id");
	}

	public static String insertSession(int id, boolean b) throws SQLException {
		Random rand = new Random();
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("id");
		colonnes.add("cle");
		colonnes.add("root");
		String key = "" + id +rand.nextInt() ;
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(""+id);
		valeurs.add( key );
		valeurs.add("0");
		DatabaseServices.insert( "Sessions" , colonnes , valeurs );
		return key;
	}
	public static boolean checkSession(int id, String key) throws SQLException {
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("id");
		colonnes.add("cle");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add("" + id);
		valeurs.add( key );
		
		return DatabaseServices.exists("Sessions", colonnes, valeurs) ;
	}
	public static void supprSession(int id, String key) throws SQLException {
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("id");
		colonnes.add("cle");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(""+id);
		valeurs.add( key );
		DatabaseServices.drop( "Sessions" , colonnes , valeurs );
		
	} 
}
