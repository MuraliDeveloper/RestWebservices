package com.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorDTO {

	private String AppStatus;
	private String StatusMsg;
	 
	public ErrorDTO(String appStatus, String statusMsg) {
		super();
		AppStatus = appStatus;
		StatusMsg = statusMsg;
	}

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

	public ErrorDTO() {
	}

}
