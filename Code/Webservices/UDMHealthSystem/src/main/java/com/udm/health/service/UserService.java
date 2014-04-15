package com.udm.health.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.udm.health.dao.UserAccessDao;
import com.udm.health.dao.UserDao;
import com.udm.health.domain.hibernate.State;
import com.udm.health.domain.hibernate.User;
import com.udm.health.domain.hibernate.UserAccess;
import com.udm.health.mvc.controller.dto.NewMedicalStaff;

@Component
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserAccessDao userAccessDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public void createUser(com.udm.health.domain.internal.User createUser){
		User user = new User();
		user.setCity(createUser.getCity());
		user.setDateOfBirth(createUser.getDateOfBirth());
		user.setEmail(createUser.getEmail());
		user.setFirstName(createUser.getFirstName());
		user.setLastName(createUser.getLastName());
		user.setPassword(passwordEncoder.encode(createUser.getPassword()));
		user.setPhoneNumber(createUser.getPhoneNumber());
		user.setSsn(createUser.getSsn());
		user.setState(State.getState(createUser.getState()));
		user.setStreet(createUser.getStreet());
		user.setZipCode(createUser.getZipCode());
		user.setUserType("PATIENT");
	
		userDao.save(user);
	}
	
	
	public User createUser(NewMedicalStaff medicalStaff){
		User user = new User();
		user.setCity(medicalStaff.getCity());
		user.setDateOfBirth(medicalStaff.getDateOfBirth());
		user.setEmail(medicalStaff.getUsername());
		user.setFirstName(medicalStaff.getFirstName());
		user.setLastName(medicalStaff.getLastName());
		user.setPassword(passwordEncoder.encode(medicalStaff.getPassword()));
		user.setPhoneNumber(medicalStaff.getPhoneNumber());
		user.setSsn(medicalStaff.getSsn());
		user.setState(State.getState(medicalStaff.getState()));
		user.setStreet(medicalStaff.getStreet());
		user.setZipCode(medicalStaff.getZip());
	
		userDao.save(user);
		
		return user;
	}
	
	public List<User> findAvailableUserForMedicalStaffId(Long id){
		List<User> availableUserList = userDao.findAllPatients();
		List<UserAccess> currentUsers = userAccessDao.findUserAccessByMedicalStaffId(id);
		List<User> userList = new ArrayList<User>();
		
		
		Hashtable<Long, User> userMap = new Hashtable<Long, User>();
		for(UserAccess userAccess: currentUsers){
			userMap.put(userAccess.getUser().getIdUser(), userAccess.getUser());
		}
		
		for(User user: availableUserList){
			if(!userMap.containsKey(user.getIdUser())){
				userList.add(user);
			}
		}
		
		return userList;
	}
	
	
	public User findUserByEmail(String email){
		return userDao.findUserByEmail(email);
	}
	
	public User findUserById(Long id){
		return userDao.findById(id);
	}
	
	
	public void changePassword(Long id, String password){
		User user = findUserById(id);
		user.setPassword(password);
		userDao.update(user);
	}
	
	public boolean authenticateUser(String email, String password){
		User user = findUserByEmail(email);
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
