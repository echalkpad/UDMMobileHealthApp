package com.udm.health.response;

import com.udm.health.dao.mapping.WebServiceResponse;
import com.udm.health.domain.ws.LoginResponse;
import com.udm.health.domain.ws.ResponseHeaderType;

public class LoginValidationAdapter implements ResponseAdapter<WebServiceResponse,LoginResponse>{

	@Override
	public boolean shouldAdapt(Object responseIn, Object responseOut) {
		return WebServiceResponse.class == responseIn.getClass() && LoginResponse.class == responseOut.getClass();
	}

	@Override
	public void adaptAndAttach(WebServiceResponse responseIn, LoginResponse responseOut) {
		ResponseHeaderType responseHeader = (ResponseHeaderType)responseOut.ensureResponseHeader();
		responseHeader.setStatusCode(responseIn.getStatus().getCode());
	}

}
