package com.telluriac.cpy.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import com.telluriac.cpy.model.Department;
import com.telluriac.cpy.model.Employee;

@Repository("departmentDao")
public class DepartmentDaoImpl extends AbstractDaoImpl<Integer, Department> implements DepartmentDao {

	// @PostConstruct
	// public void init() {
	// System.out.println("DAO created" + this.getSessionFactory() == null);
	// }

	@Override
	public Department findById(Integer id) {
		Department dept = (Department) getEntityManager().find(Department.class, id);
		if (dept != null) {
			Hibernate.initialize(dept.getEmployees());
			for (Employee emp : dept.getEmployees()) {
				Hibernate.initialize(emp.getProjects());
			}
		}
		return dept;
	}

	public List<Department> listAll() {
		List<Department> deptList = getEntityManager().createNamedQuery("Department.findAll").getResultList();
		for (Department dept : deptList) {
			Hibernate.initialize(dept.getEmployees());
			for (Employee emp : dept.getEmployees()) {
				Hibernate.initialize(emp.getProjects());
			}
		}
		return deptList;
	}

	public void deleteAll() {
		Query query = getEntityManager().createQuery("delete from Department");
		query.executeUpdate();
	}

}
