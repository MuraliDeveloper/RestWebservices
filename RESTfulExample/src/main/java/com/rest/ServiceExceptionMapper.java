package com.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.util.ErrorDTO;
import com.util.ServiceException;

@Provider
public class ServiceExceptionMapper implements
		javax.ws.rs.ext.ExceptionMapper<ServiceException> {

	public Response toResponse(ServiceException ex) {
		return Response.status(ex.getHttpStatus())
				.entity(new ErrorDTO(ex.getErrorCode(), ex.getErrorMsg())).build();
	}
}
