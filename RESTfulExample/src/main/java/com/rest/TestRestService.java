package com.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.module.Country;
import com.module.CountryInfo;
import com.module.CountryList;
import com.module.Employee;

@Path("/TestRestService")
public class TestRestService {

	@Path("/getHi")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHi() {
		return "hi...";
	}

	@Path("/getHiWithQuery")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHiWithQuery(@QueryParam("name") String name,
			@QueryParam("id") int empId) {
		return "query params:: .name== " + name + " empid=" + empId;
	}

	// http://localhost:8080/RESTfulExample/rest/TestRestService/getEmp
	// http://localhost:8080/RESTfulExample/rest/TestRestService/getEmp/1234/xyz/28
	@Path("/getEmp/{empId}/{name}/{age}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getPathParams(@PathParam("empId") String empID,
			@PathParam("name") String name, @PathParam("age") int age) {
		return "path params::....name== " + name + " empid=" + empID
				+ ", age::" + age;
	}

	// http://localhost:8080/RESTfulExample/rest/TestRestService/getCountryXML/1234

	@Path("/getCountryXML/{cId}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Country getCountry(@PathParam("cId") String countryId) {
		//
		Country country = new Country();
		country.setCountryCode("CountryCode_" + countryId);
		country.setCountryName("CountryName+" + countryId);
		country.setId(Integer.parseInt(countryId));
		return country;

	}

	// http://localhost:8080/RESTfulExample/rest/TestRestService/getCountriesXML/1234
	@Path("/getCountriesXML/{cId}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Country> getCountries(@PathParam("cId") String countryId) {
		List<Country> countries = new ArrayList<Country>();

		for (int i = 1; i <= 10; i++) {
			Country country = new Country();
			country.setCountryCode("CountryCode_" + countryId + "_" + i);
			country.setCountryName("CountryName_" + countryId + "_" + i);
			country.setId(Integer.parseInt(countryId) + i);
			countries.add(country);

		}
		return countries;

	}
	
	// http://localhost:8080/RESTfulExample/rest/TestRestService/getEmpJson/123
	@Path("/getEmpJson/{empId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(@PathParam("empId") String empId){
		Employee employee = new Employee();
		employee.setEmployeeId("EMPID_"+empId);
		employee.setName("EmpName_"+empId);
		return employee;
	}
	
	// http://localhost:8080/RESTfulExample/rest/TestRestService/getCountryByCode/IN
	@Path("/getCountryByCode/{cCode}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Country getCountryByCode(@PathParam("cCode") String countryCode){
		Map<String, Country> countriesFromDB = countryMap; 
		return countriesFromDB.get(countryCode);
	}
	
	@Path("/addCountryXML")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	/*<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	<country>
	    <countryCode>AUS</countryCode>
	    <countryName>AUSTRALIA</countryName>
	    <id>19</id>
	</country>
	*/
	public Response addCountryXML(Country country) throws URISyntaxException{
		//	call the db to insert the country obj
		insertCountry(country);
		//
		return Response.created(new URI("/add3")).build();
	}
	
	
	
	@Path("/addCountryInfoXML")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addCountryInfoXML(CountryInfo countryInfo) throws URISyntaxException{
		//	call the db to insert the country obj
		insertCountry(countryInfo.getCountry());
		//
		return Response.created(new URI("/add3")).build();
	}

	
	
	/*
	 * <countryList>
	<count>12</count>
		<country>
		   <countryCode>IN</countryCode>
	        <countryName>India</countryName>
	      	<id>1</id>
	      </country>
	      	<country>
		   <countryCode>IN</countryCode>
	        <countryName>India</countryName>
	      	<id>1</id>
	      </country>
	</countryList>
	*/
	@Path("/addCountryListXML")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addCountryInfoXML(CountryList countryList) throws URISyntaxException{
		//	call the db to insert the country obj
		for(Country country :countryList.getCountry()){
		insertCountry(country);
		}
		//
		return Response.created(new URI("/add3")).build();
	}
	
	
	
	/*<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	<country>
	    <countryCode>AUS</countryCode>
	    <countryName>AUSTRALIA</countryName>
	    <id>19</id>
	</country>
	*/
	@Path("/updateCountryXML/{cCode}")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public CountryInfo updateCountryXML(Country country , @PathParam("cCode") String cCode) throws URISyntaxException{
		//	call the db to insert the country obj
		String statusCode = updateCountry(cCode,country);
		CountryInfo countryInfo = new CountryInfo();
		countryInfo.setStatusCode(statusCode);
		countryInfo.setCountry(country);
		return countryInfo;
	}
	
	
	@Path("/getALLCountries")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Country> getCountries() {
		return getALLCountriesInfo();
	}
	
	
	@Path("/deleteCountry/{cCode}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCountryByCode(@PathParam("cCode") String cCode){
		 return "Status code::"+deleteCountry(cCode);
		
	}
	/*
	  *<countryInfo>
		<count>12</count>
		<clientId>abcd<clientId>
		<country>
		   <countryCode>IN</countryCode>
	        <countryName>India</countryName>
	      	<id>1</id>
	      </country>
			</countryInfo>
	   */
	
	private List<Country> getALLCountriesInfo() {
		List<Country> countries = new ArrayList<Country>();
		for(Entry<String, Country> entry:  countryMap.entrySet()){
			countries.add(entry.getValue());
		}
		return countries;
	}

	//Response.created(new URI("/add3")).build();
	static Map<String, Country>  countryMap = new HashMap<String, Country> ();
	static List<Country> listOfCountries = new ArrayList<Country>();
	static{
		Country indiaCountry = new Country(1, "India", "IN");
		Country chinaCountry = new Country(4, "China", "CH");
		Country nepalCountry = new Country(3, "Nepal", "NP");
		Country bhutanCountry = new Country(2, "Bhutan", "BH");

		listOfCountries.add(indiaCountry);
		listOfCountries.add(chinaCountry);
		listOfCountries.add(nepalCountry);
		listOfCountries.add(bhutanCountry);
		
		for(Country country: listOfCountries){
			countryMap.put(country.getCountryCode(), country);
		}
	}

	private void insertCountry(Country country) {
		countryMap.put(country.getCountryCode(), country);
	}

	private String deleteCountry(String code) {
		if(countryMap.containsKey(code)){
			countryMap.remove(code);
			return "111";
		}
		return "000";
	}
	
	private String updateCountry(String cCode,Country country) {
		if(countryMap.containsKey(cCode)){
			countryMap.put(cCode, country);
			return "111";
		}
		return "000";
	}


}
