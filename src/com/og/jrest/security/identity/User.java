package com.og.jrest.security.identity;

public class User {

	// TODO Make this better. This is basically nonsense right now

	private String token;

	private String name;

	public User(String token) {
		this.token = token;
	}

	public User(String token, String name) {
		this.token = token;
		this.name = name;
	}

	public String getToken() {
		return this.token;
	}

	public String setToken(String token) {
		return token;
	}

	public String getName() {
		return this.name;
	}

}
