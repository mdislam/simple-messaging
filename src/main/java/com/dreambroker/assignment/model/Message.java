package com.dreambroker.assignment.model;

import java.util.Date;

public class Message {

	private String sender;
	private String message;
	private Date sent;

	public Message() {
		sender = "";
		message = "";
		sent = null;
	}

	public Message(String sender, String message, Date sent) {
		this.sender = sender;
		this.message = message;
		this.sent = sent;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getSent() {
		return sent;
	}

	public void setSent(Date sent) {
		this.sent = sent;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
