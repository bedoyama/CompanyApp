package com.telluriac.cpy.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.telluriac.cpy.model.Employee;
import com.telluriac.cpy.model.EmployeeEager;

public interface EmployeeService {
	void saveEmployee(@WebParam(name = "emp") Employee employee);

	void saveEmployeeEager(@WebParam(name = "emp") EmployeeEager employee);

	void updateEmployee(@WebParam(name = "emp") Employee employee);

	void deleteEmployee(@WebParam(name = "emp") Employee employee);

	void deleteEmployeeById(Integer Id);

	Employee findEmployeeById(@WebParam(name = "id") Integer id);
	
	EmployeeEager findEmployeeEagerById(Integer id);

	public List<Employee> listAllEmployees();

	public List<EmployeeEager> listAllEmployeesEager();

	public void deleteAllEmployees();
}
