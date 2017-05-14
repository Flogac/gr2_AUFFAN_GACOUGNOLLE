//permet de tester bd.UsersTools.supprUser: TERMINE
package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

import bd.UsersTools;

public class SupprUserTest{
	@Test
	public void test(){
		try{
			UsersTools.newUser("bob","1234","bob","dylan");
		}
		catch(Exception e){}
		try{
			UsersTools.supprUser("bob");
			assertFalse(UsersTools.userExists("bob"));
		}
		catch(Exception e){
			fail();
		}
	}
}