package com.udm.health.endpoint;

import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import com.udm.health.domain.ws.ResponseHeaderType;
import com.udm.health.domain.ws.SendBloodPressureMeasurementRequest;
import com.udm.health.domain.ws.SendBloodPressureMeasurementResponse;

@Endpoint
@Component
public class SendBloodPressureMeasurementEndpoint extends BaseEndpoint<SendBloodPressureMeasurementRequest, SendBloodPressureMeasurementResponse>{

	@SoapAction("http://www.udm.com/SendBloodPressureMeasurement")
	@ResponsePayload
	public SendBloodPressureMeasurementResponse handleRequest(@RequestPayload SendBloodPressureMeasurementRequest request, MessageContext messageContext) {
		return super.handleRequest(request, messageContext);
	}
	
	@Override
	protected Object processRequest(SendBloodPressureMeasurementRequest request) {
		return process(request);
	}

	@Override
	protected SendBloodPressureMeasurementResponse createResponse() {
		SendBloodPressureMeasurementResponse response = new SendBloodPressureMeasurementResponse();
		response.setResponseHeader(new ResponseHeaderType());
		return response;
	}

}
