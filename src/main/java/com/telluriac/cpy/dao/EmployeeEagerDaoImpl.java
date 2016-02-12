package com.telluriac.cpy.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.telluriac.cpy.model.EmployeeEager;

@Repository("employeeEagerDao")
public class EmployeeEagerDaoImpl extends AbstractDaoImpl<Integer, EmployeeEager> implements EmployeeEagerDao {

	@Override
	public EmployeeEager findById(Integer id) {

		EmployeeEager emp = (EmployeeEager) getEntityManager().find(EmployeeEager.class, id);
		if (emp != null) {
			Hibernate.initialize(emp.getProjects());
		}
		return emp;
	}

	public List<EmployeeEager> listAll() {
		List<EmployeeEager> empList = getEntityManager().createNamedQuery("EmployeeEager.findAll").getResultList();
		for (EmployeeEager emp : empList) {
			Hibernate.initialize(emp.getProjects());
		}

		return empList;
	}

	public void deleteAll() {
		Query query = getEntityManager().createQuery("delete from Employee");
		query.executeUpdate();
	}

}
