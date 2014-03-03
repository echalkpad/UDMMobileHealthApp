package com.udm.health.dao;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.MeasurementAttribute;
import com.udm.health.domain.hibernate.UserMeasurement;
import com.udm.health.domain.hibernate.UserMeasurementAttribute;

@Component
public class UserMeasurementAttributeDao extends BaseDao<UserMeasurementAttribute, Long>{
	
	protected UserMeasurementAttributeDao() {
		super(UserMeasurementAttribute.class);
	}

	public void saveUserMeasurementAttribute(MeasurementAttribute attribute, UserMeasurement userMeasurement, String value) {
		UserMeasurementAttribute userMeasurementAtt = new UserMeasurementAttribute();
		userMeasurementAtt.setMeasurementAttribute(attribute);
		userMeasurementAtt.setUserMeasument(userMeasurement);
		userMeasurementAtt.setValue(value);
		save(userMeasurementAtt);
	}

}
