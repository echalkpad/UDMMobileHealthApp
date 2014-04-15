package com.udm.health.endpoint;

import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import com.udm.health.domain.ws.BloodSugarMeasurementRequest;
import com.udm.health.domain.ws.BloodSugarMeasurementResponse;
import com.udm.health.domain.ws.ResponseHeaderType;

@Endpoint
@Component
public class BloodSugarMeasurementEndpoint extends BaseEndpoint<BloodSugarMeasurementRequest, BloodSugarMeasurementResponse>{


	@SoapAction("http://www.udm.com/BloodSugarMeasurementRequest")
	@ResponsePayload
	public BloodSugarMeasurementResponse handleRequest(@RequestPayload BloodSugarMeasurementRequest request, MessageContext messageContext) {
		return super.handleRequest(request, messageContext);
	}
	
	@Override
	protected Object processRequest(BloodSugarMeasurementRequest request) {
		return process(request);
	}

	@Override
	protected BloodSugarMeasurementResponse createResponse() {
		BloodSugarMeasurementResponse response = new BloodSugarMeasurementResponse();
		response.setResponseHeader(new ResponseHeaderType());
		return response;
	}

}
