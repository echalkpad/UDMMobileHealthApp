package com.udm.health.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.MeasurementAttribute;

@Component
public class MeasurementAttributeDao extends BaseDao<MeasurementAttribute, Long>{

	public MeasurementAttributeDao() {
		super(MeasurementAttribute.class);
	}
	
	public MeasurementAttribute findByAttributeNameAndMeasurementTypeCode(String name, String code){
		Query query = entityManager.createQuery("SELECT ma FROM MeasurementAttribute ma INNER JOIN  ma.measurementType AS mt " +
												"WHERE mt.code = :code AND ma.name = :name");
		query.setParameter("code", code);
		query.setParameter("name", name);
		
		try {
			return (MeasurementAttribute) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
