import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.CreateUserServlet;

public class Test{
	public static void main(String[] args){
		String key = null;
		String login = null;
		String mdp = null;
		String nom = null;
		String prenom = null;
		String friendToAdd = null;
		String friendToRemove = null;
		HttpServletRequest request = new RequestTest( key , login , mdp , nom , prenom , friendToAdd, friendToRemove);
		HttpServletResponse response = new ResponseTest();
		HttpServlet testServlet = new CreateUserServlet();
		testServlet.doGet( request , response);
		System.out.print(response.toString());
	}
}
