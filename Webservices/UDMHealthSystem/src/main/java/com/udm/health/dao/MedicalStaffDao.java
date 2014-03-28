package com.udm.health.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.MedicalStaff;

@Component
public class MedicalStaffDao extends BaseDao<MedicalStaff, Long>{
	
	public MedicalStaffDao(){
		super(MedicalStaff.class);
	}
	
	public MedicalStaff MedicalStaffByEmail(String email){
		Query query = entityManager.createQuery("SELECT m FROM MedicalStaff m WHERE m.user.email = :email");
		query.setParameter("email", email);
		
		try{
			return (MedicalStaff) query.getSingleResult();
		}  catch (NoResultException e) {
			return null;
		}
	}
	
}
