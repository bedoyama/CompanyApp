package com.telluriac.cpy.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@Table(name="department")
@NamedQuery(name="DepartmentEager.findAll", query="SELECT d FROM DepartmentEager d")
public class DepartmentEager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="dept_id", unique=true, nullable=false)
	private Integer deptId;

	@Column(name="dept_email", length=150)
	private String deptEmail;

	@Column(name="dept_name", nullable=false, length=100)
	private String deptName;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="department",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<EmployeeEager> employees;

	public DepartmentEager() {
	}

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptEmail() {
		return this.deptEmail;
	}

	public void setDeptEmail(String deptEmail) {
		this.deptEmail = deptEmail;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<EmployeeEager> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<EmployeeEager> employees) {
		this.employees = employees;
	}

	public EmployeeEager addEmployee(EmployeeEager employee) {
		getEmployees().add(employee);
		employee.setDepartment(this);

		return employee;
	}

	public EmployeeEager removeEmployee(EmployeeEager employee) {
		getEmployees().remove(employee);
		employee.setDepartment(null);

		return employee;
	}

}