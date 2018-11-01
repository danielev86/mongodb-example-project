package com.danielev86.mongoexample.bean;

import java.io.Serializable;

public class PersonBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String firstName;
	
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
