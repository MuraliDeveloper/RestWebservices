package com.module;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {

	String name;
	String employeeId;
	String pan;
	
/*	{
	    "name": "test_34132",
	    "employeeId": "34132",
	    "pan": "Pan_34132"
	}
*/	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", employeeId=" + employeeId + "]";
	}

	 

 

}
