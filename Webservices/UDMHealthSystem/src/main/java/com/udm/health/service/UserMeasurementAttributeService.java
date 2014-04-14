package com.udm.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udm.health.dao.UserMeasurementAttributeDao;
import com.udm.health.domain.hibernate.UserMeasurementAttribute;

@Component
public class UserMeasurementAttributeService {

	@Autowired
	private UserMeasurementAttributeDao dao;
	
	public List<UserMeasurementAttribute> findMeasurementAttributesByUserMeasurementId(Long id){
		return dao.findMeasurementAttributesByUserMeasurementId(id);
	}
}
