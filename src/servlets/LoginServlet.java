//version 0

package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.UserServices;
 
/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
 
 /**
	 * 
	 */
	private static final long serialVersionUID = -5477069511069024818L;

/**
 * Default constructor.
 */
 public LoginServlet() {
 }
 
 /*
 * This method will handle all GET request.
 */
 protected void doGet(HttpServletRequest request,
 HttpServletResponse response) throws ServletException, IOException {
 
 	String login = request.getParameter( "login" );
 	String mdp = request.getParameter( "mdp" );
 	JSONObject retour = UserServices.login( login , mdp );
 	response.setContentType( "text/plain" );
 	response.getWriter().print( retour );
 }
 

}