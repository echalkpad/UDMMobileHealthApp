package com.udm.health.endpoint;

import static com.udm.health.interceptor.MessageContextEntry.WS_PASSWORD;
import static com.udm.health.interceptor.MessageContextEntry.WS_REQUEST_LOG;
import static com.udm.health.interceptor.MessageContextEntry.WS_RESPONSE;
import static com.udm.health.interceptor.MessageContextEntry.WS_USERNAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.context.MessageContext;

import com.udm.health.domain.hibernate.RequestLog;
import com.udm.health.domain.ws.HasResponseHeader;
import com.udm.health.domain.ws.RequestClassName;
import com.udm.health.processor.WcsRequestProcessor;
import com.udm.health.response.ResponseAppender;
import com.udm.health.service.RequestLogService;
import com.udm.health.validation.ValidationResults;
import com.udm.health.validation.Validator;

public abstract class BaseEndpoint<I extends RequestClassName, O extends HasResponseHeader> {

	private static final Logger logger = LoggerFactory.getLogger(BaseEndpoint.class);
	
	@Autowired
	private WcsRequestProcessor requestProcessor;
	@Autowired
	private Validator validator;
	@Autowired
	private ResponseAppender responseAppender;
	@Autowired
	private RequestLogService requestLogService;
	@Autowired
//	@Qualifier("webServiceAuthenticationManager")
	private AuthenticationManager authenticationManager;
	
	public O handleRequest(I request, MessageContext messageContext) {
		logger.trace("Entering endpoint " + request.getClass().getSimpleName());
		logRequest(request, messageContext);
		
		O response = createResponse();
		
		try {
			authenticate(messageContext);
			ValidationResults validationResults = validator.validate(request);
	
			if (validationResults.hasValidationErrors()) {
				responseAppender.appendToResponse(validationResults, response);
			} else {
				Object procResponse = processRequest(request);
				responseAppender.appendToResponse(procResponse, response);
			}
		} catch (Exception e) {
			logger.error("Unexpected error", e);
			responseAppender.appendToResponse(e, response);
		} finally {
			SecurityContextHolder.clearContext();
		}
		
		messageContext.setProperty(WS_RESPONSE.name(), response);
		logger.trace("Exiting endpoint " + request.getClass().getSimpleName());
		
		return response;
	}

	private void logRequest(I request, MessageContext messageContext) {
		RequestLog requestLog = requestLogService.logRequest(request, messageContext);
		messageContext.setProperty(WS_REQUEST_LOG.name(), requestLog);
	}

	private void authenticate(MessageContext messageContext) {
		String user = (String) messageContext.getProperty(WS_USERNAME.name());
		String password = (String) messageContext.getProperty(WS_PASSWORD.name());
		if(authenticationManager != null){
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}
	
	protected <T, R> R process(T request) {
		return requestProcessor.process(request);
	}
	
	protected abstract Object processRequest(I request);
	protected abstract O createResponse();
	
	public void setRequestProcessor(WcsRequestProcessor requestProcessor) {
		this.requestProcessor = requestProcessor;
	}
	
	public void setResponseAppender(ResponseAppender responseAppender) {
		this.responseAppender = responseAppender;
	}
	
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
	public void setRequestLogService(RequestLogService requestLogService) {
		this.requestLogService = requestLogService;
	}
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
}
