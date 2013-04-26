package com.example.demorestclient;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BoundService extends Service {

	private LocalBinder localBinder = new LocalBinder(new BlockingWorker());

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("creo el localbinder");
		return localBinder;
	}

}
