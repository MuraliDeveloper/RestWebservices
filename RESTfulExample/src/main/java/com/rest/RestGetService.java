package com.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.module.Country;
import com.module.Employee;

@Path("/test")
public class RestGetService {

	@Path("/text")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}
	
	@Path("/xml1")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Country getXML1() {
		return new Country(1, "India", "IN");
	}

	@Path("/xml2")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Country> getXML() {
		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries = createCountryList();
		return listOfCountries;
	}

	@GET
	@Path("/json1")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getTrackInJSON() {
		Employee track = new Employee();
		track.setName("Sandman");
		track.setEmployeeId("Metallica");
		return track;
	}

	@GET
	@Path("/json2")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Country> getCountryList() {
		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries = createCountryList();
		return listOfCountries;
	}
	
	@Path("/getCountry/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Country> getCountry(@PathParam("id") String id) {
		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries = createCountryList();
		return listOfCountries;
	}
		
	@GET
	@Path("/getEmpById/{empid}/{deptid}")
	public String getEmployeeById(
			@PathParam("empid") String empid,
			@PathParam("deptid") String deptid){
		
		//business logic  
		return "empid:: "+empid +", deptid:"+deptid; 
	}
	
	//http://localhost:8080/RESTfulExample/rest/test/getEmpById2/1;deptid=123
	@GET
	@Path("/getEmpById2/{empid}")
	public String getEmployeeById2(
			@PathParam("empid") String empid,
			@MatrixParam("deptid") String deptid){
		//business logic  
		return "empid:: "+empid +", deptid:"+deptid; 
	}
		
	@GET
	@Path("{Empid : \\d+}") //support digit only
	public Response getUserById(@PathParam("Empid") String id) {
	   return Response.status(200).entity("getUserById is called, id : " + id).build();

	}

	//first character need a-zA-Z, second character need
	//a-zA-Z_0-9].
	@GET
	@Path("/Emps/{EmpName : [a-zA-Z][a-zA-Z_0-9]}")
	public Response getUserByUserName(@PathParam("EmpName") String username) {
	   return Response.status(200)
		.entity("getUserByUserName is called, EmpName : " + username).build();

	}
	
	@GET
	@Path("/country")
	@Produces(MediaType.APPLICATION_JSON)
	public Country getCountryById(@QueryParam("id") int id) {
		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries = createCountryList();
		for (Country country : listOfCountries) {
			if (country.getId() == id)
				return country;
		}
		return null;
	}
	
	public List<Country> createCountryList() {
		Country indiaCountry = new Country(1, "India", "IN");
		Country chinaCountry = new Country(4, "China", "CH");
		Country nepalCountry = new Country(3, "Nepal", "NP");
		Country bhutanCountry = new Country(2, "Bhutan", "BH");

		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries.add(indiaCountry);
		listOfCountries.add(chinaCountry);
		listOfCountries.add(nepalCountry);
		listOfCountries.add(bhutanCountry);
		return listOfCountries;
	}

	@GET
	@Path("/getHeader")
	public Response addUser(@HeaderParam("user-agent") String userAgent) {
		return Response.status(200)
				.entity("addUser is called, userAgent : " + userAgent).build();
	}

	@GET
	@Path("/getHeaders")
	public Response addUser(@Context HttpHeaders headers) {
		String userAgent = headers.getRequestHeader("user-agent").get(0);
		return Response.status(200)
				.entity("addUser is called, userAgent : " + userAgent).build();

	}

}