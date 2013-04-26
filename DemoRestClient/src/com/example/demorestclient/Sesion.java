package com.example.demorestclient;



import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Sesion extends Activity {
	
	private EditText editTextName;
	private EditText editTextPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		setContentView(R.layout.activity_sesion);
		
		editTextName = (EditText) findViewById(R.id.editTextName);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
	}

	public void irPrincipal(View v) {
		Intent intent = new Intent(this, CrearUsuario.class);
		startActivity(intent);	
	}
	
	public void doBindService(View v) {
		System.out.println("click iniciar sesion");
		Intent intent = new Intent(this, BoundService.class);
		bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
	
	}

	private void iniciarSesion(final BlockingWorker blockingWorker) {
		
		new Thread(new Runnable() {
			public void run() {
				String username = editTextName.getText().toString();
				String password = editTextPassword.getText().toString();
			
				try {
					String response = blockingWorker.iniciarSesion(username,
							password);
					if(response.charAt(10)=='N' && response.charAt(13)=='s' && response.charAt(16)=='e')
					{
						
						System.out.println("El usuario y la contraseña no coinciden");
					}
					else{
						Intent intent= new Intent();
						intent.setClass(getApplicationContext(), ActPrincipal.class);
						startActivity(intent);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}).start();
		
		
	}

	
	@Override
	protected void onStop() {
		super.onStop();

		try {
			unbindService(serviceConnection);
		} catch (Exception e) {
			Log.i("lindas", "Servicio desligado");
		}
	}
	private ServiceConnection serviceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalBinder localBinder = (LocalBinder) service;
			iniciarSesion(localBinder.getBlockingWorker());
		}
		public void onServiceDisconnected(ComponentName name) {	}
	};

}

