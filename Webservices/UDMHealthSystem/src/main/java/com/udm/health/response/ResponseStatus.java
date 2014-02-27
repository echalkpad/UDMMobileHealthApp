package com.udm.health.response;

public enum ResponseStatus {

	
	INVALID_LOGIN("INVALID_LOGIN", "Invalid login."),
	SUCCESS("SUCCESS", "Success"),
	INVALID_BRAND("INVALID BRAND","Invalid Brand"),
	INVALID_SOURCE("3","Invalid Source"),
	FAILURE("4", "Unknown error.");
	

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
