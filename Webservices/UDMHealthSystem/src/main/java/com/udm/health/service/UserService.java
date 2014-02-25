package com.udm.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udm.health.dao.UserDao;
import com.udm.health.domain.hibernate.User;

@Component
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	public void createUser(User user){
		userDao.save(user);
	}
	
	
	public User finUserByEmail(String email){
		return userDao.finUserByEmail(email);
	}

}
