package com.udm.health.processor;

import org.springframework.beans.factory.annotation.Autowired;

import com.udm.health.dao.mapping.WebServiceResponse;
import com.udm.health.domain.ws.LoginRequest;
import com.udm.health.response.ResponseStatus;
import com.udm.health.service.UserService;

public class LoginProcessor implements RequestProcessor<LoginRequest, WebServiceResponse>{

	@Autowired
	private UserService userService;
	
	@Override
	public WebServiceResponse process(LoginRequest request) {
		WebServiceResponse response = new WebServiceResponse();
		if(userService.authenticateUser(request.getEmail(), request.getPassword()))
			response.setStatus(ResponseStatus.SUCCESS);
		else
			response.setStatus(ResponseStatus.INVALID_LOGIN);		

		return response;
	}

}
