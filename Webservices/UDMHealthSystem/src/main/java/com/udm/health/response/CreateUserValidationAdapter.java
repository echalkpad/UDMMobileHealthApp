package com.udm.health.response;

import com.udm.health.domain.ws.CreateUserResponse;
import com.udm.health.domain.ws.ResponseHeaderType;

public class CreateUserValidationAdapter implements ResponseAdapter<com.udm.health.dao.mapping.CreateUserResponse, CreateUserResponse>{

	@Override
	public boolean shouldAdapt(Object responseIn, Object responseOut) {
		return CreateUserResponse.class == responseIn.getClass() && CreateUserResponse.class == responseOut.getClass();
	}

	@Override
	public void adaptAndAttach(com.udm.health.dao.mapping.CreateUserResponse responseIn, CreateUserResponse responseOut) {
		ResponseHeaderType responseHeader = (ResponseHeaderType)responseOut.ensureResponseHeader();
		responseHeader.setStatusCode(responseIn.getStatus().getCode());
	
	}

}
