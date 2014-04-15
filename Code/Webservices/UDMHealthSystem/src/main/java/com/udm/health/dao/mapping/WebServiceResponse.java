package com.udm.health.dao.mapping;

import com.udm.health.response.ResponseStatus;

public class WebServiceResponse {

	private ResponseStatus status;

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus statusCode) {
		status = statusCode;
	} 
	
	
}
