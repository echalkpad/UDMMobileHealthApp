package com.udm.health.displaytag;

import org.displaytag.decorator.TableDecorator;

import com.udm.health.domain.hibernate.WebUser;

public class WebUserDecorator extends TableDecorator {

	public String getDelete() {
		WebUser user = getWebUser();
		return String.format("<a class=\"button\" href=\"javascript:deleteUser('%s')\"><img src=\"%s/resources/images/trash.png\" /></a>", user.getId(), getPageContext().getServletContext().getContextPath());
	}
	
	public String getPassword() {
		WebUser user = getWebUser();
		return String.format("<a class=\"button\" href=\"javascript:resetPassword('%s');\">Reset</a>", user.getId());
	}
	
	public String getEnabled() {
		WebUser user = getWebUser();
		String checked = user.getEnabled() ? "checked=\"true\"" : "";
		return String.format("<input type=\"checkbox\" name=\"enabled\" value=\"%s\" %s />", user.getId(), checked);
	}
	
	public String getPrivileges() {
		WebUser user = getWebUser();
		return String.format("<a class=\"button\" href=\"javascript:displayPrivileges('%s');\">Edit</a>", user.getUsername());
	}
	
	//exposed for testing
	WebUser getWebUser() {
		return (WebUser) getCurrentRowObject();
	}
	
}
