package com.udm.health.response;

public enum ResponseStatus {

	
	INVALID_LOGIN("INVALID_LOGIN", "Invalid login."),
	SUCCESS("SUCCESS", "Success"),
	FAILURE("UNKNOW_ERROR", "Unknown error.");
	

	private String code;
	private String message;
	
	private ResponseStatus(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
}
