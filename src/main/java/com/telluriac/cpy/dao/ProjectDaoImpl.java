package com.telluriac.cpy.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.telluriac.cpy.model.Project;

@Repository("projectDao")
public class ProjectDaoImpl extends AbstractDaoImpl<Integer, Project> implements ProjectDao {

	@Override
	public List<Project> listAll() {
		List<Project> projList = getEntityManager().createNamedQuery("Project.findAll").getResultList();
		return projList;
	}

	@Override
	public void deleteAll() {
		Query query = getEntityManager().createQuery("delete from Project");
		query.executeUpdate();
	}

}
