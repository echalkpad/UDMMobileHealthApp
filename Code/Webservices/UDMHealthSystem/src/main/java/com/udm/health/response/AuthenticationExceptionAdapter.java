package com.udm.health.response;

import org.springframework.security.core.AuthenticationException;

import com.udm.health.domain.ws.HasResponseHeader;
import com.udm.health.domain.ws.ResponseHeader;

public class AuthenticationExceptionAdapter implements ResponseAdapter<AuthenticationException, HasResponseHeader>  {

	@Override
	public boolean shouldAdapt(Object responseIn, Object responseOut) {
		return AuthenticationException.class.isAssignableFrom(responseIn.getClass()) && HasResponseHeader.class.isAssignableFrom(responseOut.getClass());
	}

	@Override
	public void adaptAndAttach(AuthenticationException responseIn, HasResponseHeader responseOut) {
		ResponseHeader header = responseOut.ensureResponseHeader();
		header.setStatusCode(ResponseStatus.INVALID_LOGIN.toString());
		header.addMessageList(ResponseStatus.INVALID_LOGIN.getMessage());
		
	}

}
