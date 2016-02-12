package com.telluriac.cpy.service;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telluriac.cpy.dao.EmployeeDao;
import com.telluriac.cpy.dao.EmployeeEagerDao;
import com.telluriac.cpy.model.Employee;
import com.telluriac.cpy.model.EmployeeEager;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private EmployeeEagerDao employeeEagerDao;

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void saveEmployee(Employee employee) {
		employeeDao.persist(employee);
	}

	public void saveEmployeeEager(EmployeeEager employee) {
		employeeEagerDao.persist(employee);
	}

	public void updateEmployee(Employee employee) {
		employeeDao.update(employee);
	}

	public void deleteEmployee(Employee employee) {
		employeeDao.delete(employee);
	}

	public Employee findEmployeeById(Integer id) {
		return employeeDao.findById(id);
	}

	public EmployeeEager findEmployeeEagerById(Integer id) {
		return employeeEagerDao.findById(id);
	}

	public List<Employee> listAllEmployees() {
		return employeeDao.listAll();
	}

	public void deleteAllEmployees() {
		employeeDao.deleteAll();
	}

	public void deleteEmployeeById(Integer Id) {
		employeeDao.deleteById(Id);
	}

	public List<EmployeeEager> listAllEmployeesEager() {
		return employeeEagerDao.listAll();
	}


}
