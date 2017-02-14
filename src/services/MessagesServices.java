package services;

import java.sql.SQLException;

import org.json.JSONObject;

import bd.MessagesTools;
import bd.SessionsTools;

public class MessagesServices {

	public static JSONObject listMessages(String login, String key) {
		if( login == null || key == null )
			return ErrorJSON.serviceRefused( "Wrong arguments" , -1 );
		try{
			boolean is_user = SessionsTools.userExists( login );
			if( !is_user )
				return ErrorJSON.serviceRefused( "Unknown user" + login , 1 );
			boolean key_ok = SessionsTools.checkSession( login , key );
			if( !key_ok )
				return ErrorJSON.serviceRefused( "Wrong key" + login , 1 );
			int id = SessionsTools.getIdUser( login );
			JSONObject retour = new JSONObject();
			retour.put( "login" , MessagesTools.listMessages( id ) );
			return retour;
		} catch ( SQLException e ){
			return ErrorJSON.serviceRefused( "SQLQueryException" , 1000 );
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}

	public static JSONObject newMessage(String login, String key, String message) {
		if( login == null || key == null || message == null )
			return ErrorJSON.serviceRefused( "Wrong arguments" , -1 );
		try{
			boolean is_user = SessionsTools.userExists( login );
			if( !is_user )
				return ErrorJSON.serviceRefused( "Unknown user" + login , 1 );
			boolean key_ok = SessionsTools.checkSession( login , key );
			if( !key_ok )
				return ErrorJSON.serviceRefused( "Wrong key" + login , 1 );
			int id = SessionsTools.getIdUser( login );
			boolean is_message = MessagesTools.addMessage( id , message);
			JSONObject retour = new JSONObject();
			retour.put( "login" , MessagesTools.listMessages( id ) );
			return retour;
		} catch ( SQLException e ){
			return ErrorJSON.serviceRefused( "SQLQueryException" , 1000 );
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}

}
