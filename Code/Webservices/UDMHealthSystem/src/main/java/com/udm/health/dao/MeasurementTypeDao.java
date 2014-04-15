package com.udm.health.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.MeasurementType;

@Component
public class MeasurementTypeDao extends BaseDao<MeasurementType, Long>{

	public MeasurementTypeDao() {
		super(MeasurementType.class);
	}
	
	public MeasurementType findByCode(String code){
		Query query = entityManager.createQuery("SELECT mt FROM MeasurementType mt WHERE mt.code = :code");
		query.setParameter("code", code);
		
		try {
			return (MeasurementType) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
