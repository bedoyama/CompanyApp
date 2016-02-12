package com.telluriac.cpy.service;

import java.util.List;

import javax.jws.WebParam;

import com.telluriac.cpy.model.Project;

public interface ProjectService {
	void saveProject(@WebParam(name = "prj") Project project);

	void updateProject(@WebParam(name = "prj") Project project);

	void deleteProject(@WebParam(name = "prj") Project project);

	void deleteProjectById(Integer Id);

	Project findProjectById(@WebParam(name = "id") Integer id);

	public List<Project> listAllProjects();

	public void deleteAllProjects();
}
