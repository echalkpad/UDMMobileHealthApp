package com.udm.health.endpoint;

import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import com.udm.health.domain.ws.CreateUserRequest;
import com.udm.health.domain.ws.CreateUserResponse;

@Endpoint
@Component
public class CreateUserEndpoint extends BaseEndpoint<CreateUserRequest, CreateUserResponse>{

	@SoapAction("http://www.udm.com/CreateUserRequest")
	@ResponsePayload
	public CreateUserResponse handleRequest(@RequestPayload CreateUserRequest request, MessageContext messageContext) {
		return super.handleRequest(request, messageContext);
	}
	
	
	@Override
	protected Object processRequest(CreateUserRequest request) {
	
		return process(request);
	}

	@Override
	protected CreateUserResponse createResponse() {
		return new CreateUserResponse();
	}

}
