package com.telluriac.cpy.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telluriac.cpy.dao.DepartmentDao;
import com.telluriac.cpy.model.Department;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
//	@PostConstruct
//	public void init(){
//		System.out.println(departmentDao == null);
//	}
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public void saveDepartment(Department department) {
		departmentDao.persist(department);
	}

	public void updateDepartment(Department department) {
		departmentDao.update(department);
	}

	public void deleteDepartment(Department department) {
		departmentDao.delete(department);
	}

	public Department findDepartmentById(Integer id) {
		return departmentDao.findById(id);
	}

	public List<Department> listAllDepartments() {
		return departmentDao.listAll();
	}

	public void deleteAllDepartments() {
		departmentDao.deleteAll();
	}

	public void deleteDepartmentById(Integer id) {
		departmentDao.deleteById(id);
	}

}
