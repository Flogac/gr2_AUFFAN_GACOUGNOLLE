package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.MessagesServices;

public class ListMessagesServlet extends HttpServlet {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 7863203084382545591L;

	/**
	 * Default constructor.
	 */
	 public ListMessagesServlet() {
	 }
	 
	 /*
	 * This method will handle all GET request.
	 */
	 protected void doGet(HttpServletRequest request,
	 HttpServletResponse response) throws ServletException, IOException {
	 
	 	String login = request.getParameter( "login" );
	 	String key = request.getParameter( "key" );
	 	JSONObject retour = MessagesServices.listMessages( login , key );
	 	response.setContentType( "text/plain" );
	 	response.getWriter().print( retour );
	 }
	 

}
