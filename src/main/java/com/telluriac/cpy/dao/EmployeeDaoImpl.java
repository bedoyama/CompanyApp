package com.telluriac.cpy.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.telluriac.cpy.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDaoImpl<Integer, Employee> implements EmployeeDao {

	@Override
	public Employee findById(Integer id) {
		
		Employee emp = (Employee) getEntityManager().find(Employee.class, id);
		Hibernate.initialize(emp.getProjects());
		return emp;
	}
	
	public List<Employee> listAll() {
		List<Employee> empList = getEntityManager().createNamedQuery("Employee.findAll").getResultList();
		for (Employee emp : empList) {
			Hibernate.initialize(emp.getProjects());
		}

		return empList;
	}

	public void deleteAll() {
		Query query = getEntityManager().createQuery("delete from Employee");
		query.executeUpdate();
	}

}
