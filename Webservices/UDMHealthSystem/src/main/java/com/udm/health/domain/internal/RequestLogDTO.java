package com.udm.health.domain.internal;

import java.math.BigDecimal;
import java.util.Date;

public class RequestLogDTO {
	private Long requestLogId;
	private String refId;
	private String clientRequestId;
	private String serviceName;
	private Date receivedDate;
	private Long responseCode;
	private Long batchCount;
	private BigDecimal duration;
	private String externalOrderNumber;
	
	public RequestLogDTO(){
		
	}
	
	public RequestLogDTO(Long requestLogId, String refId, String clientRequestId, String serviceName, Date receivedDate, Long responseCode, Long batchCount, BigDecimal duration, String externalOrderNumber){
		this.requestLogId = requestLogId;
		this.refId = refId;
		this.clientRequestId = clientRequestId;
		this.serviceName = serviceName;
		this.receivedDate = receivedDate;
		this.responseCode = responseCode;
		this.batchCount = batchCount;
		this.duration = duration;
		this.externalOrderNumber = externalOrderNumber;		
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getClientRequestId() {
		return clientRequestId;
	}

	public void setClientRequestId(String clientRequestId) {
		this.clientRequestId = clientRequestId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Long getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Long responseCode) {
		this.responseCode = responseCode;
	}

	public Long getBatchCount() {
		return batchCount;
	}

	public void setBatchCount(Long batchCount) {
		this.batchCount = batchCount;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public String getExternalOrderNumber() {
		return externalOrderNumber;
	}

	public void setExternalOrderNumber(String externalOrderNumber) {
		this.externalOrderNumber = externalOrderNumber;
	}

	public Long getRequestLogId() {
		return requestLogId;
	}

	public void setRequestLogId(Long requestLogId) {
		this.requestLogId = requestLogId;
	}
}
