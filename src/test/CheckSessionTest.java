//ce fichier sert a tester bd.SessionsTools.checkSession: TERMINE
package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bd.SessionsTools;

public class CheckSessionTest{
	@Test
	public void test(){
		try{
			SessionsTools.supprSession(26);
			SessionsTools.insertSession(26,false);
			assertEquals(SessionsTools.checkSession(26),true);
		}
		catch(Exception e){}
	}
}