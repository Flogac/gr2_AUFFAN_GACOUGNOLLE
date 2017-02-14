package services;

import org.json.JSONObject;

public class ErrorJSON {

	public static JSONObject serviceRefused(String string, int i) {
		JSONObject res=new JSONObject();
		res.put(string,i);
		return res;
	}
}
