package com.udm.health.mvc.validation;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class UniqueInTableValidator implements ConstraintValidator<UniqueInTable, String> {

	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<?> entity;
	private String entityField;
	
	@Override
	public void initialize(UniqueInTable constraintAnnotation) {
		entity = constraintAnnotation.entity();
		entityField = constraintAnnotation.entityField(); 
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value)) {
			return true;
		}
		
		String queryString = String.format("select e from %s e where e.%s = :value", entity.getSimpleName(), entityField);
		TypedQuery<?> query = entityManager.createQuery(queryString, entity);
		query.setParameter("value", value);
		try {
			Object result = query.getSingleResult();
			return result == null;
		} catch (NoResultException e) {
			return true;
		}
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	void setEntity(Class<?> entity) {
		this.entity = entity;
	}
	
	void setEntityField(String entityField) {
		this.entityField = entityField;
	}
	
}
