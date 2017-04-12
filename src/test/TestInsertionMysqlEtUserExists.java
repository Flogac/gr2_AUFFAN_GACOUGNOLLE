package test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bd.DatabaseServices;
import bd.SessionsTools;

//FONCTIONNE!!!!!
public class TestInsertionMysqlEtUserExists{
	public static void main(String[] args) throws SQLException{
		System.out.println("entree de main dans test.TestInsertionMysqlEtUserExists.java");
		List<String> colonnes=new ArrayList<String>();
		List<String> valeurs=new ArrayList<String>();
		colonnes.add("login");
		valeurs.add("1234");
		DatabaseServices.insert("Users",colonnes,valeurs);
		valeurs=new ArrayList<String>();
		valeurs.add("5678");
		DatabaseServices.insert("Users",colonnes,valeurs);
		colonnes=new ArrayList<String>();
		valeurs=new ArrayList<String>();
		colonnes.add("de");
		colonnes.add("vers");
		colonnes.add("timestamp");
		valeurs.add("1234");
		valeurs.add("5678");
		//permet d'ajouter un timestamp compatible avec mysql
		valeurs.add(new Timestamp(System.currentTimeMillis()).toString());
		//DatabaseServices.insert("",new ArrayList<String>(),new ArrayList<String>());
		DatabaseServices.insert("Friends",colonnes,valeurs);
		System.out.println(SessionsTools.userExists("1234"));
		System.out.println("sortie de main dans test.TestInsertionMysqlEtUserExists.java");
	}
}