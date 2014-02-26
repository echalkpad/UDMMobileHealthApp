package com.udm.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udm.health.dao.UserDao;
import com.udm.health.domain.hibernate.State;
import com.udm.health.domain.hibernate.User;

@Component
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	public void createUser(com.udm.health.domain.internal.User createUser){
		User user = new User();
		user.setCity(createUser.getCity());
		user.setDateOfBirth(createUser.getDateOfBirth());
		user.setEmail(createUser.getEmail());
		user.setFirstName(createUser.getFirstName());
		user.setLastName(createUser.getLastName());
		user.setPassword(createUser.getPassword());
		user.setPhoneNumber(createUser.getPhoneNumber());
		user.setSsn(createUser.getSsn());
		user.setState(State.getState(createUser.getState()));
		user.setStreet(createUser.getStreet());
		user.setZipCode(createUser.getZipCode());
	
		userDao.save(user);
	}
	
	
	public User finUserByEmail(String email){
		return userDao.finUserByEmail(email);
	}
	
	public boolean authenticateUser(String email, String password){
		User user = finUserByEmail(email);
		if(user != null){
			if(password.toUpperCase().equals(user.getPassword().toUpperCase()))
				return true;
			else
				return false;
		}
		else{
			return false;
		}
	}

}
