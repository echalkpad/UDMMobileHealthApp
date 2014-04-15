package com.udm.health.domain.internal;

import com.udm.health.domain.ws.CreateUserRequest;
import com.udm.health.domain.ws.LoginRequest;
import com.udm.health.domain.ws.SendBloodPressureMeasurementRequest;
import com.udm.health.domain.ws.TemperatureMeasurementRequest;
import com.udm.health.domain.ws.BloodSugarMeasurementRequest;



public enum Service {

	CREATE_USER_REQUEST(CreateUserRequest.class),
	LOGIN_USER(LoginRequest.class),
	SEND_BLOOD_PRESSURE(SendBloodPressureMeasurementRequest.class),
	TEMPERATURE_MEASUREMENT(TemperatureMeasurementRequest.class),
	BLOOD_SUGAR_MEASUREMENT(BloodSugarMeasurementRequest.class);

	
	private Class<?> requestClass;
	
	private Service(Class<?> requestClass) {
		this.requestClass = requestClass;
	}
	
	public static Service lookupServiceFor(Class<?> clazz) {
		for (Service service : values()) {
			if (service.requestClass == clazz) {
				return service;
			}
		}
		throw new IllegalArgumentException("No service found for " + clazz);
	}
	
	
} 