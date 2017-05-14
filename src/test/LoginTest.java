//ce fichier teste services.UserServices.login: NON TERMINE
//https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html
package test;

import static org.junit.Assert.fail;

import org.junit.Test;

import services.UserServices;
import bd.SessionsTools;


public class LoginTest{
	@Test
	public void test(){
		try{
			UserServices.logout("test2");
		} 
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			UserServices.login("test2","test");
		}
		catch(Exception e){
			e.printStackTrace();
			fail();
		}
	}
}
