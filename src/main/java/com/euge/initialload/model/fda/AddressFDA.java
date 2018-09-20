package com.euge.initialload.model.fda;

public class AddressFDA {
	private int id;

	private String city;

	private int gaia;

	private int integrationID;

	private String number;

	private String postalCode;

	private String street;

	public AddressFDA(String city,int gaia,int integrationID,String number,String postalCode,String street) {
		this.city=city;
		this.gaia=gaia;
		this.integrationID=integrationID;
		this.number=number;
		this.postalCode=postalCode;
		this.street=street;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getGaia() {
		return this.gaia;
	}

	public void setGaia(int gaia) {
		this.gaia = gaia;
	}

	public int getIntegrationID() {
		return this.integrationID;
	}

	public void setIntegrationID(int integrationID) {
		this.integrationID = integrationID;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

}