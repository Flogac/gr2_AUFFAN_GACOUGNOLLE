package test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bd.DatabaseServices;

public class TestInsertionMysqlEtUserExists{
	public static void main(String[] args){
		String req="Friends";
		List<String> colonnes=new ArrayList<String>();
		List<String> valeurs=new ArrayList<String>();
		colonnes.add("de");
		colonnes.add("vers");
		colonnes.add("timestamp");
		valeurs.add("1234");
		valeurs.add("5678");
		//permet d'ajouter un timestamp compatible avec mysql
		valeurs.add(new Timestamp(System.currentTimeMillis()).toString());
		//DatabaseServices.insert("",new ArrayList<String>(),new ArrayList<String>());
		DatabaseServices.insert(req,colonnes,valeurs);
		System.out.println("tout va bien");
	}
}