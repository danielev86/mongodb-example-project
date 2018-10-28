package com.danielev86.mongoexample.bean;

import java.io.Serializable;

public class ActorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String actor;

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

}
