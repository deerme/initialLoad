package com.euge.initialload.model.stagging;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private BigDecimal id;

	private String code;

	private BigDecimal integrationid;

	private String job;

	private BigDecimal numtries;

	private String operation;

	private String segment;

	private String status;

	public Customer() {
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

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}