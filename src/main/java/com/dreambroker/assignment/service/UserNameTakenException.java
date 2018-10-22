package com.dreambroker.assignment.service;

public class UserNameTakenException extends RuntimeException {

	public UserNameTakenException() {
		super();
	}

	public UserNameTakenException(String what) {
		super(what);
	}
}
