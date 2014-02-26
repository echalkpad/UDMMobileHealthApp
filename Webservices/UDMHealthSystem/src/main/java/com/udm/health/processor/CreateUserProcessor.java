package com.udm.health.processor;

import org.springframework.beans.factory.annotation.Autowired;

import com.udm.health.dao.mapping.WebServiceResponse;
import com.udm.health.domain.internal.User;
import com.udm.health.response.ResponseStatus;
import com.udm.health.service.UserService;

public class CreateUserProcessor implements RequestProcessor<User, WebServiceResponse>{

	@Autowired
	private UserService userService;
	
	@Override
	public WebServiceResponse process(User request) {
		WebServiceResponse reponse = new WebServiceResponse();
		try{
			userService.createUser(request);	
			reponse.setStatus(ResponseStatus.SUCCESS);
		}catch(Exception e){
			reponse.setStatus(ResponseStatus.FAILURE);
		}
		return reponse;
	}
	

}
