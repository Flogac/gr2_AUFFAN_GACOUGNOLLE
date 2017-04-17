//changer toutes les occurrences de message par comment ou commentaire
package bd;

import java.net.UnknownHostException;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;

public class MessagesTools{

	public static JSONArray listMessages(int id , int nombre ) throws UnknownHostException {
		DBCursor curseur = getMessage(nombre, null, id, null, null, null, null );
		JSONObject jasonTemp = new JSONObject();
		JSONArray tableau = new JSONArray();
		int i = 0;
		
		while(curseur.hasNext() && i < nombre || nombre == -1 )
		{
			BasicDBObject recuperation = (BasicDBObject) curseur.next();
			jasonTemp.put("id", recuperation.getObjectId("_id").toString());
			jasonTemp.put("auteur", recuperation.getString("auteur"));
			jasonTemp.put("message", recuperation.getString("message"));
			jasonTemp.put("titre", recuperation.getString("title"));
			jasonTemp.put("date", recuperation.getString("date"));
			jasonTemp.put("source", recuperation.getString("source"));
				
			tableau.put(jasonTemp);
			
		}
		
		curseur.close();
		return tableau;
	}
	//methode de verification de l'existence d'un commentaire commentExists()
	//messageBelongsUser() pour verifier si un message appartient bien a un utilisateur
	//deleteMessageBD()
	//listMessageBD() pour afficher les derniers commentaires
	//listMessage(query,contact) pour faire une recherche dans nos commentaires

	public static boolean addMessage(int id, String message , String titre , boolean commentaire , String source ) throws UnknownHostException {
		if(message == null || titre == null || (commentaire && source == null ) ) return false;
		
			BasicDBObject retour = new BasicDBObject();
			 retour.append("auteur", id);
			 retour.append("message", message);
			 retour.append("title", titre);
			 retour.append("date", new Date().getTime());
			 if( commentaire ) retour.append("source", source);
			
			try
			{
				DatabaseServices.add("messages" , retour);
			}
			catch (MongoException e)
			{
				return false;
			}
			return true;
		}
	

	public static boolean removeMessage(int id, String id_message) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean belongsToUser(int id, String id_message) throws UnknownHostException {
		if( getMessage(1, null, id, null, null, null, id_message).hasNext() )
			return true;
		return false;
	}
	
	private static DBCursor getMessage( final int nombre , final String date , final int id, final String message , final String titre , final String source , final String id_message) throws UnknownHostException{
		BasicDBObject requete = new BasicDBObject();
		
		if(source !=null)
			requete.append( "source", source);		// Message parent, 0 si aucun, n pour commentaire du message n.
		
		if( date != null )
			requete.append( "date", date);
		
		if( id > 0 )
			requete.append( "auteur" , id );
		
		if( message != null )
			requete.append( "message" , message);
		
		if( titre != null )
			requete.append( "titre" , titre);
		
		if( id_message != null )
			requete.append( "_id" , id_message );
		
		DBCursor curseur = DatabaseServices.find("messages", requete);
		curseur.sort(new BasicDBObject().append("date", 1));
		
		return curseur;
	}
}