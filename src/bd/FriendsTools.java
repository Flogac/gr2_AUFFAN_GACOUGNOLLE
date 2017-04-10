package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FriendsTools{

	public static boolean addFriend(int id, int id_friend) throws SQLException {
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("de");
		colonnes.add("vers");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(""+id);
		valeurs.add(""+id_friend);
		DatabaseServices.insert( "Friends" , colonnes , valeurs );
		return true;
	}

	public static boolean isFriend(int id, int id_friend) throws SQLException {
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("de");
		colonnes.add("vers");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(""+id);
		valeurs.add(""+id_friend);
		
		return DatabaseServices.exists("Friends", colonnes, valeurs) ;
	}

	public static boolean removeFriend(int id, int id_friend) throws SQLException {
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("de");
		colonnes.add("vers");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(""+id);
		valeurs.add(""+id_friend);
		DatabaseServices.drop( "Session" , colonnes , valeurs );
		return true;
	}

	public static String listFriends(int id) throws SQLException {
		ArrayList<String> colonnes = new ArrayList<String>();
		colonnes.add("de");
		ArrayList<String> valeurs = new ArrayList<String>();
		valeurs.add(""+id);
		ArrayList<String> select = new ArrayList<String>();
		select.add( "vers" );
		ResultSet resultat = DatabaseServices.select( "Friends" , select , colonnes , valeurs );
		String arr = "";
		while (resultat.next()) {
		    arr += resultat.getString("vers");
		}
		return arr;
	}

}