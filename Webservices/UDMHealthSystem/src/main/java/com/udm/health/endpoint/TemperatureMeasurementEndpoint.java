package com.udm.health.endpoint;

import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import com.udm.health.domain.ws.ResponseHeaderType;
import com.udm.health.domain.ws.TemperatureMeasurementRequest;
import com.udm.health.domain.ws.TemperatureMeasurementResponse;

@Endpoint
@Component
public class TemperatureMeasurementEndpoint extends BaseEndpoint<TemperatureMeasurementRequest, TemperatureMeasurementResponse> {

	
	@SoapAction("http://www.udm.com/TemperatureMeasurementRequest")
	@ResponsePayload
	public TemperatureMeasurementResponse handleRequest(@RequestPayload TemperatureMeasurementRequest request, MessageContext messageContext) {
		return super.handleRequest(request, messageContext);
	}
	
	@Override
	protected Object processRequest(TemperatureMeasurementRequest request) {
		return process(request);
	}

	@Override
	protected TemperatureMeasurementResponse createResponse() {
		TemperatureMeasurementResponse response = new TemperatureMeasurementResponse();
		response.setResponseHeader(new ResponseHeaderType());
		return response;
	}


}
