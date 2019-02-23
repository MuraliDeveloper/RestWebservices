package com.module;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmployeeResponse {

	private String appStatus;

	private String statusMsg;

	private List<Employee> emps;
  
	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public List<Employee> getEmps() {
		if(emps==null){
			emps = new ArrayList<Employee>();
		}
		return emps;
	}

	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}

}
