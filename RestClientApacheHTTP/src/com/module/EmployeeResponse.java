package com.module;

import java.util.ArrayList;
import java.util.List;

public class EmployeeResponse {

	private String AppStatus;

	private String StatusMsg;

	private List<Employee> emps;

	public String getAppStatus() {
		return AppStatus;
	}

	public void setAppStatus(String appStatus) {
		AppStatus = appStatus;
	}

	public String getStatusMsg() {
		return StatusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		StatusMsg = statusMsg;
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
