//ce fichier teste bd.SessionsTools.checkPassword: TERMINE
package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bd.SessionsTools;
import bd.UsersTools;

public class CheckPasswordTest{
	@Test
	public void test(){
		try{
			UsersTools.newUser("---","-","--","----");
			assertEquals(SessionsTools.checkPassword("---","-"),true);
			assertEquals(SessionsTools.checkPassword("---","1234"),false);
		}
		catch(Exception e){}
	}
}