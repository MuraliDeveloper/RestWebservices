package com.apache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.module.Employee;
import com.module.EmployeeResponse;


public class ConsumerApacheClientTest {

	
	public  static <T> T getObject(Class<T> clazz,String jsonInString) throws JsonParseException, JsonMappingException, IOException {
		//1.get json
		/*String jsonInString = 
				"{\"name\":\"first name\",\"lastName\":\"last name\",\"age\":28,\"messages\":[\"message_0\",\"message_1\",\"message_2\",\"message_3\",\"message_4\",\"message_5\",\"message_6\",\"message_7\",\"message_8\",\"message_9\"]}";
		*///JSON from String to Object
		
		//2.create obj mapper
		ObjectMapper mapper = new ObjectMapper();
		
		T t= null;
		//3.call method to read json and create obj
		try{
		t= (T)mapper.readValue(jsonInString, clazz);
		}catch(Exception exp){
			System.out.println(exp);
		}
		return t;
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		//getCall();
		postCall();
		/*
		url = ConsmerUtil.BASE_URL + "/updateCountryXML/CH";
		inputData = ConsmerUtil.getInputXML();
		inputFormat = ConsmerUtil.XML;
		outputFormat = ConsmerUtil.XML;
		String responseStr = putRestApache(url,inputFormat, inputData);
		//unmarshal
		System.out.println("put response::"+responseStr);
*/	}

	private static void postCall() throws IOException, JsonGenerationException,
			JsonMappingException, JsonParseException {
		Employee  emp = new  Employee();
		emp.setEmployeeId("9001");
		emp.setName("testuser9001");
		emp.setPan("testPan");
		
		String url = ConsmerUtil.BASE_URL + "/employee";
		
		//convert emp obj to json str
		String inputData = getJsonStr(emp);
		String inputFormat = ConsmerUtil.JSON;

		//make web service call
		String postResponse = postRestApache(url,inputFormat, inputData);
		System.out.println(postResponse);

		//convert json to emp obj
		EmployeeResponse empResObj = getObject(EmployeeResponse.class, postResponse);
		System.out.println(empResObj);
	}

	private static <T> String getJsonStr(T t) throws IOException,
			JsonGenerationException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		String inputData = mapper.writeValueAsString(t);
		return inputData;
	}

	private static void getCall() throws JsonParseException,
			JsonMappingException, IOException {
		String url = ConsmerUtil.BASE_URL + "/employee/getAllEmps";
		String outputFormat = ConsmerUtil.JSON;
	
		String jsonStr = getRestApache(url, outputFormat);
		System.out.println("Json string = "+jsonStr);
		
		EmployeeResponse empResObj = getObject(EmployeeResponse.class, jsonStr);
		System.out.println(empResObj);
	}

	private static String getRestApache(String url,String outputFormat) {
		String getResponse=null;
		DefaultHttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("accept", outputFormat);
			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));
			System.out.println("Output from Server .... \n");
			String output;
			while ((output = br.readLine()) != null) {
				getResponse = output;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
		return getResponse;
	}
	
	private static String postRestApache(String url, String inputFormat,
			String inputStr) {
		String output=null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url);
			StringEntity input = new StringEntity(inputStr);
			input.setContentType(inputFormat);
			postRequest.setEntity(input);
			postRequest.addHeader("accept", inputFormat);
			HttpResponse response = httpClient.execute(postRequest);
			if (response.getStatusLine().getStatusCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));
			
			String output1= null;
			while ((  output1 = br.readLine()) != null) {
				System.out.println(output1);
				output= output1;
			}
			//output="created";
			httpClient.getConnectionManager().shutdown();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	private static String putRestApache(String url, String inputFormat,
			String inputStr) {
		String output=null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut httpPut = new HttpPut(url);
			StringEntity input = new StringEntity(inputStr);
			input.setContentType(inputFormat);
			httpPut.setEntity(input);
			HttpResponse response = httpClient.execute(httpPut);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));
			
			String output1= null;
			while ((  output1 = br.readLine()) != null) {
				output= output1;
			}
			httpClient.getConnectionManager().shutdown();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
}
