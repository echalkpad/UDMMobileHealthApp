package com.udm.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udm.health.dao.UserMeasurementDao;
import com.udm.health.domain.hibernate.UserMeasurement;

@Component
public class UserMeasurementService {

	@Autowired
	private UserMeasurementDao userMeasurementDao;
	
	public List<UserMeasurement> getUserMeasurementByEmail(String email){
		return userMeasurementDao.findUserMeasurementByEmail(email);				
	}
	
	public int recordCount(String email){
		return Long.valueOf(userMeasurementDao.recordCount(email)).intValue();				
	}
}
