package com.udm.health.processor;

import com.udm.health.dao.mapping.CreateUserResponse;
import com.udm.health.domain.ws.CreateUserRequest;
import com.udm.health.response.ResponseStatus;

public class CreateUserProcessor implements RequestProcessor<CreateUserRequest, CreateUserResponse>{

	@Override
	public CreateUserResponse process(CreateUserRequest request) {
		CreateUserResponse reponse = new CreateUserResponse();
		reponse.setStatus(ResponseStatus.SUCCESS);
		return reponse;
	}

}
