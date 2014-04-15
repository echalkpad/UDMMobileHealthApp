package com.udm.health.interceptor;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ws.context.MessageContext;

import com.udm.health.domain.hibernate.RequestLog;
import com.udm.health.domain.ws.BloodSugarMeasurementResponse;
import com.udm.health.service.RequestLogService;


public class RequestLogInterceptorTest {

	private IMocksControl mocks;
	private RequestLogService requestLogService;
	private RequestLogInterceptor interceptor;
	private MessageContext messageContext;
	private RequestLog requestLog;
	private BloodSugarMeasurementResponse response;
	
	@Before
	public void setup() {
		mocks = createControl();
		requestLogService = mocks.createMock(RequestLogService.class);
		messageContext = mocks.createMock(MessageContext.class);
		response = new BloodSugarMeasurementResponse();
		requestLog = new RequestLog();
		
		interceptor = new RequestLogInterceptor();
		interceptor.setRequestLogService(requestLogService);
	}
	
	@Test
	public void shouldLogResponseWhenResponseAndRequestLogSet() throws Exception {
		expect(messageContext.getProperty("WS_RESPONSE")).andReturn(response);
		expect(messageContext.getProperty("WS_REQUEST_LOG")).andReturn(requestLog);
		expect(requestLogService.logResponse(response, messageContext, requestLog)).andReturn(requestLog);
		
		mocks.replay();
		
		interceptor.handleResponse(messageContext, null);
		
		mocks.verify();
	}
	
	@Test
	public void shouldDoNothingWhenResponseNotSet() throws Exception {
		expect(messageContext.getProperty("WS_RESPONSE")).andReturn(null);
		expect(messageContext.getProperty("WS_REQUEST_LOG")).andReturn(requestLog);
		
		mocks.replay();
		
		interceptor.handleResponse(messageContext, null);
		
		mocks.verify();
	}
	
	@Test
	public void shouldDoNothingWhenRequestLogNotSet() throws Exception {
		expect(messageContext.getProperty("WS_RESPONSE")).andReturn(response);
		expect(messageContext.getProperty("WS_REQUEST_LOG")).andReturn(null);
		
		mocks.replay();
		
		interceptor.handleResponse(messageContext, null);
		
		mocks.verify();
	}
	
}
