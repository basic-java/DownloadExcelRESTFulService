package com.concord.report.model;

public class XLSDataModel {
	
	private String firstName; 
	private String lastName; 
	private String location;
	
	public XLSDataModel(String firstName, String lastName, String location) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.location = location;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getLocation() {
		return location;
	}
	
}
