package com.udm.health.dao;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.MeasurementType;

@Component
public class MeasurementTypeDao extends BaseDao<MeasurementType, Long>{

	public MeasurementTypeDao() {
		super(MeasurementType.class);
	}
	
	public MeasurementType findBy

}
