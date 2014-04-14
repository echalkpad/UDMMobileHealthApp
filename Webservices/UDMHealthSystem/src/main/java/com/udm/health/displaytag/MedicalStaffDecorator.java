package com.udm.health.displaytag;

import org.displaytag.decorator.TableDecorator;

import com.udm.health.domain.hibernate.MedicalStaff;

public class MedicalStaffDecorator extends TableDecorator {

	MedicalStaff getMedicalStaff(){
		return (MedicalStaff) getCurrentRowObject();
	}
	
	public String getDelete() {
		MedicalStaff user = getMedicalStaff();
		return String.format("<a class=\"button\" href=\"javascript:deleteUser('%s')\"><img src=\"%s/resources/images/trash.png\" /></a>", user.getId(), getPageContext().getServletContext().getContextPath());
	}
	
	public String getPassword() {
		MedicalStaff user = getMedicalStaff();
		return String.format("<a class=\"button\" href=\"javascript:resetPassword('%s');\">Reset</a>", user.getId());
	}
	
	public String getName(){
		return getMedicalStaff().getUser().getFirstName();
	}
	
	public String getLastName(){
		return getMedicalStaff().getUser().getLastName();
	}
	
	public String getSpeciality(){
		return getMedicalStaff().getSpeciality();
	}
}
