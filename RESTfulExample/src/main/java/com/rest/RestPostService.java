package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.module.CountryInfo;
import com.module.CountryList;
import com.module.EmployeeInfo;
import com.module.EmployeeList;

@Path("/post")
public class RestPostService {
	
	//req with composition
	@POST
	@Path("/addEmp")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createCountry(CountryInfo countryInfo) {
		/*
		 <countryInfo>
		<count>12</count>
		<country>
		   <countryCode>IN</countryCode>
	        <countryName>India</countryName>
	      	<id>1</id>
	      </country>
			</countryInfo>
			*/
		String result = "Employee saved : " + countryInfo;
		return Response.status(201).entity(result).build();
	}
	
	//req with list
	@POST
	@Path("/addEmps")
	@Consumes(MediaType.APPLICATION_XML)
	public Response createCountries(CountryList countryList) {
		/*
		<countryList>
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
		String result = "Employee saved : " + countryList;
		return Response.status(201).entity(result).build();
	}
	
	
	//req with composition
	@POST
	@Path("/addEmpJSON")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployeeJSON(EmployeeInfo employeeInfo) {
		/*
		  {
			 "userName":"12",
			 "employee":
			 {
			    "name": "IN",
			    "employeeId": "India"
			  }
			 }
			*/
		String result = "Employee saved : " + employeeInfo;
		return Response.status(201).entity(result).build();
	}
	
	
	//req with list
		@POST
		@Path("/addEmpsJSON")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response createEmployeesJSON(EmployeeList employeeList) {
			/*
			 {
				 "userName":"12",
				 "employees":
				 [{
				    "name": "IN",
				    "employeeId": "India"
				  }
				  ,
				  {
				    "name": "PK",
				    "employeeId": "PAK"
				  }
				  ]
				 }
				*/
			String result = "Employee saved : " + employeeList;
			return Response.status(201).entity(result).build();
		}
		
}
