package com.euge.initialload.model.stagging;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ADDRESS database table.
 * 
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private BigDecimal id;

	private String city;

	private String code;

	private BigDecimal gaia;

	private BigDecimal integrationid;

	private String job;

	private BigDecimal numtries;

	private String operation;

	private String postalcode;

	private String status;

	private String street;

	private String streetnum;

	public Address() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getGaia() {
		return this.gaia;
	}

	public void setGaia(BigDecimal gaia) {
		this.gaia = gaia;
	}

	public BigDecimal getIntegrationid() {
		return this.integrationid;
	}

	public void setIntegrationid(BigDecimal integrationid) {
		this.integrationid = integrationid;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public BigDecimal getNumtries() {
		return this.numtries;
	}

	public void setNumtries(BigDecimal numtries) {
		this.numtries = numtries;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetnum() {
		return this.streetnum;
	}

	public void setStreetnum(String streetnum) {
		this.streetnum = streetnum;
	}

}