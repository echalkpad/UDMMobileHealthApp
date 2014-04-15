package com.udm.health.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.Service;

@Component
public class ServiceDao extends BaseDao<Service, Long> {

	public ServiceDao() {
		super(Service.class);
	}
	
	public Service findByServiceName(String serviceName) {
		Query query = entityManager.createQuery("SELECT s FROM Service s WHERE s.serviceName = :serviceName");
		query.setParameter("serviceName", serviceName);
		
		try {
			return (Service) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
