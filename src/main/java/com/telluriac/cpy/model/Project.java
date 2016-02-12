package com.telluriac.cpy.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@Table(name="project")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="project_id", unique=true, nullable=false)
	private Integer projectId;

	@Column(name="project_location", length=100)
	private String projectLocation;

	@Column(name="project_name", nullable=false, length=100)
	private String projectName;

	//bi-directional many-to-many association to Employee
	@ManyToMany(mappedBy="projects")
	@JsonIgnore
	private List<Employee> employees;

	public Project() {
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectLocation() {
		return this.projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}