package com.udm.health.domain.internal;

import com.udm.health.domain.ws.CreateUserRequest;
import com.udm.health.domain.ws.LoginRequest;



public enum Service {

	CREATE_USER_REQUEST(CreateUserRequest.class),
	LOGIN_USER(LoginRequest.class);

	
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