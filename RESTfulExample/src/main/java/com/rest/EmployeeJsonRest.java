package com.rest;

import java.io.File;
import java.io.InputStream;
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
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.module.Employee;
import com.module.EmployeeResponse;
import com.util.ServiceException;


@Path("/employee")
public class EmployeeJsonRest {
	//http://localhost:8081/RestServer/rest/employee/form
	private static final String SUCCESS_CODE = "1111";
	
	@Context
	UriInfo uriInfo;
	
	//http://localhost:8082/RestServer/rest/employee/getAllEmps
	@Path("/getAllEmps")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeResponse getAllEmps(){
		
		//	1.get emp list
		List<Employee> list = new ArrayList<Employee>();
		Set<Entry<String, Employee>> entrySet = map.entrySet();		
		for(Entry<String, Employee> entry:  entrySet){
		list.add(entry.getValue());
		}
		
		//2. prepare response
		EmployeeResponse response = new EmployeeResponse();
		response.setAppStatus(SUCCESS_CODE);
		response.setEmps(list);
		response.setStatusMsg("SUCCESS");
		return response;
	}
 	
	public boolean isEmpty(String str){
		return str==null || str.equals("");
	}
	
	/*METHOD : POST
	 * URL : http://localhost:8082/RestServer/rest/employee
	  Headers :
	               Accept application/json
	               Content-Type : application/json
	 Request Body:           
	 {
         "name": "muralidhar",
         "pan": "aggvgavaa",
         "employeeId": "1000"
     }*/
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEmployesJSON(Employee emp
			,@Context UriInfo uriInfo
			) throws ServiceException, URISyntaxException{
		validatePayload(emp, Status.BAD_REQUEST);
		
		EmployeeResponse response = new EmployeeResponse();
		int status= 201;
		if(isExistingEmployee(emp.getEmployeeId())){
			throw new ServiceException(
					Status.BAD_REQUEST, "ERRO2","Employee id Already exists!!");
			/*response.setAppStatus("ERRO2");
			status =400;
			response.setStatusMsg("Employee already exist");*/
		}else{
			saveEmp(emp);
			response.setAppStatus(SUCCESS_CODE);
			response.setStatusMsg("SUCCESS");
		}
		
		// create location url
		URI absolutePath = uriInfo.getAbsolutePath();
		String path = absolutePath.toString() + "/"+emp.getEmployeeId();
		
		// add to the response object
		ResponseBuilder entity = Response.created(new URI(path)).entity(response);
		entity.header("myHeader", "test"+emp.getEmployeeId());
		//ResponseBuilder entity = Response.status(status).entity(response);
		return entity.build();
	}

	private void validatePayload(Employee emp, Status status) throws ServiceException {
		if(isEmpty(emp.getEmployeeId()) || 
				isEmpty(emp.getName())||
				isEmpty(emp.getPan())){
			throw new ServiceException(
					status, "ERRO3","Request payload have empty values..");
			
		}
	}

	private boolean isExistingEmployee(String id) {
		return map.containsKey(id);
	}

	/* METHOD : PUT 
	 * URL : http://localhost:8082/RestServer/rest/employee/1000
	  Headers :
	               Accept application/json
	               Content-Type : application/json
	 Request Body:           
	 {
       "name": "muralidhar",
       "pan": "aggvgavaa",
       "employeeId": "1000"
   }*/
	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployesJSON(
			@PathParam("id") int id,
			Employee emp) throws ServiceException{
		validatePayload(emp,Status.FORBIDDEN);
		EmployeeResponse response = new EmployeeResponse();
		response.setAppStatus(SUCCESS_CODE);
		response.setStatusMsg("SUCCESS");
		int status= 201;
		if(!isExistingEmployee(""+id)){
			response.setAppStatus("ERRO2");
			status = 400;
			response.setStatusMsg("Employee id doesnot exists!!");
		}else{
			saveEmp(emp);
		}
		return Response.status(status).entity(response).build();
	}
	
