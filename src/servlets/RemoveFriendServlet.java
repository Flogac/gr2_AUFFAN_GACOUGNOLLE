package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.FriendsServices;

public class RemoveFriendServlet extends HttpServlet {

	 /**
	 * Default constructor.
	 */
	 public RemoveFriendServlet() {
	 }
	 
	 /*
	 * This method will handle all GET request.
	 */
	 protected void doGet(HttpServletRequest request,
	 HttpServletResponse response) throws ServletException, IOException {
	 
	 	String login = request.getParameter( "login" );
	 	String key = request.getParameter( "key" );
	 	String friendToRemove = request.getParameter("friendToRemove");
	 	JSONObject retour = FriendsServices.removeFriend( login , key , friendToRemove );
	 	response.setContentType( "text/plain" );
	 	response.getWriter().print( retour );
	 }
	 

}
