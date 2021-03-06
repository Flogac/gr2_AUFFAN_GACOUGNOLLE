package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.MessagesServices;

public class NewMessageServlet extends HttpServlet {

	 /**
	 * 
	 */
	private static final long serialVersionUID = -1326023034810755869L;

	/**
	 * Default constructor.
	 */
	 public NewMessageServlet() {
	 }
	 
	 /*
	 * This method will handle all GET request.
	 */
	 protected void doGet(HttpServletRequest request,
	 HttpServletResponse response) throws ServletException, IOException {
	 
	 	String login = request.getParameter( "login" );
	 	String key = request.getParameter( "key" );
	 	String message = request.getParameter("message" );
	 	String titre = request.getParameter( "titre" );
	 	JSONObject retour = MessagesServices.newMessage( login , key , message, titre );
	 	response.setContentType( "text/plain" );
	 	response.getWriter().print( retour );
	 }

}
