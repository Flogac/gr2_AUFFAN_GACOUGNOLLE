//ce fichier permet de tester bd.DatabaseServices.delete: TERMINE.
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bd.DatabaseServices;

public class DeleteTest{
	@Test
	public void test(){
		List<String> colonnes=new ArrayList<String>();
		List<String> valeurs=new ArrayList<String>();
		colonnes.add("login");
		valeurs.add("darth vader");
		try{
			DatabaseServices.insert("Users",colonnes,valeurs);
		}
		catch(Exception e){}
		try{
			assertEquals(DatabaseServices.delete("Users",colonnes,valeurs),true);
			assertTrue(true);
		}
		catch(Exception e){}
	}
}