package com.example.demorestclient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Eventos extends Activity {

	Spinner prueba;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventos);
		
		prueba = (Spinner) findViewById(R.id.spinner1);
System.out.println("funcionaaaaaaaaa222222222222222222222222222");
//Creamos el adaptador
ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ciudades,android.R.layout.simple_spinner_item);
//Añadimos el layout para el menú
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Le indicamos al spinner el adaptador a usar
prueba.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eventos, menu);
		return true;
	}

	
	public void irEventoSeleccionado(View v) {
		Intent intent = new Intent(this, EventoSeleccionado.class);
		startActivity(intent);
	}
	
}
