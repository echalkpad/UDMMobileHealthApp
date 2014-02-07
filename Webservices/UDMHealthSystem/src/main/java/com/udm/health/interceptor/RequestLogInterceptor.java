package com.udm.health.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

import com.udm.health.domain.hibernate.RequestLog;
import com.udm.health.domain.ws.HasResponseHeader;
import com.udm.health.service.RequestLogService;

@Component
public class RequestLogInterceptor implements EndpointInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(RequestLogInterceptor.class);
	
	private static final String WS_REQUEST_LOG = "WS_REQUEST_LOG";
	private static final String WS_RESPONSE = "WS_RESPONSE";
	
	@Autowired
	private RequestLogService requestLogService;
	
	@Override
	public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
		HasResponseHeader response = (HasResponseHeader) messageContext.getProperty(WS_RESPONSE);
		RequestLog requestLog = (RequestLog) messageContext.getProperty(WS_REQUEST_LOG);
		if (response != null && requestLog != null) {
			requestLogService.logResponse(response, messageContext, requestLog);
		} else {
			logger.warn(String.format("Response [%s] and Request Log [%s] must not be null.", response, requestLog));
		}
		return true;
	}
	
	@Override
	public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
		return true;
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
		
	}
	
	public void setRequestLogService(RequestLogService requestLogService) {
		this.requestLogService = requestLogService;
	}

}
