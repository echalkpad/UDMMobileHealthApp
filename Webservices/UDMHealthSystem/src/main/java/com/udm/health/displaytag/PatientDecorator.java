package com.udm.health.displaytag;

import org.displaytag.decorator.TableDecorator;
import org.springframework.stereotype.Component;

import com.udm.health.domain.hibernate.UserAccess;

@Component
public class PatientDecorator extends TableDecorator  {

	private UserAccess getUserAccess(){
		return (UserAccess)getCurrentRowObject();
	}
	
	public String getFirstName(){
		return getUserAccess().getUser().getFirstName();
	}
	
	public String getLastName(){
		return getUserAccess().getUser().getLastName();
	}
	
	public String getDateOfBirth(){
		return getUserAccess().getUser().getDateOfBirth();
	}
	
	public String getStreet(){
		return getUserAccess().getUser().getStreet();
	}
	
	public String getCity(){
		return getUserAccess().getUser().getCity();
	}
	
	public String getState(){
		return getUserAccess().getUser().getState().getStateName();
	}
	
	
	public String getViewDetails() {
		Long userId = getUserAccess().getUser().getIdUser();
		return String.format("<a href=\"javascript:displayDetails( %s)\">View</a>", userId);
	}
}
