package com.udm.health.displaytag;

import org.displaytag.decorator.TableDecorator;

import com.udm.health.domain.hibernate.User;

public class UserAccessDecorator extends TableDecorator  {

		private User getUser(){
			return (User)getCurrentRowObject();
		}
		
		public String getFirstName(){
			return getUser().getFirstName();
		}
		
		public String getLastName(){
			return getUser().getLastName();
		}
		
		public String getDateOfBirth(){
			return getUser().getDateOfBirth();
		}
		
		public String getStreet(){
			return getUser().getStreet();
		}
		
		public String getCity(){
			return getUser().getCity();
		}
		
		public String getState(){
			return getUser().getState().getStateName();
		}
		
		
		public String getAdd() {
			Long userId = getUser().getIdUser();
			return String.format("<a class=\"button\" href=\"javascript:followUser('%s')\"><img src=\"%s/resources/images/add.png\" /></a>", userId, getPageContext().getServletContext().getContextPath());
		}
}
