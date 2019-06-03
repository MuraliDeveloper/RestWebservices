package com.rest;

import java.util.HashMap;
import java.util.Map;

import com.module.Employee;

public class EmpData {

	static Map<String, Employee> map = new HashMap<String, Employee>();

	static {
		for (int i = 1; i < 10; i++) {
			Employee emp = new Employee();
			emp.setEmployeeId("12121" + i);
			emp.setName("test_NAME_1234" + i);
			emp.setPan("tets_PAN_2123" + i);
			map.put(emp.getEmployeeId(), emp);
		}
	}

}
