package com.udm.health.endpoint;

import static com.udm.health.interceptor.MessageContextEntry.WS_PASSWORD;
import static com.udm.health.interceptor.MessageContextEntry.WS_REQUEST_LOG;
import static com.udm.health.interceptor.MessageContextEntry.WS_RESPONSE;
import static com.udm.health.interceptor.MessageContextEntry.WS_USERNAME;
import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertSame;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ws.context.MessageContext;

import com.udm.health.domain.hibernate.RequestLog;
//import com.udm.health.domain.ws.GetSourceListRequest;
//import com.udm.health.domain.ws.GetSourceListResponse;
import com.udm.health.domain.ws.HasResponseHeader;
import com.udm.health.processor.UDMRequestProcessor;
import com.udm.health.response.ResponseAppender;
import com.udm.health.service.RequestLogService;
import com.udm.health.validation.ErrorType;
import com.udm.health.validation.ValidationResults;
import com.udm.health.validation.Validator;

public class BaseEndpointTest {
	
//	private static final String PASSWORD = "password";
//	private static final String USER = "user";
//	private IMocksControl mocks;
//	private WcsRequestProcessor requestProcessor;
//	private Validator validator;
//	private ResponseAppender responseAppender;
//	private RequestLogService requestLogService;
//	private BaseEndpoint baseEndpoint;
//	private GetSourceListRequest request;
//	private MessageContext messageContext;
//	private GetSourceListResponse response;
//	private RequestLog requestLog;
//	private AuthenticationManager authenticationManager;
//	
//	@Before
//	public void setup() {
//		mocks = createControl();
//		requestProcessor = mocks.createMock(WcsRequestProcessor.class);
//		validator = mocks.createMock(Validator.class);
//		responseAppender = mocks.createMock(ResponseAppender.class);
//		requestLogService = mocks.createMock(RequestLogService.class);
//		messageContext = mocks.createMock(MessageContext.class);
//		authenticationManager = mocks.createMock(AuthenticationManager.class);
//		
//		baseEndpoint = EasyMock.createMockBuilder(BaseEndpoint.class).addMockedMethods("createResponse", "processRequest").createMock(mocks);
//		
//		baseEndpoint.setRequestLogService(requestLogService);
//		baseEndpoint.setRequestProcessor(requestProcessor);
//		baseEndpoint.setResponseAppender(responseAppender);
//		baseEndpoint.setValidator(validator);
//		baseEndpoint.setAuthenticationManager(authenticationManager);
//
//		requestLog = new RequestLog();
//		request = new GetSourceListRequest();
//		response = new GetSourceListResponse();
//	}
//	
//	@Test
//	public void shouldProcessRequestWhenHasNoErrors() {
//		ValidationResults results = new ValidationResults();
//		String procResults = "results";
//
//		
//
//		expect(requestLogService.logRequest(request, messageContext)).andReturn(requestLog);
//		expect(baseEndpoint.createResponse()).andReturn(response);
//		expect(validator.validate(request)).andReturn(results);
//		expect(baseEndpoint.processRequest(request)).andReturn(procResults);
//		responseAppender.appendToResponse(procResults, response);
//		messageContext.setProperty(WS_REQUEST_LOG.name(), requestLog);
//		messageContext.setProperty(WS_RESPONSE.name(), response);
//		expect(messageContext.getProperty(WS_USERNAME.name())).andReturn(USER);
//		expect(messageContext.getProperty(WS_PASSWORD.name())).andReturn(PASSWORD);
//		expect(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(USER, PASSWORD))).andReturn(null);
//		
//		mocks.replay();
//		
//		HasResponseHeader actualResponse = baseEndpoint.handleRequest(request, messageContext);
//		assertSame(response, actualResponse);
//
//		mocks.verify();
//	}
//	
//	@Test
//	public void shouldNotProcessRequestWhenHasValidationErrors() {
//		ValidationResults results = new ValidationResults();
//		results.addValidationError(ErrorType.NULL.getErrorDescription());
// 
//		expect(requestLogService.logRequest(request, messageContext)).andReturn(requestLog);
//		expect(baseEndpoint.createResponse()).andReturn(response);
//		expect(validator.validate(request)).andReturn(results);
//		responseAppender.appendToResponse(results, response);
//		messageContext.setProperty(WS_REQUEST_LOG.name(), requestLog);
//		messageContext.setProperty(WS_RESPONSE.name(), response);
//		expect(messageContext.getProperty(WS_USERNAME.name())).andReturn(USER);
//		expect(messageContext.getProperty(WS_PASSWORD.name())).andReturn(PASSWORD);
//		expect(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(USER, PASSWORD))).andReturn(null);
//		
//		mocks.replay();
//		
//		HasResponseHeader actualResponse = baseEndpoint.handleRequest(request, messageContext);
//		assertSame(response, actualResponse);
//		
//		mocks.verify();
//	}
//
//	@Test
//	public void shouldAttachErrorWhenThrowsAnException() {
//		ValidationResults results = new ValidationResults();
//		Throwable exception = new RuntimeException();
//
//		expect(requestLogService.logRequest(request, messageContext)).andReturn(requestLog);
//		expect(baseEndpoint.createResponse()).andReturn(response);
//		expect(validator.validate(request)).andReturn(results);
//		expect(baseEndpoint.processRequest(request)).andThrow(exception);
//		responseAppender.appendToResponse(exception, response);
//		messageContext.setProperty(WS_REQUEST_LOG.name(), requestLog);
//		messageContext.setProperty(WS_RESPONSE.name(), response);
//		expect(messageContext.getProperty(WS_USERNAME.name())).andReturn(USER);
//		expect(messageContext.getProperty(WS_PASSWORD.name())).andReturn(PASSWORD);
//		expect(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(USER, PASSWORD))).andReturn(null);
//		
//		mocks.replay();
//		
//		HasResponseHeader actualResponse = baseEndpoint.handleRequest(request, messageContext);
//		assertSame(response, actualResponse);
//		
//		mocks.verify();
//	}
//	
//	@Test
//	public void shouldReturnWhenAuthenticationFails() {
//		Throwable exception = new BadCredentialsException("");
//		
//		expect(requestLogService.logRequest(request, messageContext)).andReturn(requestLog);
//		expect(baseEndpoint.createResponse()).andReturn(response);
//		messageContext.setProperty(WS_REQUEST_LOG.name(), requestLog);
//		messageContext.setProperty(WS_RESPONSE.name(), response);
//		expect(messageContext.getProperty(WS_USERNAME.name())).andReturn(USER);
//		expect(messageContext.getProperty(WS_PASSWORD.name())).andReturn(PASSWORD);
//		expect(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(USER, PASSWORD))).andThrow(exception);
//		responseAppender.appendToResponse(exception, response);
//		
//		mocks.replay();
//		
//		HasResponseHeader actualResponse = baseEndpoint.handleRequest(request, messageContext);
//		assertSame(response, actualResponse);
//		
//		mocks.verify();
//	}

}
