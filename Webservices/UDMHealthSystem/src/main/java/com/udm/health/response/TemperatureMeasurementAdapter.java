package com.udm.health.response;

import com.udm.health.dao.mapping.WebServiceResponse;
import com.udm.health.domain.ws.ResponseHeaderType;
import com.udm.health.domain.ws.TemperatureMeasurementResponse;

public class TemperatureMeasurementAdapter implements ResponseAdapter<WebServiceResponse, TemperatureMeasurementResponse>{

	@Override
	public boolean shouldAdapt(Object responseIn, Object responseOut) {
		return WebServiceResponse.class == responseIn.getClass() && TemperatureMeasurementResponse.class == responseOut.getClass();
	}

	@Override
	public void adaptAndAttach(WebServiceResponse responseIn, TemperatureMeasurementResponse responseOut) {
		ResponseHeaderType responseHeader = (ResponseHeaderType)responseOut.ensureResponseHeader();
		responseHeader.setStatusCode(responseIn.getStatus().getCode());
		
	}

}
