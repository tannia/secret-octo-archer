package com.example.demorestclient;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.kevinsawicki.http.HttpRequest;

public class CrearUsuario extends Activity {

	Spinner prueba;
	String usuario;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_song);
		prueba = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ciudades,android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		prueba.setAdapter(adapter);

	}

	public void createSong(View v) {
		EditText editTextUrl = (EditText) findViewById(R.id.editTextUrl);
		EditText editTextName = (EditText) findViewById(R.id.editTextName);
		EditText editTextPass = (EditText) findViewById(R.id.EditText01);
		EditText editTextEmail = (EditText) findViewById(R.id.EditText02);
		RadioButton rbtnFemenino = (RadioButton) findViewById(R.id.radio0);
		String sex = "0";
		if (rbtnFemenino.isChecked()) {
			sex = "1";
		}

		String username = editTextUrl.getText().toString();
		String name = editTextName.getText().toString();
		String pass = editTextPass.getText().toString();
		String email = editTextEmail.getText().toString();
		String city = prueba.getSelectedItem().toString();

		
		
		new CreateSongTask().execute(username, name, pass,sex,email,city);
		
		if(username==null){
		Intent intent = new Intent(this, ActPrincipal.class);
		startActivity(intent);
		}
		}
	
	private class CreateSongTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			
			String username = params[0];
			String name = params[1];
			String pass = params[2];
			String sex = params[3];
			String email = params[4];
			String city = params[5];
			
			Map<String, String> form = new HashMap<String, String>();

			
			form.put("city", city);
			form.put("email", email);
			form.put("sex", sex);
			form.put("pass", pass);
			form.put("name", name);
			form.put("username", username);
			
			try {
				
				
					HttpRequest httpRequest =
					HttpRequest.post("http://tefatanny.byethost5.com/backend/public/index.php/users")
					.form(form)
					.connectTimeout(4000)
					.readTimeout   (4000);
				usuario=null;
				String body = httpRequest.body();
			
				
				JSONObject jsonObject = new JSONObject(body);
				if(jsonObject.has("username")) {
					return "Se ha registrsdo el usuario: "+jsonObject.getString("username");
				}
				
				else{

					return "El usuario "+username+" existe";
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			
		}
		
		@Override
		protected void onPostExecute(String result) {
			if(result != null && !TextUtils.isEmpty(result)) {
				Toast.makeText(CrearUsuario.this, result,
					Toast.LENGTH_LONG).show();
				Intent intent= new Intent();
				intent.setClass(getApplicationContext(), ActPrincipal.class);
				startActivity(intent);
			}
			else {
				Toast.makeText(CrearUsuario.this, "Error!", Toast.LENGTH_LONG).show();
				finish();
			}
			
		}
		
	}
}

