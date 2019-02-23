package com.rest;

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
import javax.ws.rs.core.Response.ResponseBuilder;

import com.module.Employee;
import com.module.EmployeeList;


@Path("/myRest")
public class EmpRest {
	
	//http://localhost:8081/RestServer/rest/myRest/hello
	@Path("/hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello(){
		return "hello User welcome to webservices";
	}
	
	//http://localhost:8081/RestServer/rest/myRest/getData?name=xyz&age=23
	//http://localhost:8081/RestServer/rest/myRest/getData?name=xyz&age=12
	//http://localhost:8081/RestServer/rest/myRest/getData
	@Path("/getData")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getValidData(
			@QueryParam("name") String name,
			@QueryParam("age") int age
			){
		if(name==null || age==0){
			return"invalid Input";
		}
		if(age<18){
			return"invalid Age";
		}
		return "valid input";
	}
	
	//http://localhost:8081/RestServer/rest/myRest/getDetails/20002/krishna/11
	@Path("/getDetails/{id}/{name}/{age}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getDetails(
			@PathParam("id") int id,
			@PathParam("name") String name,
			@PathParam("age") int age){
		if(id ==0  || name==null ||  age==0){
			return"invalid Input";
		}
		if(age<18){
			return"invalid Age";
		}
		return "id ="+id +", name= "+name +", age ="+age;
	}
	
	//http://localhost:8081/RestServer/rest/myRest/EmpById/34132
	@Path("/EmpById/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmpById(
			@PathParam("id") int id){
		Employee  emp = new Employee();
		emp.setEmployeeId(""+id);
		emp.setName("test_"+id);
		emp.setPan("Pan_"+id);
		return emp;
	}
	
	//http://localhost:8081/RestServer/rest/myRest/EmpById/34132
	@Path("/EmpById/{id}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(
			@PathParam("id") int id){
		String data="Delete successfull for :::--"+id;
		int statusCode= 204;
		Response responseObj = Response.status(statusCode).entity(data).build();
		return responseObj;
	}
	
	//http://localhost:8081/RestServer/rest/myRest/getEmps
	@Path("/getEmps")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeList getEmps(){
		EmployeeList  list = new EmployeeList();
		list.setUserName("SAP");
		for(int i=1;i<=15;i++){
			Employee  emp = new Employee();
			emp.setEmployeeId(""+2000+i);
			emp.setName("test_"+2000+i);
			emp.setPan("Pan_"+2000+i);
			list.getEmployees().add(emp);
		}
		return list;
	}
	
	@Path("/emp")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createEmp(Employee emp){
		String data="creation  successfull for :::--"+emp;
		int statusCode= 201;
		Response responseObj = Response.status(statusCode).entity(data).build();
		return responseObj;
	}
	
	@Path("/emp")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateEmp(Employee emp){
		String data="update  successfull for :::--"+emp;
		int statusCode= 201;
		Response responseObj = Response.status(statusCode).entity(data).build();
		return responseObj;
	}
	
}
