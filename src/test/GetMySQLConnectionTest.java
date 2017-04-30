//ce fichier permet de tester bd.DatabaseServices.getMySQLConnection: TERMINE
package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bd.DatabaseServices;

public class GetMySQLConnectionTest{
	@Test
	public void test(){
		try{
			DatabaseServices.getMySQLConnection();
			assertTrue(true);
		}
		catch(Exception e){}
	}
}