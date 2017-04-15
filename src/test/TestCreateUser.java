package test;

import java.sql.SQLException;

import services.FriendsServices;
import services.UserServices;
import bd.SessionsTools;
import bd.UsersTools;

//FONCTIONNE!!!!!
public class TestCreateUser{
	public static void main(String[] args) throws SQLException{
		System.out.println("entree de main dans test.TestInsertionMysqlEtUserExists.java");
		System.out.println( FriendsServices.addFriend("DanyDuTurfu", "22190873460", "Vatar"));
		System.out.println( FriendsServices.addFriend("DanyDuTurfu", "22190873460", "Florange"));
		System.out.println( FriendsServices.listFriends("DanyDuTurfu", "22190873460") );
		//System.out.println( UserServices.login("Vatar", "DanyUDaBest"));
		//System.out.println( UserServices.logout("Vatar", "9-1954067198"));
		/*String clef = SessionsTools.insertSession(9, true);
		System.out.println(clef);
		SessionsTools.supprSession(9, clef);*/
		//permet d'ajouter un timestamp compatible avec mysql
		//DatabaseServices.insert("",new ArrayList<String>(),new ArrayList<String>());
		System.out.println(SessionsTools.userExists("Vatar"));
		System.out.println("sortie de main dans test.TestInsertionMysqlEtUserExists.java");
	}
}