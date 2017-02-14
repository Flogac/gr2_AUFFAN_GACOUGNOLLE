package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.FriendsServices;
import services.UserServices;

public class AddFriendServlet extends HttpServlet{

	 /**
	 * Default constructor.
	 */
	 public AddFriendServlet() {
	 }
	 
	 /*
	 * This method will handle all GET request.
	 */
	 protected void doGet(HttpServletRequest request,
	 HttpServletResponse response) throws ServletException, IOException {
	 
	 	String login = request.getParameter( "login" );
	 	String key = request.getParameter( "key" );
	 	String friendToAdd = request.getParameter("friendToAdd");
	 	JSONObject retour = FriendsServices.addFriend( login , key , friendToAdd );
	 	response.setContentType( "text/plain" );
	 	response.getWriter().print( retour );
	 }
	 

}
