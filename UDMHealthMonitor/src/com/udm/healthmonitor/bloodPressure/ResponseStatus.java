package com.udm.healthmonitor.bloodPressure;

public enum ResponseStatus {
	SUCCESS("SUCCESS"), FAILURE("FAILURE"), UNKNOWN("UNKNOWN");
	
	private ResponseStatus(String value){
		this.value = value;
	}
	
	private String value;
	
	public String toString(){
		return value;
	}
}
