package com.udm.health.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.RequestLog;

@Component
public class RequestLogDao extends BaseDao<RequestLog, Long> {

	public RequestLogDao() {
		super(RequestLog.class);
	}
	
	
	public List<RequestLog> findRequestLogs(String queryStr, int start, int pageSize){
		try {
			TypedQuery<RequestLog> query = entityManager.createQuery(queryStr, RequestLog.class);
			query.setFirstResult(start);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
}
