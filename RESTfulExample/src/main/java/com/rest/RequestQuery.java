package com.rest;

import javax.ws.rs.QueryParam;

public class RequestQuery {

	private @QueryParam("id") String id;
	private @QueryParam("name") String name;
	private @QueryParam("age") String age;
	private @QueryParam("dept") String dept;
	private @QueryParam("pan") String pan;

	@Override
	public String toString() {
		return "RequestQuery [id=" + id + ", name=" + name + ", age=" + age
				+ ", dept=" + dept + ", pan=" + pan + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
}
