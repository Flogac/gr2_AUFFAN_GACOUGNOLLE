package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.UserServices;
 
/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {

	 /**
	 * Default constructor.
	 */
	 public LogoutServlet() {
	 }
	 
	 /*
	 * This method will handle all GET request.
	 */
	 protected void doGet(HttpServletRequest request,
	 HttpServletResponse response) throws ServletException, IOException {
	 
	 	String login = request.getParameter( "login" );
	 	String key = request.getParameter( "key" );
	 	JSONObject retour = UserServices.logout( login , key );
	 	response.setContentType( "text/plain" );
	 	response.getWriter().print( retour );
	 }
	
}
