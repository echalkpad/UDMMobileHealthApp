package com.udm.health.domain.hibernate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="request_logs")
public class RequestLog {

	@Id
    @SequenceGenerator(name = "request_logs_seq", allocationSize = 1, sequenceName = "request_logs_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_logs_seq")
	@Column(name="request_log_pk")
	private Long id;
	

	@Column(name="request_xml")
	private String requestXml;
	
	@Column(name="response_xml")
	private String responseXml;
	
	@Column(name="service_name")
	private String serviceName;
	
	@Column(name="response_code")
	private String responseCode;
	
	@Column(name="response_message")
	private String responseMessage;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="update_date")
	private Date updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestXml() {
		return requestXml;
	}

	public void setRequestXml(String requestXml) {
		this.requestXml = requestXml;
	}

	public String getResponseXml() {
		return responseXml;
	}

	public void setResponseXml(String responseXml) {
		this.responseXml = responseXml;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
