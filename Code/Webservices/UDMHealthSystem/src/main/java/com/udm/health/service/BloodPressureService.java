package com.udm.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udm.health.dao.MeasurementAttributeDao;
import com.udm.health.dao.MeasurementTypeDao;
import com.udm.health.dao.UserDao;
import com.udm.health.dao.UserMeasurementAttributeDao;
import com.udm.health.dao.UserMeasurementDao;
import com.udm.health.domain.hibernate.MeasurementAttribute;
import com.udm.health.domain.hibernate.MeasurementType;
import com.udm.health.domain.hibernate.User;
import com.udm.health.domain.hibernate.UserMeasurement;
import com.udm.health.domain.ws.SendBloodPressureMeasurementRequest;

@Component
public class BloodPressureService {

	private static final String BLOOD_PRESSURE_CODE = "BLOOD_PRESSURE";
	private static final String SYSTOLIC_NAME = "SYSTOLIC";
	private static final String DIASTOLIC_NAME = "DIASTOLIC";
	

	private MeasurementType bloodPressureType;
	private MeasurementAttribute systolicAttribute;
	private MeasurementAttribute diastolicAttribute;

	@Autowired
	private MeasurementTypeDao measurementTypeDao;

	@Autowired
	private UserMeasurementDao userMeasurementDao;
	
	@Autowired
	private MeasurementAttributeDao measurementAttributeDao;

	@Autowired
	private UserMeasurementAttributeDao userMeasurementAttDao;
	
	@Autowired
	private UserDao userDao;

	private MeasurementType getBloodPressureType() {
		if (bloodPressureType == null) {
			bloodPressureType = measurementTypeDao.findByCode(BLOOD_PRESSURE_CODE);
		}
		return bloodPressureType;
	}
	
	private MeasurementAttribute getSystolicAttribute(){
		if(systolicAttribute == null){
			systolicAttribute = measurementAttributeDao.findByAttributeNameAndMeasurementTypeCode(SYSTOLIC_NAME, BLOOD_PRESSURE_CODE);
		}
		return systolicAttribute;
	}
	
	private MeasurementAttribute getDiastolicAttribute(){
		if(diastolicAttribute == null){
			diastolicAttribute = measurementAttributeDao.findByAttributeNameAndMeasurementTypeCode(DIASTOLIC_NAME, BLOOD_PRESSURE_CODE);
		}
		
		return diastolicAttribute;
	}

	

	public boolean saveBloodPressureRequest(SendBloodPressureMeasurementRequest request){
		UserMeasurement userMeasurement = saveUserMeasurement(request);
		boolean status = false;
		if(userMeasurement != null){
			userMeasurementAttDao.saveUserMeasurementAttribute(getSystolicAttribute(), userMeasurement, request.getSystolic());
			userMeasurementAttDao.saveUserMeasurementAttribute(getDiastolicAttribute(), userMeasurement, request.getDiastolic());
			status = true;
		}

		return status;
	}


	private UserMeasurement saveUserMeasurement(SendBloodPressureMeasurementRequest request) {
		UserMeasurement userMeasurement = null;
		User user = userDao.findUserByEmail(request.getEmail());
		if(user != null) {
			userMeasurement = userMeasurementDao.saveUserMeasurement(user, request.getMeasurementDate(), request.getMeasurementTime(), getBloodPressureType());
		}
		return userMeasurement;
	}
}
