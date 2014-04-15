package com.udm.health.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.User;

@Component
public class UserDao extends BaseDao<User, Long>{

	public UserDao() {
		super(User.class);
	}

	public User findUserByEmail(String email){
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email");
		query.setParameter("email", email);
		
		try{
			return (User) query.getSingleResult();
		}  catch (NoResultException e) {
			return null;
		}
	}
	
	public List<User> findAllPatients(){
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.userType = :userType", User.class);
		query.setParameter("userType", "PATIENT");
		return query.getResultList();
	}
	
}
