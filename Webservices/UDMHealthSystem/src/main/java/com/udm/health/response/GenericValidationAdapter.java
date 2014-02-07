package com.udm.health.response;

import com.udm.health.domain.ws.HasResponseHeader;
import com.udm.health.domain.ws.ResponseHeaderType;
import com.udm.health.validation.ValidationResults;

public class GenericValidationAdapter implements ResponseAdapter<ValidationResults, HasResponseHeader> {

	@Override
	public boolean shouldAdapt(Object responseIn, Object responseOut) {
		return ValidationResults.class == responseIn.getClass() && HasResponseHeader.class.isAssignableFrom(responseOut.getClass());
	}

	@Override
	public void adaptAndAttach(ValidationResults responseIn, HasResponseHeader responseOut) {
		ResponseHeaderType responseHeader = (ResponseHeaderType)responseOut.ensureResponseHeader();
		
		responseHeader.setStatusCode(ResponseStatus.FAILURE.toString());
		addErrorMessages(responseIn, responseHeader);
		
		
	}

	private void addErrorMessages(ValidationResults results, ResponseHeaderType responseHeader){
		for(String messageCode: results.getValidationErrors()){
			responseHeader.addMessageList(messageCode);
		}
	}
}
