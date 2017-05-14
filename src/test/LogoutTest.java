//ce fichier teste services.UserServices.logout: TERMINE
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import services.UserServices;
import bd.SessionsTools;

public class LogoutTest{
	@Test
	public void test(){
		UserServices.login("test2","test");
		UserServices.logout("test2");
		try{
			assertEquals(false,SessionsTools.checkSession(SessionsTools.getIdUser("test2")));
		}
		catch(Exception e){
			fail();
		}
	}
}