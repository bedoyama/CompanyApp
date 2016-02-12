package com.telluriac.cpy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telluriac.cpy.dao.ProjectDao;
import com.telluriac.cpy.model.Project;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void saveProject(Project project) {
		projectDao.persist(project);
	}

	public void updateProject(Project project) {
		projectDao.update(project);
	}

	public void deleteProject(Project project) {
		projectDao.delete(project);
	}

	public Project findProjectById(Integer id) {
		return projectDao.findById(id);
	}

	public List<Project> listAllProjects() {
		return projectDao.listAll();
	}

	public void deleteAllProjects() {
		projectDao.deleteAll();
	}

	public void deleteProjectById(Integer Id) {
		projectDao.deleteById(Id);
	}

}
