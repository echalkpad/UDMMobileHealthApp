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
import com.udm.health.domain.ws.TemperatureMeasurementRequest;

@Component
public class TemperatureMeasurementService {

	@Autowired
	private MeasurementTypeDao measurementTypeDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserMeasurementDao userMeasurementDao;

	@Autowired
	private UserMeasurementAttributeDao userMeasurementAttDao;

	@Autowired
	private MeasurementAttributeDao measurementAttributeDao;

	private MeasurementType getMeasurementType(String mesarumentType) {
		return measurementTypeDao.findByCode(mesarumentType);
	}

	private MeasurementAttribute getTemperatureAttribute(String temperatureCode) {
		return measurementAttributeDao.findByAttributeNameAndMeasurementTypeCode(temperatureCode, temperatureCode);
	}

	public boolean saveTemperatureMesarument(TemperatureMeasurementRequest request) {
		UserMeasurement userMeasurement = saveUserMeasurement(request);
		boolean status = false;
		if (userMeasurement != null) {
			userMeasurementAttDao.saveUserMeasurementAttribute(getTemperatureAttribute(request.getScale()), userMeasurement,	request.getValue());
			status = true;
		}
		return status;
	}

	private UserMeasurement saveUserMeasurement(TemperatureMeasurementRequest request) {
		UserMeasurement userMeasurement = null;
		User user = userDao.findUserByEmail(request.getEmail());
		if (user != null) {
			userMeasurement = userMeasurementDao.saveUserMeasurement(user, request.getMeasurementDate(), request.getMeasurementTime(), getMeasurementType(request.getScale()));
		}
		return userMeasurement;
	}

}
