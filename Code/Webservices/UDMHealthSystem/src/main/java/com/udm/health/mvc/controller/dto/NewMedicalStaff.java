package com.udm.health.mvc.controller.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.udm.health.domain.hibernate.WebUser;
import com.udm.health.mvc.validation.UniqueInTable;
import com.udm.health.mvc.validation.UniqueInTables;

public class NewMedicalStaff {
	
	@NotEmpty
	@UniqueInTables({
		@UniqueInTable(entity=WebUser.class, entityField="username")
	})
	@Email
	private String username;
	@Length(min=5, max = 10)
	private String password;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String dateOfBirth;	
	@NotEmpty
	private String phoneNumber;
	@NotEmpty
	private String ssn;
	@NotEmpty
	private String street;
	@NotEmpty
	private String city;
	@NotEmpty
	private String state;
	@NotEmpty
	private String zip;
	private Boolean enabled;
	@NotEmpty
	private String speciality;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Boolean getEnabled() {
		if (enabled == null) {
			enabled = Boolean.TRUE;
		}
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

}
