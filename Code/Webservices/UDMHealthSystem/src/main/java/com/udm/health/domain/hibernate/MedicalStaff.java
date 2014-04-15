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
@Table(name="medical_staff")
public class MedicalStaff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="medical_staff_pk")
	private Long id;
	
	@Column(name="speciality")
	private String speciality; 
	
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
