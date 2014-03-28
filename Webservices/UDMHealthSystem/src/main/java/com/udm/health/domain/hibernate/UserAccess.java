package com.udm.health.domain.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_access")
public class UserAccess {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_access_pk")
	private Long id;

	@ManyToOne
	@JoinColumn(name="medical_staff_pk")
	private MedicalStaff medicalStaff;
	
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;
	
	
	@Column(name="enabled")
	private Boolean enabled;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public MedicalStaff getMedicalStaff() {
		return medicalStaff;
	}


	public void setMedicalStaff(MedicalStaff medicalStaff) {
		this.medicalStaff = medicalStaff;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
		
}
