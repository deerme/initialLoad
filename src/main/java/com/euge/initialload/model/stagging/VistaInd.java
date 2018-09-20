package com.euge.initialload.model.stagging;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the VISTA_IND database table.
 * 
 */
@Entity
@Table(name="VISTA_IND")
@NamedQuery(name="VistaInd.findAll", query="SELECT v FROM VistaInd v")
public class VistaInd implements Serializable {
	private static final long serialVersionUID = 1L;

	private String city;

	private String code;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_INTEGRATIONID")
	private BigDecimal custIntegrationid;

	@Column(name="ADDR_INTEGRATIONID")
	private BigDecimal addr_integrationid;

	@Column(name="CUST_OPER")
	private String custOper;

	private String email;

	private BigDecimal gaia;

	@Id
	private BigDecimal id;

	private BigDecimal integrationid;

	private String job;

	private String name;

	private BigDecimal numtries;

	private String operation;

	private String phone;

	private String postalcode;

	private BigDecimal scn;

	private String segment;

	private String status;

	private String street;

	private String streetnum;

	private String surname;

	public VistaInd() {
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

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public BigDecimal getCustIntegrationid() {
		return this.custIntegrationid;
	}

	public void setCustIntegrationid(BigDecimal custIntegrationid) {
		this.custIntegrationid = custIntegrationid;
	}

	public String getCustOper() {
		return this.custOper;
	}

	public void setCustOper(String custOper) {
		this.custOper = custOper;
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

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
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

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public BigDecimal getScn() {
		return this.scn;
	}

	public void setScn(BigDecimal scn) {
		this.scn = scn;
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

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public BigDecimal getAddr_integrationid() {
		return this.addr_integrationid;
	}

	public void setAddr_integrationid(BigDecimal addr_integrationid) {
		this.addr_integrationid = addr_integrationid;
	}

}