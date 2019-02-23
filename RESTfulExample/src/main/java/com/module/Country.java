package com.module;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Country {
	private int id;
	private String countryName;
	private String countryCode;

	@Override
	public String toString() {
		return "Country [id=" + id + ", countryName=" + countryName
				+ ", countryCode=" + countryCode + "]";
	}

	public int getId() {
		return id;
	}

	public Country() {
		super();
	}

	public Country(int id, String countryName, String countryCode) {
		super();
		this.id = id;
		this.countryName = countryName;
		this.countryCode = countryCode;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
