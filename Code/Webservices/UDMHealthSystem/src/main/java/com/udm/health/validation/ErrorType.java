package com.udm.health.validation;

public enum ErrorType {

	NULL("Field can not be null"), BLANK("Field can not be blank"), EMPTY("Field can not be empty"), SIZE("Field size error"), INVALID("Field invalid"), NOT_A_NUMBER("Filed is not a Number");
	
	private String errorDescription;
	
	private ErrorType(String errorDescription){
		this.errorDescription = errorDescription;
	}
	
	public String getErrorDescription(){
		return errorDescription;
	}
}
