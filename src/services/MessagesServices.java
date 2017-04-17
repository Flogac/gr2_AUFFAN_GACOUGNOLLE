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
			int id = SessionsTools.getIdUser( login );
			boolean key_ok = SessionsTools.checkSession( id , key );
			if( !key_ok )
				return ErrorJSON.serviceRefused( "Wrong key" + login , 1 );
			JSONObject retour = new JSONObject();
			retour.put( "login" , MessagesTools.listMessages( id , -1 , -1) );
			return retour;
		} catch ( SQLException e ){
			return ErrorJSON.serviceRefused( "SQLQueryException" , 1000 );
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}

	public static JSONObject newMessage(String login, String key, String message , String titre ) {
		if( login == null || key == null || message == null )
			return ErrorJSON.serviceRefused( "Wrong arguments" , -1 );
		try{
			boolean is_user = SessionsTools.userExists( login );
			if( !is_user )
				return ErrorJSON.serviceRefused( "Unknown user" + login , 1 );
			int id = SessionsTools.getIdUser( login );
			boolean key_ok = SessionsTools.checkSession( id , key );
			if( !key_ok )
				return ErrorJSON.serviceRefused( "Wrong key" + login , 1 );
			MessagesTools.addMessage( id , message, titre, false, null);
			JSONObject retour = new JSONObject();
			retour.put( "login" , message );
			return retour;
		} catch ( SQLException e ){
			return ErrorJSON.serviceRefused( "SQLQueryException" , 1000 );
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}

	public static JSONObject removeMessage(String login, String key, String message) {
		if( login == null || key == null || message == null )
			return ErrorJSON.serviceRefused( "Wrong arguments" , -1 );
		try{
			boolean is_user = SessionsTools.userExists( login );
			if( !is_user )
				return ErrorJSON.serviceRefused( "Unknown user" + login , 1 );
			int id = SessionsTools.getIdUser( login );
			boolean key_ok = SessionsTools.checkSession( id , key );
			if( !key_ok )
				return ErrorJSON.serviceRefused( "Wrong key" + login , 1 );
			boolean is_owned = MessagesTools.belongsToUser( id , message );
			if( !is_owned )
				return ErrorJSON.serviceRefused( "Message not owned or do not exists" + message , 13 );
			MessagesTools.removeMessage( id , message);
			JSONObject retour = new JSONObject();
			retour.put( "login" , message );
			return retour;
		} catch ( SQLException e ){
			return ErrorJSON.serviceRefused( "SQLQueryException" , 1000 );
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}

}
