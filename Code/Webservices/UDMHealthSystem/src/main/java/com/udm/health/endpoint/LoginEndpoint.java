package com.udm.health.endpoint;

import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import com.udm.health.domain.ws.LoginRequest;
import com.udm.health.domain.ws.LoginResponse;
import com.udm.health.domain.ws.ResponseHeaderType;

@Endpoint
@Component
public class LoginEndpoint extends BaseEndpoint<LoginRequest,LoginResponse>{

	@SoapAction("http://www.udm.com/LoginRequest")
	@ResponsePayload
	public LoginResponse handleRequest(@RequestPayload LoginRequest request, MessageContext messageContext) {
		return super.handleRequest(request, messageContext);
	}
	
	
	@Override
	protected Object processRequest(LoginRequest request) {
		return process(request);
	}
	

	@Override
	protected LoginResponse createResponse() {
		LoginResponse response = new LoginResponse();
		response.setResponseHeader(new ResponseHeaderType());
		return response;
	}

}
