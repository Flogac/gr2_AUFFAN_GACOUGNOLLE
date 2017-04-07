//Codes d'erreur
//0:succes

package services;

import org.json.JSONObject;

public class ErrorJSON {

	public static JSONObject serviceRefused(String message, int codeErreur){
		System.out.println("entree dans serviceRefused de services.ErrorJSON");
		JSONObject res=new JSONObject();
		res.put(message,codeErreur);
		return res;
	}
	public static JSONObject serviceAccepted(){
		System.out.println("entree dans serviceAccepted de services.ErrorJSON");
		JSONObject res=new JSONObject();
		res.put("succes",0);
		return res;
	}
}
