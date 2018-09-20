package com.euge.initialload.model.fda;

public class individualFDA {
	private int id;

	private int integrationID;

	private String name;

	private int scn;

	private String surname;

	public individualFDA() {}

	public individualFDA(int integrationID,String name,int scn,String surname) {
		this.integrationID=integrationID;
		this.name=name;
		this.scn=scn;
		this.surname=surname;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIntegrationID() {
		return this.integrationID;
	}

	public void setIntegrationID(int integrationID) {
		this.integrationID = integrationID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScn() {
		return this.scn;
	}

	public void setScn(int scn) {
		this.scn = scn;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}

