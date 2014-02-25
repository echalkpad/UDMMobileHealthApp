package com.udm.health.endpoint;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import com.udm.health.domain.hibernate.User;
import com.udm.health.domain.ws.CreateUserRequest;
import com.udm.health.domain.ws.CreateUserResponse;
import com.udm.health.domain.ws.ResponseHeaderType;

@Endpoint
@Component
public class CreateUserEndpoint extends BaseEndpoint<CreateUserRequest, CreateUserResponse>{

	@Autowired
	private DozerBeanMapper mapper;
	
	@SoapAction("http://www.udm.com/CreateUserRequest")
	@ResponsePayload
	public CreateUserResponse handleRequest(@RequestPayload CreateUserRequest request, MessageContext messageContext) {
		return super.handleRequest(request, messageContext);
	}
	
	
	@Override
	protected Object processRequest(CreateUserRequest request) {
	
		User user = mapper.map(request, User.class);
		return process(user);
	}

	@Override
	protected CreateUserResponse createResponse() {
		CreateUserResponse response = new CreateUserResponse();
		response.setResponseHeader(new ResponseHeaderType());
		return response;
	}

}
