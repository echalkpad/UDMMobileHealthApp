package com.udm.health.processor;

import org.springframework.beans.factory.annotation.Autowired;

import com.udm.health.dao.mapping.WebServiceResponse;
import com.udm.health.domain.ws.SendBloodPressureMeasurementRequest;
import com.udm.health.response.ResponseStatus;
import com.udm.health.service.BloodPressureService;

public class SendBloodPreasureMeasurementProcessor implements RequestProcessor<SendBloodPressureMeasurementRequest, WebServiceResponse> {

	@Autowired
	private BloodPressureService bloodPressureService;
	
	@Override
	public WebServiceResponse process(SendBloodPressureMeasurementRequest request) {
		WebServiceResponse response = new WebServiceResponse();
		if(bloodPressureService.saveBloodPressureRequest(request))
			response.setStatus(ResponseStatus.SUCCESS);
		else
			response.setStatus(ResponseStatus.FAILURE);
		return response;
	}

}
