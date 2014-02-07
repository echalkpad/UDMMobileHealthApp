package com.udm.health.service;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.TransformerObjectSupport;

import com.udm.health.dao.RequestLogDao;
import com.udm.health.domain.hibernate.RequestLog;
import com.udm.health.domain.internal.Service;
import com.udm.health.domain.ws.HasResponseHeader;
import com.udm.health.domain.ws.Message;

@Component
public class RequestLogService extends TransformerObjectSupport {

	private Logger logger = LoggerFactory.getLogger(RequestLogService.class);
	
	@Autowired
	private RequestLogDao requestLogDao;
	@Autowired
	private ServiceService serviceService;
		
	public List<RequestLog> findAll() {
		return requestLogDao.findAll();
	}
	
	public List<RequestLog> findAll(int start, int pageSize) {
		return requestLogDao.findAll(start, pageSize);
	}
	
	public RequestLog findById(Long id) {
		return requestLogDao.findById(id);
	}
	
	public long recordCount() {
		return requestLogDao.recordCount();
	}
	
	
	public RequestLog logRequest(Object request, MessageContext messageContext) {
		Service service = Service.lookupServiceFor(request.getClass());
		
		RequestLog requestLog = new RequestLog();
		requestLog.setServiceName(service.toString());
		requestLog.setCreateDate(new Date());
		requestLog.setUpdatedDate(new Date());
		logRequestXml(messageContext, service.toString(), requestLog);
		
		try {
			requestLogDao.save(requestLog);
		} catch (Exception e) {
			logger.warn("Failed to log request.", e);
		}
		return requestLog;
	}

	public RequestLog logResponse(HasResponseHeader response, MessageContext messageContext, RequestLog requestLog) {
		String statusCode = response.ensureResponseHeader().getStatusCode();
		Message messageList = response.ensureResponseHeader().getMessageList();
		if (statusCode != null) {
			requestLog.setResponseCode(statusCode);
			requestLog.setResponseMessage(getMessageListString(messageList));			
		}
		requestLog.setUpdatedDate(new Date());
		logResponseXml(messageContext, requestLog);
		
		try {
			return requestLogDao.update(requestLog);
		} catch (Exception e) {
			logger.warn("Failed to log response.", e);
			return requestLog;
		}
	}
	
	private String getMessageListString(Message messageList){
		StringBuffer messageString = new StringBuffer();
		for(String singleMessage : messageList.getMessage()){
			messageString.append(singleMessage+"\n");
		}
		return messageString.toString();
	}

	private void logResponseXml(MessageContext messageContext, RequestLog requestLog) {
		try {
			if (serviceService.isLoggingEnabled(requestLog.getServiceName())) {
				String responseXml = writeXml(messageContext.getResponse());
				requestLog.setResponseXml(responseXml);
			}
		} catch (Exception e) {
			logger.warn("Exception while logging response xml.", e);
		}
	}
	
	private void logRequestXml(MessageContext messageContext, String serviceName, RequestLog requestLog) {
		try {
			if (serviceService.isLoggingEnabled(requestLog.getServiceName())) {
				String requestXml = writeXml(messageContext.getRequest());
				requestLog.setRequestXml(requestXml);
			}
		} catch (Exception e) {
			logger.warn("Exception while logging request xml.", e);
		}
	}
	
	private String writeXml(WebServiceMessage message) {
		try {
			Source source = getSource(message);
			Transformer transformer = createNonIndentingTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(source, new StreamResult(writer));
			return writer.toString();
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}

	private Source getSource(WebServiceMessage message) {
        if (message instanceof SoapMessage) {
            SoapMessage soapMessage = (SoapMessage) message;
            return soapMessage.getEnvelope().getSource();
        }
        else {
            return null;
        }
    }
	
    private Transformer createNonIndentingTransformer() throws TransformerConfigurationException {
        Transformer transformer = createTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        return transformer;
    }
	
	public void setRequestLogDao(RequestLogDao requestLogDao) {
		this.requestLogDao = requestLogDao;
	}

}
