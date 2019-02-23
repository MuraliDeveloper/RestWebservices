package com.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.util.ErrorDTO;
import com.util.ServiceException;

@Provider
public class GenericExceptionMapper implements
		javax.ws.rs.ext.ExceptionMapper<Throwable> {

	public Response toResponse(Throwable ex) {
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new ErrorDTO("000", ex.getMessage())).build();
	}
}
