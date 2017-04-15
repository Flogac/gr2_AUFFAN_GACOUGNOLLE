package servlets;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.UserServices;

public class CreateUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3258215123825292918L;

	/**
	 * Default constructor.
	 */
	 public CreateUserServlet() {
	 }
	 
	 /*
	 * This method will handle all GET request.
	 */
	 protected void doGet(HttpServletRequest request,
	 HttpServletResponse response) throws ServletException, IOException {
	 
	 	String login = request.getParameter( "login" );
	 	String mdp = request.getParameter( "mdp" );
	 	String nom = request.getParameter("nom");
	 	String prenom = request.getParameter("prenom");
	 	JSONObject retour = UserServices.createUser(login, mdp, prenom, nom);
	 	response.setContentType( "text/plain" );
	 	response.getWriter().print( retour );
	 }
	 

}
