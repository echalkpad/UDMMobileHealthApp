package com.udm.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udm.health.dao.UserAccessDao;
import com.udm.health.domain.hibernate.UserAccess;

@Component
public class UserAccessService {
	
	@Autowired
	private UserAccessDao userAccessDao;

	
	public List<UserAccess> findByMedicalStaffEmail(String email, String firstName, String lastName, String userEmail, String state, String sort, String sortOrder, int start, int pageSize){
		return userAccessDao.findByMedicalStaffEmail(email, firstName, lastName, userEmail, state, sort, sortOrder, start, pageSize);
	}
	
	public int recordCount(String email, String firstName, String lastName, String userEmail, String state) {
		return Long.valueOf(userAccessDao.recordCount(email, firstName, lastName, userEmail, state)).intValue();
	}
	
	public void saveUserAccess(UserAccess userAccess){
		userAccessDao.save(userAccess);
	}
	public void deleteUserAccess(Long medicalStaffId){
		List<UserAccess> userAccesList = userAccessDao.findUserAccessByMedicalStaffId(medicalStaffId);
		for(UserAccess access: userAccesList){
			userAccessDao.delete(access);
		}
	}
	
	public UserAccess findUserAccessById(Long id){
		return userAccessDao.findById(id);
	}
}
