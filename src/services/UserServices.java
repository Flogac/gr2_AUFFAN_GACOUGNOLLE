package services;

import org.json.JSONObject;

import bd.SessionsTools;
import bd.UsersTools;

import java.sql.SQLException;

public class UserServices {
	
	public static JSONObject loginService( String login , String mdp ){
		if( login == null || mdp == null )
			return ErrorJSON.serviceRefused( "Wrong arguments" , -1 );
		try{
			boolean is_user = UsersTools.userExists( login );
			if( !is_user )
				return ErrorJSON.serviceRefused( "Unknown user" + login , 1 );
			boolean password_ok = SessionsTools.checkPassword( login , mdp );
			if( !password_ok )
				return ErrorJSON.serviceRefused( "Wrong password", 1 );
			JSONObject retour = new JSONObject();
			int id = SessionsTools.getIdUser( login );
			String key = SessionsTools.insertSession( id , false );
			retour.put("id",id);
			retour.put("login",login);
			retour.put( "key" , key );
			return retour;
		} catch ( SQLException e ){
			return ErrorJSON.serviceRefused( "SQLQueryException" , 1000 );
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}

	public static JSONObject logout(String login, String key) {
		if( login == null || key == null )
			return ErrorJSON.serviceRefused( "Wrong arguments" , -1 );
		try
		{
			boolean is_user = SessionsTools.userExists( login );
			if( !is_user )
				return ErrorJSON.serviceRefused( "Unknown user" + login , 1 );
			int id = SessionsTools.getIdUser( login );
			boolean key_ok = SessionsTools.checkSession( id , key );
			if( !key_ok )
				return ErrorJSON.serviceRefused( "Wrong key" + login , 2 );
			SessionsTools.supprSession( id , key );
			JSONObject retour = new JSONObject();
			retour.put( "login" , login );
			return retour;
		} catch ( SQLException e ){
			return ErrorJSON.serviceRefused( "SQLQueryException" , 1000 );
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}

	public static JSONObject createUser(String login, String mdp, String prenom, String nom) {
		if( login == null || mdp == null || prenom == null || nom == null )
			return ErrorJSON.serviceRefused( "Wrong arguments" , -1 );
		try{
			boolean is_user = SessionsTools.userExists( login );
			if( is_user)
				return ErrorJSON.serviceRefused("Login already used", 2);
			UsersTools.newUser( login, mdp , prenom , nom );
			int id = SessionsTools.getIdUser( login );
			JSONObject retour = new JSONObject();
			String key = SessionsTools.insertSession( id , false );
			retour.put( "key" , key );
			return retour;
		} catch ( SQLException e ){
			return ErrorJSON.serviceRefused( "SQLQueryException" , 1000 );
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}
}
