package com.telluriac.cpy.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDaoImpl<K extends Serializable, E> implements AbstractDao<K, E> {
	protected Class<E> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public AbstractDaoImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}
	
	public void persist(E entity) {
		entityManager.persist(entity);
	}

	public void update(E entity) {
		entityManager.merge(entity);
	}

	public void delete(E entity) {
		entityManager.remove(entity);
	}

	public void deleteById(K id) {
		E entity = findById(id);
		entityManager.remove(entity);
	}

	public E findById(K id) {
		E entity = entityManager.find(entityClass, id);
		return entity;
	}
}