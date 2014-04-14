package com.udm.health.dao;

import java.util.List;

import javax.persistence.TypedQuery;

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
	
	
	public List<UserMeasurementAttribute> findMeasurementAttributesByUserMeasurementId(Long userMeasurementId){
		TypedQuery<UserMeasurementAttribute> query = entityManager.createQuery("SELECT m FROM UserMeasurementAttribute m WHERE m.userMeasument.id = :id", UserMeasurementAttribute.class);
		query.setParameter("id", userMeasurementId);
		return query.getResultList();
		
	}

}
