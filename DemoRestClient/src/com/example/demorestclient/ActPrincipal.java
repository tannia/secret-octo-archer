package com.example.demorestclient;

import com.github.kevinsawicki.http.Session;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ActPrincipal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act_principal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_principal, menu);
		return true;
	}

	
	public void irEventos(View v) {
		Intent intent = new Intent(this, Eventos.class);
		startActivity(intent);
	}

	public void irConcursos(View v) {
		Intent intent = new Intent(this, Concursos.class);
		startActivity(intent);
	}
	
	public void irNotificaciones(View v) {
		Intent intent = new Intent(this, Notificaciones.class);
		startActivity(intent);
	}
	
	public void irPerfil(View v) {
		Intent intent = new Intent(this, Perfil.class);
		startActivity(intent);
	}
	
	public void cerrarSession(View view) {
		Session.sessionData.remove("token");
		Session.sessionData.remove("id");

		Intent intent = new Intent(this, Sesion.class);
		startActivity(intent);
	}
	
	
}
