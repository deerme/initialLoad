package com.euge.initialload.model.fda;

public class CustomerFDA{
	private int id;

	private int integrationID;

	private int scn;

	private String segment;

	public CustomerFDA(int integrationID,int scn,String segment) {
		this.integrationID=integrationID;
		this.scn=scn;
		this.segment=segment;
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

	public int getScn() {
		return this.scn;
	}

	public void setScn(int scn) {
		this.scn = scn;
	}

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

}