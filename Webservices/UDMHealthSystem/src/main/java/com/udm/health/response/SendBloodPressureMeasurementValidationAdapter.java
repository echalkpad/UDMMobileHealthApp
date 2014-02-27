package com.udm.health.response;

import com.udm.health.dao.mapping.WebServiceResponse;
import com.udm.health.domain.ws.ResponseHeaderType;
import com.udm.health.domain.ws.SendBloodPressureMeasurementResponse;

public class SendBloodPressureMeasurementValidationAdapter implements ResponseAdapter<WebServiceResponse, SendBloodPressureMeasurementResponse> {

	@Override
	public boolean shouldAdapt(Object responseIn, Object responseOut) {
		return WebServiceResponse.class == responseIn.getClass() && SendBloodPressureMeasurementResponse.class == responseOut.getClass();
	}

	@Override
	public void adaptAndAttach(WebServiceResponse responseIn, SendBloodPressureMeasurementResponse responseOut) {
		ResponseHeaderType responseHeader = (ResponseHeaderType)responseOut.ensureResponseHeader();
		responseHeader.setStatusCode(responseIn.getStatus().getCode());
	}

}
