package com.example.demorestclient;


import android.os.Binder;

public class LocalBinder extends Binder {

	private BlockingWorker blockingWorker;

	public LocalBinder(BlockingWorker blockingWorker) {
		this.blockingWorker = blockingWorker;
		System.out.println("localbinder");
	}	

	public BlockingWorker getBlockingWorker() {
		return blockingWorker;
	}

}
