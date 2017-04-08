package test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bd.DatabaseServices;

public class TestSuppressionMysql{
	public static void main(String[] args){
		String req="Friends";
		List<String> colonnes=new ArrayList<String>();
		List<String> valeurs=new ArrayList<String>();
		colonnes.add("de");
		colonnes.add("vers");
		colonnes.add("timestamp");
		valeurs.add("1234");
		valeurs.add("5678");
		valeurs.add(new Timestamp(System.currentTimeMillis()).toString());
		try{
			DatabaseServices.drop(req,colonnes,valeurs);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}