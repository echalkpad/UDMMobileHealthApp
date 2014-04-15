package com.udm.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udm.health.dao.MedicalStaffDao;
import com.udm.health.domain.hibernate.MedicalStaff;
import com.udm.health.domain.hibernate.User;


@Component
public class MedicalStaffService {
	
	@Autowired
	private MedicalStaffDao medicalStaffDao;
	
	public List<MedicalStaff> findAll(){
		return medicalStaffDao.findAll();
	}
	
	public void createMedicalStaff(User user, String speciality){
		user.setUserType(speciality);
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setUser(user);
		medicalStaff.setSpeciality(speciality);
		medicalStaffDao.save(medicalStaff);
	}
	
	
	public void delete(MedicalStaff medicalStaff){
		medicalStaffDao.delete(medicalStaff);
	}
	
	public MedicalStaff findById(Long id){
		return medicalStaffDao.findById(id);
	}
	
	public MedicalStaff findByEmail(String email){
		return medicalStaffDao.findByEmail(email);
	}

}
