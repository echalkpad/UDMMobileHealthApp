package com.udm.health.processor;

import com.udm.health.dao.mapping.WebServiceResponse;
import com.udm.health.domain.ws.SendBloodPressureMeasurementRequest;

public class SendBloodPreasureMeasurementProcessor implements RequestProcessor<SendBloodPressureMeasurementRequest, WebServiceResponse> {

	@Override
	public WebServiceResponse process(SendBloodPressureMeasurementRequest request) {
		WebServiceResponse response = new WebServiceResponse();
		
		return response;
	}

}
