package com.udm.health.response;

import com.udm.health.dao.mapping.WebServiceResponse;
import com.udm.health.domain.ws.BloodSugarMeasurementResponse;
import com.udm.health.domain.ws.ResponseHeaderType;

public class BloodSugarMeasurementAdapter implements ResponseAdapter<WebServiceResponse, BloodSugarMeasurementResponse>{

	@Override
	public boolean shouldAdapt(Object responseIn, Object responseOut) {
		return WebServiceResponse.class == responseIn.getClass() && BloodSugarMeasurementResponse.class == responseOut.getClass();
	}

	@Override
	public void adaptAndAttach(WebServiceResponse responseIn, BloodSugarMeasurementResponse responseOut) {
		ResponseHeaderType responseHeader = (ResponseHeaderType)responseOut.ensureResponseHeader();
		responseHeader.setStatusCode(responseIn.getStatus().getCode());
	}

}
