package com.udm.health.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.udm.health.domain.hibernate.RequestLog;
import com.udm.health.domain.internal.SortOrder;

public abstract class BaseDao<T,K> {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	private Class<T> entityClass;
	
	protected BaseDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Transactional
	public void save(T entity) {
		entityManager.persist(entity);
	}
	
	@Transactional
	public T update(T entity) {
		return entityManager.merge(entity);
	}
	
	public T findById(K id) {
		return entityManager.find(entityClass, id);
	}
	
	public List<T> findAll() {
		TypedQuery<T> query = entityManager.createQuery(String.format("select e from %s e", entityClass.getSimpleName()), entityClass);
		return query.getResultList();
	}
	
	public List<RequestLog> findAll(int start, int pageSize) {
		TypedQuery<RequestLog> query = entityManager.createQuery(String.format("select e from %s e", entityClass.getSimpleName()), RequestLog.class);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	public List<RequestLog> findAll(int start, int pageSize, String sortField, SortOrder sortOrder) {
		TypedQuery<RequestLog> query = entityManager.createQuery(String.format("select e from %s e order by e.%s %s", entityClass.getSimpleName(), sortField, sortOrder.toString()), RequestLog.class);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	public List<RequestLog> find(String requestLogQuery, int start, int pageSize) {
		TypedQuery<RequestLog> query = entityManager.createQuery(requestLogQuery, RequestLog.class);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	
	@Transactional
	public void delete(T entity) {
		entityManager.remove(entity);
	}
	
	public long recordCount() {
		TypedQuery<Long> query = entityManager.createQuery(String.format("select count(e) from %s e", entityClass.getSimpleName()), Long.class);
		return query.getSingleResult();
	}
	
	public long recordCount(String queryCount) {
		TypedQuery<Long> query = entityManager.createQuery(queryCount, Long.class);
		return query.getSingleResult();
	}
	
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