	//URL : http://localhost:8082/RestServer/rest/employee/1000
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmpById(
			@PathParam("id") int empId){
		//get emp obj from service 
		Employee employee = map.get(""+empId);
		EmployeeResponse response = new EmployeeResponse();
		int status = 200;
		if(employee==null){
			status =  400;
			response.setAppStatus("ERRO1");
			response.setStatusMsg("Employee Id not found");
		}else{
			response.getEmps().add(employee);
			response.setStatusMsg("SUCCESS");
			response.setAppStatus(SUCCESS_CODE);
		}
		return Response.status(status).entity(response).build();
	}
	//URL : http://localhost:8082/RestServer/rest/employee/1000
	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response  delete(@PathParam("id") int empId){
		Employee employee = map.get(""+empId);
		EmployeeResponse response = new EmployeeResponse();
		if(employee==null){
			response.setAppStatus("ERRO1");
			response.setStatusMsg("Employee Id not found");
		}else{
			map.remove(""+empId);
			response.setStatusMsg("SUCCESS");
			response.setAppStatus(SUCCESS_CODE);
		}
		return Response.status(204).entity(response).build();
	}
	
	// http://localhost:8082/RestServer/rest/employee/form3?name=xyz
	/*@Path("/form3")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String form3(@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws ServletException, IOException{
		return "data== "+ request.getParameter("name");
	}*/
	
	/*@Path("/form")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void getFormData(@FormParam("id") int id,
			@FormParam("name") String name,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("response.jsp").forward(request, response);
	}*/
	
/*	@Path("/form1")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void getFormData1(MultivaluedMap<String, String> map,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("response.jsp").forward(request, response);
	}*/
	
	@GET
    @Path("/txt")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getText() {
		String fileName = "1.txt";
		String path ="C:\\Murali\\Mythri Progs\\webservices\\delete\\"+fileName;
        return buildResponse(fileName, path);
    }
	
	@GET
    @Path("/img")
    @Produces("image/jpg")
    public Response getImage() {
		String fileName = "1.jpg";
		String path ="C:\\Murali\\Mythri Progs\\webservices\\delete\\"+fileName;
        return buildResponse(fileName, path);
    }

	@GET
    @Path("/pdf")
    @Produces("application/pdf")
    public Response getPdf() {
		String fileName = "1.pdf";
		String path ="C:\\Murali\\Mythri Progs\\webservices\\delete\\"+fileName;
        return buildResponse(fileName, path);
    }
	
	private Response buildResponse(String fileName, String path) {
		File file = new File(path);
        ResponseBuilder rb = Response.ok((Object) file);
        rb.header("Content-Disposition","attachment; filename="+fileName);
        return rb.build();
	}

	static Map<String, Employee> map = new HashMap<String, Employee>();
	
	private static void saveEmp(Employee emp){
		map.put(emp.getEmployeeId(),emp );
	}
	
	static{
		Employee emp = new Employee();
		emp.setEmployeeId("12121");
		emp.setName("test_NAME_1234");
		emp.setPan("tets_PAN_2123");
		map.put(emp.getEmployeeId(),emp );
	}
	
	@Path("/header")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHeaderValue(
			@HeaderParam("clientId") String clientId,
			@HeaderParam("clientPass") String clientPass
			){
		return clientId.equals(clientPass )? "Valid client" :"Invalid Client";
	}
	
	/*@Path("/multiQuery")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String multiQuery(@ RequestQuery reqObj){
		return ;
	}*/

	@Path("/input")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEmployesinput(InputStream io
			,@Context UriInfo uriInfo
			) throws ServiceException, URISyntaxException
			{
		/*String json = IOUtils.toString(stream, Charsets.UTF_8);
		if(StringUtils.isEmpty(json)){
			LOG.error("json string is empty: {}",json);
			throw new BaseClientException(Response.Status.BAD_REQUEST, DataConstants.APPIOT_EMPTY_PAYLOAD);
		}*/
		
		Employee emp = new Employee();
		EmployeeResponse response = new EmployeeResponse();
		int status= 201;
		if(isExistingEmployee(emp.getEmployeeId())){
			throw new ServiceException(
					Status.BAD_REQUEST, "ERRO2","Employee id Already exists!!");
			/*response.setAppStatus("ERRO2");
			status =400;
			response.setStatusMsg("Employee already exist");*/
		}else{
			saveEmp(emp);
			response.setAppStatus(SUCCESS_CODE);
			response.setStatusMsg("SUCCESS");
		}
		
		// create location url
		URI absolutePath = uriInfo.getAbsolutePath();
		String path = absolutePath.toString() + "/"+emp.getEmployeeId();
		
		// add to the response object
		ResponseBuilder entity = Response.created(new URI(path)).entity(response);
		entity.header("myHeader", "test"+emp.getEmployeeId());
		//ResponseBuilder entity = Response.status(status).entity(response);
		return entity.build();
	}
}
