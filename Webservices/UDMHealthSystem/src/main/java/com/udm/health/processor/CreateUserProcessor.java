package com.udm.health.processor;

import org.springframework.beans.factory.annotation.Autowired;

import com.udm.health.dao.mapping.CreateUserResponse;
import com.udm.health.domain.hibernate.User;
import com.udm.health.response.ResponseStatus;
import com.udm.health.service.UserService;

public class CreateUserProcessor implements RequestProcessor<User, CreateUserResponse>{

	@Autowired
	private UserService userService;
	
	@Override
	public CreateUserResponse process(User request) {
		userService.createUser(request);
		CreateUserResponse reponse = new CreateUserResponse();
		reponse.setStatus(ResponseStatus.SUCCESS);
		return reponse;
	}
	

}
