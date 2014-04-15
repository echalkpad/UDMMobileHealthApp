package com.udm.health.response;

import com.udm.health.dao.mapping.WebServiceResponse;
import com.udm.health.domain.ws.CreateUserResponse;
import com.udm.health.domain.ws.ResponseHeaderType;

public class CreateUserValidationAdapter implements ResponseAdapter<WebServiceResponse, CreateUserResponse>{

	@Override
	public boolean shouldAdapt(Object responseIn, Object responseOut) {
		return WebServiceResponse.class == responseIn.getClass() && CreateUserResponse.class == responseOut.getClass();
	}

	@Override
	public void adaptAndAttach(WebServiceResponse responseIn, CreateUserResponse responseOut) {
		ResponseHeaderType responseHeader = (ResponseHeaderType)responseOut.ensureResponseHeader();
		responseHeader.setStatusCode(responseIn.getStatus().getCode());
	
	}


}
