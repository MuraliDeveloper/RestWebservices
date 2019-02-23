package com.module;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmployeeList {

	private String userName;
	private List<Employee> employees;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Employee> getEmployees() {
		if(employees==null){
			employees = new ArrayList<Employee>();
		}
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
