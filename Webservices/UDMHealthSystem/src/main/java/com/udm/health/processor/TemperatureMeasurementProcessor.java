package com.udm.health.processor;

import org.springframework.beans.factory.annotation.Autowired;

import com.udm.health.dao.mapping.WebServiceResponse;
import com.udm.health.domain.ws.TemperatureMeasurementRequest;
import com.udm.health.response.ResponseStatus;
import com.udm.health.service.TemperatureMeasurementService;

public class TemperatureMeasurementProcessor implements RequestProcessor<TemperatureMeasurementRequest, WebServiceResponse>{
	
	@Autowired
	private TemperatureMeasurementService temperatureService;

	@Override
	public WebServiceResponse process(TemperatureMeasurementRequest request) {
		WebServiceResponse response = new WebServiceResponse();
		if(temperatureService.saveTemperatureMesarument(request)){
			response.setStatus(ResponseStatus.SUCCESS);
		}else{
			response.setStatus(ResponseStatus.FAILURE);
		}
		return response;
	}

}
