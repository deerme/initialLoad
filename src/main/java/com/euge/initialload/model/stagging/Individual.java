package com.euge.initialload.model.stagging;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the INDIVIDUAL database table.
 * 
 */
@Entity
@NamedQuery(name="Individual.findAll", query="SELECT i FROM Individual i")
public class Individual implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private BigDecimal id;

	private String code;

	private String email;

	private BigDecimal gaia;

	private BigDecimal integrationid;

	private String job;

	private String name;

	private BigDecimal numtries;

	private String operation;

	private String phone;

	private BigDecimal scn;

	private String status;

	private String surname;

	public Individual() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getScn() {
		return this.scn;
	}

	public void setScn(BigDecimal scn) {
		this.scn = scn;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}