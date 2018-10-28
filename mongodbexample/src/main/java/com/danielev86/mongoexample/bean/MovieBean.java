package com.danielev86.mongoexample.bean;

import java.io.Serializable;
import java.util.List;

public class MovieBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;

	private List<ActorBean> lstActor;

	private Integer year;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ActorBean> getLstActor() {
		return lstActor;
	}

	public void setLstActor(List<ActorBean> lstActor) {
		this.lstActor = lstActor;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
