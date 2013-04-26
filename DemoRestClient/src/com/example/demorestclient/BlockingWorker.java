package com.example.demorestclient;



import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;


import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.Session;

public class BlockingWorker {


	private static final String SUCCESS  = "success";
	public String iniciarSesion(String username, String password) {
		try {
			
			
			
Map<String, String> form = new HashMap<String, String>();

			

			form.put("name", username);
			form.put("username", password);

				
				
					HttpRequest httpRequest =
					HttpRequest.post("http://tefatanny.byethost5.com/backend/public/index.php/users")
					.form(form)
					.connectTimeout(4000)
					.readTimeout   (4000);
			
			
			String res = httpRequest.body();
			
			JSONObject jsonObject = new JSONObject(res);

			// Inició sesión correctamente.
			if(jsonObject.has("token")){
				Session.sessionData.put("token", jsonObject.getString("token"));
				Session.sessionData.put("id",    jsonObject.getString("id"));
				return SUCCESS;
			}
			else {
				return jsonObject.getString("error");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "no funciona";
		}
		
	}

}