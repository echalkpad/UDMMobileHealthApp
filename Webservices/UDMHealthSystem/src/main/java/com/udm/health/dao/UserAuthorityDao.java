package com.udm.health.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.Authority;
import com.udm.health.domain.hibernate.UserAuthority;

@Component
public class UserAuthorityDao extends BaseDao<UserAuthority, Long> {

	public UserAuthorityDao() {
		super(UserAuthority.class);
	}
	
	public List<UserAuthority> findByUserName(String username) {
		TypedQuery<UserAuthority> query = entityManager.createQuery("select ua from UserAuthority ua where ua.username = :username", UserAuthority.class);
		query.setParameter("username", username);
		return query.getResultList();
	}
	
	public List<UserAuthority> findByAuthority(Authority authority) {
		TypedQuery<UserAuthority> query = entityManager.createQuery("select ua from UserAuthority ua where ua.authority = :authority", UserAuthority.class);
		query.setParameter("authority", authority);
		return query.getResultList();
	}
	
}
