package com.udm.health.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.User;

@Component
public class UserDao extends BaseDao<User, Long>{

	public UserDao() {
		super(User.class);
	}

	public User finUserByEmail(String email){
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email");
		query.setParameter("email", email);
		
		try{
			return (User) query.getSingleResult();
		}  catch (NoResultException e) {
			return null;
		}
	}
}
