package services;

import java.sql.SQLException;

import org.json.JSONObject;

import bd.FriendsTools;
import bd.SessionsTools;

public class FriendsServices {

	public static JSONObject addFriend(String login, String key, String friendToAdd) {
		if( login == null || key == null || friendToAdd == null )
			return ErrorJSON.serviceRefused( "Wrong arguments" , -1 );
		try{
			boolean is_user = SessionsTools.userExists( login );
			if( !is_user )
				return ErrorJSON.serviceRefused( "Unknown user" + login , 1 );
			boolean is_friend = SessionsTools.userExists( friendToAdd );
				if( !is_friend )
					return ErrorJSON.serviceRefused( "Unknown user" + friendToAdd , 3 );
			boolean key_ok = SessionsTools.checkSession( login , key );
				if( !key_ok )
					return ErrorJSON.serviceRefused( "Wrong key" + login , 1 );
			int id = SessionsTools.getIdUser( login );
			int id_friend = SessionsTools.getIdUser( friendToAdd );
			boolean added_friend = FriendsTools.addFriend( id , id_friend );
			if( !added_friend ){
				return ErrorJSON.serviceRefused( "Friend is ignoring the user" + login + " " + friendToAdd, 4 );
			}
			JSONObject retour = new JSONObject();
			retour.put( "NewFriend" , friendToAdd );
			return retour;
		} catch ( SQLException e ){
			return ErrorJSON.serviceRefused( "SQLQueryException" , 1000 );
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}

	public static JSONObject removeFriend(String login, String key, String friendToRemove) {
		if( login == null || key == null || friendToRemove == null )
			return ErrorJSON.serviceRefused( "Wrong arguments" , -1 );
		try{
			boolean is_user = SessionsTools.userExists( login );
			if( !is_user )
				return ErrorJSON.serviceRefused( "Unknown user" + login , 1 );
			boolean is_friend = SessionsTools.userExists( friendToRemove );
				if( !is_friend )
					return ErrorJSON.serviceRefused( "Unknown user" + friendToRemove , 3 );
			boolean key_ok = SessionsTools.checkSession( login , key );
				if( !key_ok )
					return ErrorJSON.serviceRefused( "Wrong key" + login , 1 );
			int id = SessionsTools.getIdUser( login );
			int id_friend = SessionsTools.getIdUser( friendToRemove );
			boolean is_friended = FriendsTools.isFriend( id , id_friend );
			if( !is_friended )
				return ErrorJSON.serviceRefused( "Friend not befriended" + login , 1 );
			boolean is_removed = FriendsTools.removeFriend( id , id_friend );
			if( !is_removed )
				return ErrorJSON.serviceRefused( "Friendship is forever" + login + " " + friendToRemove , 4 );
			JSONObject retour = new JSONObject();
			retour.put( "OldFriend" , friendToRemove );
			return retour;
		} catch ( SQLException e ){
			return ErrorJSON.serviceRefused( "SQLQueryException" , 1000 );
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}

	public static JSONObject listFriends(String login, String key) {
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
			retour.put( "Friends:" , FriendsTools.listFriends( id ) );
			return retour;
		} catch ( SQLException e ){
			return ErrorJSON.serviceRefused( "SQLQueryException" , 1000 );
		}catch ( Exception e ){
			return ErrorJSON.serviceRefused( "Unknown error" , 10002 );
		}
	}
	
}
