package com.euge.initialload.model.fda;



public class ContactmediumFDA {
	private int id;

	private String email;

	private int integrationID;

	private String phone;

	private String type;

	public ContactmediumFDA(String email,int integrationID,String phone,String type) {
		this.email=email;
		this.integrationID=integrationID;
		this.phone=phone;
		this.type=type;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIntegrationID() {
		return this.integrationID;
	}

	public void setIntegrationID(int integrationID) {
		this.integrationID = integrationID;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
}