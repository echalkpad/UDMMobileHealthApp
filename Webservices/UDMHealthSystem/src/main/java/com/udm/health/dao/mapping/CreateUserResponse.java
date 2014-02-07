package com.udm.health.dao.mapping;

import com.udm.health.response.ResponseStatus;

public class CreateUserResponse {

	private ResponseStatus status;

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus statusCode) {
		if(statusCode.equals(ResponseStatus.INVALID_BRAND.getCode())){
			status = ResponseStatus.INVALID_BRAND;
		}else if(statusCode.equals(ResponseStatus.INVALID_SOURCE.getCode())){
			status = ResponseStatus.INVALID_SOURCE;
		}else if(statusCode.equals(ResponseStatus.FAILURE.getCode())){
			status = ResponseStatus.FAILURE;
		}else if(statusCode.equals(ResponseStatus.SUCCESS.getCode())){
			status = ResponseStatus.SUCCESS;
		}
	} 
	
	
}
