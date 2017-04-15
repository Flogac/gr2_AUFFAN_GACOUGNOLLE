package test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import bd.DatabaseServices;
import bd.SessionsTools;

//FONCTIONNE!!!!!
public class TestInsertionMysqlEtUserExists{
	public static void main(String[] args) throws SQLException{
		System.out.println("entree de main dans test.TestInsertionMysqlEtUserExists.java");
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("login");
		colonnes.add("prenom");
		colonnes.add("nom");
		colonnes.add("Password");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add("Benlog");
		valeurs.add("Benjamin");
		valeurs.add("Loglisci");
		valeurs.add("Benlog8");
		DatabaseServices.insert("Users",colonnes,valeurs);
		valeurs=new ArrayList<String>();
		valeurs.add("5678");
		DatabaseServices.insert("Users",colonnes,valeurs);
		colonnes=new ArrayList<String>();
		valeurs=new ArrayList<String>();
		colonnes.add("de");
		colonnes.add("vers");
		colonnes.add("timestamp");
		valeurs.add("3");
		valeurs.add("4");
		//permet d'ajouter un timestamp compatible avec mysql
		valeurs.add(new Timestamp(System.currentTimeMillis()).toString());
		//DatabaseServices.insert("",new ArrayList<String>(),new ArrayList<String>());
		DatabaseServices.insert("Friends",colonnes,valeurs);
		System.out.println(SessionsTools.userExists("Benlog"));
		System.out.println("sortie de main dans test.TestInsertionMysqlEtUserExists.java");
	}
}