package com.udm.health.dao;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.UserMeasument;

@Component
public class UserMeasurementDao extends BaseDao<UserMeasument, Long>{

	public UserMeasurementDao() {
		super(UserMeasument.class);
	}
	

}
