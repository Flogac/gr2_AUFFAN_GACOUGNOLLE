//ce fichier teste bd.UsersTools.userExists et bd.DatabaseServices.exists: TERMINE
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bd.DatabaseServices;
import bd.UsersTools;

public class UserExistsTest{
	@Test
	public void test(){
		List<String> colonnes=new ArrayList<String>();
		List<String> valeurs=new ArrayList<String>();
		colonnes.add("login");
		valeurs.add("1234");
		try{
			DatabaseServices.insert("Users",colonnes,valeurs);
		}
		catch(Exception e){}
		try{
			boolean res=UsersTools.userExists("1234");
			assertEquals(res,true);
		}
		catch(Exception e){
			fail();
		}
	}
}
