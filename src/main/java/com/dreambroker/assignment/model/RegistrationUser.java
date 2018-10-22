package com.dreambroker.assignment.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegistrationUser {
	@NotBlank
	private String name;

	@NotBlank
	@Size(min=8, max=128)
	private String password;


	public String getName() {
		return name;
	}
	public void setName(String name) { this.name = name; }


	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

}
