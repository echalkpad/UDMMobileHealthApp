package com.udm.health.dao;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.RequestLog;

@Component
public class RequestLogDao extends BaseDao<RequestLog, Long> {

	public RequestLogDao() {
		super(RequestLog.class);
	}
	
}
