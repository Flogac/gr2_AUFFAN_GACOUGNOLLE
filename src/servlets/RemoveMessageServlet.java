package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.MessagesServices;

public class RemoveMessageServlet extends HttpServlet {

	 /**
	 * Default constructor.
	 */
	 public RemoveMessageServlet() {
	 }
	 
	 /*
	 * This method will handle all GET request.
	 */
	 protected void doGet(HttpServletRequest request,
	 HttpServletResponse response) throws ServletException, IOException {
	 
	 	String login = request.getParameter( "login" );
	 	String key = request.getParameter( "key" );
	 	String message = request.getParameter("message" );
	 	JSONObject retour = MessagesServices.removeMessage( login , key , message );
	 	response.setContentType( "text/plain" );
	 	response.getWriter().print( retour );
	 }

}
