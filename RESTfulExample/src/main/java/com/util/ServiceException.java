package com.util;

import javax.ws.rs.core.Response.Status;

public class ServiceException extends Exception {

	private final Status httpStatus;
	private final String errorCode;
	private final  String errorMsg;

	public ServiceException(Status httpStatus,String errorCode, String errorMsg) {
		super();
		this.httpStatus =  httpStatus;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public Status getHttpStatus() {
		return httpStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
 
}
