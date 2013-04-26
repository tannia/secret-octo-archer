package com.example.demorestclient;

public class User {


	private String User;
	
	private String pass;
	
	public User(String user, String pass) {
		super();
		this.User = user;
		this.pass = pass;
	}

	
	@Override
	public String toString() {
		return String.format("Nombre: %s, URL: %s", User, pass);
	}

	public String getName() {
		return User;
	}

	public void setName(String user) {
		this.User = user;
	}

	public String getUrl() {
		return pass;
	}

	public void setUrl(String pass) {
		this.pass = pass;
	}
	
}
