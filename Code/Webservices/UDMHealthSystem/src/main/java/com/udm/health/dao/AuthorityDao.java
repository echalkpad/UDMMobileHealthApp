package com.udm.health.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.Authority;

@Component
public class AuthorityDao extends BaseDao<Authority, Long> {
	
	public AuthorityDao() {
		super(Authority.class);
	}
	
	public Authority findByName(String authority) {
		TypedQuery<Authority> query = entityManager.createQuery("select a from Authority a where a.authorityType = :authority", Authority.class);
		query.setParameter("authority", authority);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
