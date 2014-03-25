package com.udm.health.mvc.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.udm.health.domain.hibernate.WebUser;
import com.udm.health.mvc.validation.UniqueInTable;
import com.udm.health.mvc.validation.UniqueInTables;

public class NewWebUser {

	@NotEmpty
	@UniqueInTables({
		@UniqueInTable(entity=WebUser.class, entityField="username")
	})
	private String username;
	@NotEmpty
	private String password;
	private Boolean enabled;
	private List<String> privileges;
	
	public List<String> getPrivileges() {
		if (privileges == null) {
			privileges = new ArrayList<String>();
		}
		return privileges;
	}
	
	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}
	
	public Boolean getEnabled() {
		if (enabled == null) {
			enabled = Boolean.FALSE;
		}
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
