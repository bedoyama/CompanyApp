package com.telluriac.cpy.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.telluriac.cpy.model.Department;

public interface DepartmentService {

	void saveDepartment(@WebParam(name = "dept") Department department);

	void updateDepartment(@WebParam(name = "dept") Department department);

	void deleteDepartment(@WebParam(name = "dept") Department department);

	Department findDepartmentById(@WebParam(name = "id") Integer id);

	void deleteDepartmentById(@WebParam(name = "id") Integer id);

	public List<Department> listAllDepartments();

	public void deleteAllDepartments();
}
