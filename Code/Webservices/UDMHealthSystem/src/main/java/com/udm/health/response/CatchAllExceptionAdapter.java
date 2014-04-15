package com.udm.health.response;

import com.udm.health.domain.ws.HasResponseHeader;
import com.udm.health.domain.ws.ResponseHeader;

public class CatchAllExceptionAdapter implements ResponseAdapter<Exception, HasResponseHeader> {

	@Override
	public boolean shouldAdapt(Object responseIn, Object responseOut) {
		return Exception.class.isAssignableFrom(responseIn.getClass()) && HasResponseHeader.class.isAssignableFrom(responseOut.getClass());
	}

	@Override
	public void adaptAndAttach(Exception responseIn,HasResponseHeader responseOut) {
		
		ResponseHeader header = responseOut.ensureResponseHeader();
		header.setStatusCode(ResponseStatus.FAILURE.toString());
		header.addMessageList(ResponseStatus.FAILURE.getMessage());
	}

}
