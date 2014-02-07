package com.udm.health.response;

public enum ResponseStatus {

	
	INVALID_LOGIN("0", "Invalid login."),
	SUCCESS("1", "Success"),
	INVALID_BRAND("2","Invalid Brand"),
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
