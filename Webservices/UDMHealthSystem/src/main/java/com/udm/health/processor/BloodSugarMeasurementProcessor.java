package com.udm.health.processor;

import org.springframework.beans.factory.annotation.Autowired;

import com.udm.health.dao.mapping.WebServiceResponse;
import com.udm.health.domain.ws.BloodSugarMeasurementRequest;
import com.udm.health.response.ResponseStatus;
import com.udm.health.service.BloodSugarMeasurementService;

public class BloodSugarMeasurementProcessor  implements RequestProcessor<BloodSugarMeasurementRequest, WebServiceResponse>{
	
	@Autowired
	private BloodSugarMeasurementService bloodSugarService;

	@Override
	public WebServiceResponse process(BloodSugarMeasurementRequest request) {
		WebServiceResponse response = new WebServiceResponse();
		if(bloodSugarService.saveBloodSugarMesarument(request)){
			response.setStatus(ResponseStatus.SUCCESS);
		}else{
			response.setStatus(ResponseStatus.FAILURE);
		}
		return response;
	}

}
