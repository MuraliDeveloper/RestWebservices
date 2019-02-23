package com.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.module.Country;

@Path("/EmployeeService")
public class EmployeeService {
	
	@Context
	UriInfo uriInfo;
	
	@Path("/addEmpXML")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response putCountryXML(Country todo) throws URISyntaxException {
		todo.getCountryCode();
		return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	@Path("/addEmpJSON")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putCountryJson(Country countryJson) throws URISyntaxException {
		countryJson.getCountryCode();
		return Response.created(new URI("/add3")).build();
	}

	@POST
	@Path("/addEmpForm")
	public Response addUser(@FormParam("name") String name,
			@FormParam("age") int age) {
		return Response.status(200)
				.entity("addUser is called, name : " + name + ", age : " + age)
				.build();

	}

	// to be tested
	@POST
	@Path("/add2")
	public Response addUser2(MultivaluedMap<String, String> formParams) {
		return Response.status(200).entity("addUser is called, name : "+formParams.get("name"))
				.build();
	}
}