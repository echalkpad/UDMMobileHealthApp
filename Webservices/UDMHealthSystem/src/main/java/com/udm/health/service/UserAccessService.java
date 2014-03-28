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

	
	public List<UserAccess> findByMedicalStaffEmail(String email, String sort, String sortOrder, int start, int pageSize){
		return userAccessDao.findByMedicalStaffEmail(email, sort, sortOrder, start, pageSize);
	}
	
	public int recordCount(String email) {
		return Long.valueOf(userAccessDao.recordCount(email)).intValue();
	}
}
