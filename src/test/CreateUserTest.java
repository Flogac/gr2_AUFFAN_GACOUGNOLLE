//ce fichier permet de tester bd.UserServices.createUser: TERMINE
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import bd.UsersTools;

public class CreateUserTest{
	@Test
	public void test(){
		try{
			UsersTools.supprUser("bob");
		}
		catch(Exception e){}
		try{
			boolean res=UsersTools.newUser("bob","1234","bob","dylan");
			assertEquals(true,res);
		}
		catch(Exception e){
			fail();
		}
	}
}