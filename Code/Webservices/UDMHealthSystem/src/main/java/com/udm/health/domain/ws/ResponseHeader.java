package com.udm.health.domain.ws;



public interface ResponseHeader {

	void setStatusCode(String statusCode);
	String getStatusCode();
	void addMessageList(String message);
	Message getMessageList();
	
}
