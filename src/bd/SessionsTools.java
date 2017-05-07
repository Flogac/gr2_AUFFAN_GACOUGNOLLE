package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SessionsTools{

	public static void error(String message,int num){
		System.err.println("erreur numero "+num+" "+message);
	}
	//elle permet de s'assurer que le mdp fourni est le bon
	public static boolean checkPassword(String login, String mdp)throws SQLException,InstantiationException,IllegalAccessException,ClassNotFoundException{
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("login");
		colonnes.add("password");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(login);
		valeurs.add(mdp);
		return DatabaseServices.exists("Users",colonnes,valeurs) ;
	}

	public static int getIdUser(String login)throws SQLException,InstantiationException,IllegalAccessException,ClassNotFoundException{
		System.out.println("entree de bd.SessionsTools.getIdUser");
		List<String> colonnes=new ArrayList<String>();
		colonnes.add("login");
		List<String> valeurs=new ArrayList<String>();
		valeurs.add(login);
		List<String> select=new ArrayList<String>();
		select.add("id");
		ResultSet res=DatabaseServices.select("Users",select,colonnes,valeurs);
		res.next();
		return res.getInt("id");
	}
	public static String insertSession(int id,boolean b)throws SQLException,InstantiationException,IllegalAccessException,ClassNotFoundException, SessionDejaExistanteException{
		String key=UUID.randomUUID().toString().replace("-","");
		if(!checkSession(id)){
			List<String> colonnes=new ArrayList<String>();
			List<String> valeurs=new ArrayList<String>();
			colonnes.add("id");
			colonnes.add("cle");
			colonnes.add("root");
			valeurs.add(""+id);
			valeurs.add(key);
			if(b)
				valeurs.add("1");
			else
				valeurs.add("0");
			DatabaseServices.insert("Sessions",colonnes,valeurs);
			return key;
		}
		throw new SessionDejaExistanteException();
	}
	public static boolean checkSession(int id)throws SQLException,InstantiationException,IllegalAccessException,ClassNotFoundException{
		List<String> colonnes=new ArrayList<String>();
		List<String> valeurs=new ArrayList<String>();
		colonnes.add("id");
		valeurs.add(""+id);
		return DatabaseServices.exists("Sessions",colonnes,valeurs);
	}
	public static String getKey(String login)throws IdNotFoundException,SessionDejaExistanteException,SQLException,InstantiationException,IllegalAccessException,ClassNotFoundException{
		System.out.println("entree de bd.SessionsTools.getKey");
		int id=getIdUser(login);
		List<String> colonnes=new ArrayList<String>();
		List<String> valeurs=new ArrayList<String>();
		List<String> select=new ArrayList<String>();
		colonnes.add("id");
		valeurs.add(""+id);
		select.add("cle");
		ResultSet res=DatabaseServices.select("Sessions",select,colonnes,valeurs);
		if(res.next()==false)
			throw new SessionDejaExistanteException();
		return res.getString("id");
	}
	public static void supprSession(int id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<String> colonnes=new ArrayList<String>();
		List<String> valeurs=new ArrayList<String>();
		colonnes.add("id");
		valeurs.add(""+id);
		DatabaseServices.delete("Sessions",colonnes,valeurs);
	} 
}
