package com.udm.health.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.WebUser;

@Component
public class WebUserDao  extends BaseDao<WebUser, Long> {
	
	public WebUserDao() {
		super(WebUser.class);
	}
	
	public WebUser findByUserName(String name){
		TypedQuery<WebUser> query = entityManager.createQuery("Select u from WebUser u WHERE u.username = :username", WebUser.class);
		query.setParameter("username", name);
		return query.getSingleResult();
	}
	
}
