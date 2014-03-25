package com.udm.health.dao;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.WebUser;

@Component
public class WebUserDao  extends BaseDao<WebUser, Long> {
	
	public WebUserDao() {
		super(WebUser.class);
	}
	
}
