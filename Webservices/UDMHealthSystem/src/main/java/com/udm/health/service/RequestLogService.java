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

import org.apache.commons.lang3.StringUtils;
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
import com.udm.health.domain.internal.SortOrder;
import com.udm.health.domain.ws.HasResponseHeader;
import com.udm.health.domain.ws.Message;

@Component
public class RequestLogService extends TransformerObjectSupport {

	private Logger logger = LoggerFactory.getLogger(RequestLogService.class);
	
	@Autowired
	private RequestLogDao requestLogDao;
	@Autowired
	private ServiceService serviceService;
	private static String SELECT_QUERY = "select e from RequestLog e";
	private static String SELECT_COUNT = "select count(*) from RequestLog e";
		
	public List<RequestLog> findAll() {
		return requestLogDao.findAll();
	}
	
	public List<RequestLog> findAll(int start, int pageSize) {
		return requestLogDao.findAll(start, pageSize);
	}
	
	public List<RequestLog> findAll(int start, int pageSize, String sort, SortOrder sortOrder) {
		return requestLogDao.findAll(start, pageSize, sort, sortOrder);
	}
	
	public RequestLog findById(Long id) {
		return requestLogDao.findById(id);
	}
	
	public long recordCount() {
		return requestLogDao.recordCount();
	}
	
	public long recordCount(String service, String status, String fromDate, String toDate) {
		StringBuilder query = new StringBuilder(SELECT_COUNT);
		createWhereClause(service, status, "", null, fromDate, toDate, query);
		return requestLogDao.recordCount(query.toString());
	}
	
	
	public List<RequestLog> findLogsByFilters(int start, int pageSize, String service, String status, String sort, String sortOrder, String fromDate, String toDate) {
		List<RequestLog> list = null;
		String query=createRequestLogQuery(service, status, sort, sortOrder, fromDate, toDate);
		logger.debug("Executing Query: "+query);
		list = requestLogDao.findRequestLogs(query, start, pageSize);
		return list;
	}

	public String createRequestLogQuery(String service, String status, String sort, String sortOrder, String fromDate, String toDate) {
		StringBuilder query = new StringBuilder(SELECT_QUERY);
		createWhereClause(service, status, sort, sortOrder, fromDate, toDate, query);
		return query.toString();
	}

	private void createWhereClause(String service, String status, String sort, String sortOrder, String fromDate, String toDate, StringBuilder query) {
		if(StringUtils.isNotBlank(service) || StringUtils.isNotBlank(status) || StringUtils.isNotBlank(fromDate) || StringUtils.isNotBlank(toDate) ){
			query.append(" where");
			if(StringUtils.isNotBlank(service)){
				query.append(String.format(" e.serviceName = '%s'",service));
				if(StringUtils.isNotBlank(status) || StringUtils.isNotBlank(fromDate) || StringUtils.isNotBlank(toDate)){
					query.append(" and");
				}
			}
			if(StringUtils.isNotBlank(status)){
				query.append(String.format(" e.responseCode = '%s'",status));
				if(StringUtils.isNotBlank(fromDate) || StringUtils.isNotBlank(toDate)){
					query.append(" and");
				}
			}
			if(StringUtils.isNotBlank(fromDate)){
				query.append(String.format(" e.createDate >= '%s'",fromDate));
				if(StringUtils.isNotBlank(toDate)){
					query.append(" and");
				}
			}
			if(StringUtils.isNotBlank(toDate)){
				query.append(String.format(" e.createDate <= '%s'",toDate));
			}				
		}
		if (StringUtils.isNotBlank(sort)) {
			query.append(String.format(" order by e.%s %s", sort, SortOrder.fromString(sortOrder).toString()));
		}else if(StringUtils.isBlank(sort)){
			query.append(" order by e.createDate DESC");
		}
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
