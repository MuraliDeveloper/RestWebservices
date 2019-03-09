package com.apache;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.jackson.map.ObjectMapper;

import com.module.CountryInfo;
import com.module.Employee;

public class ConsmerUtil {

	public static String BASE_URL = "http://localhost:8082/RestServer/rest";
	public static String JSON = "application/json";
	public static String XML = "application/xml";
	public static String TEXT = "text/plain";
	
	public static String getInputXML() {
		return "<country>" + "<countryCode>CH</countryCode>"
				+ "<countryName>China2</countryName>"
				+ "<id>98765</id>" + "</country>";
	}
	
	public static String getSampleJson() {
		return "{\"name\": \"MURALIDHAR REDDY\",\"employeeId\": \"10090\",\"pan\":\"xyz\"}";
	}


	public static Employee getJsonObjectForEmployee(String jsonInString){
		//2.create obj mapper
		ObjectMapper mapper = new ObjectMapper();
		
		//3.call method to read json and create obj
		Employee employee = null;
		try{
		employee= (Employee)mapper.readValue(jsonInString, Employee.class);
		}catch(Exception exp){
			System.out.println(exp);
		}
		return employee;	
	}
	
	public static CountryInfo getJaxbObjectForEmployeeInfo(String xmlString) {
		StringReader stringReader = new StringReader(xmlString);
		JAXBContext jaxbContext = null;
		Unmarshaller unmarshaller = null;
		CountryInfo employeeInfo = null;
		try {
			jaxbContext = JAXBContext.newInstance(CountryInfo.class);

			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader xsr;
			xsr = xif.createXMLStreamReader(stringReader);

			unmarshaller = jaxbContext.createUnmarshaller();
			employeeInfo = (CountryInfo) unmarshaller.unmarshal(xsr);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeInfo;
	}
}
