package com.udm.health.service;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.udm.health.dao.WebUserDao;
import com.udm.health.domain.hibernate.WebUser;

@Component
public class WebUserService {

	@Autowired
	private WebUserDao webUserDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<WebUser> findAll() {
		return webUserDao.findAll();
	}
	
	public void changePassword(Long id, String password) {
		WebUser user = webUserDao.findById(id);
		Validate.notNull(user, "No web user found for id %s.", id);
		user.setPassword(passwordEncoder.encode(password));
		webUserDao.update(user);
	}
	
	public void enable(Long id, Boolean enabled) {
		WebUser user = webUserDao.findById(id);
		if (user != null) {
			user.setEnabled(enabled);
			webUserDao.update(user);
		}
	}
	
	public WebUser find(Long id) {
		return webUserDao.findById(id);
	}
	
	public void save(WebUser user) {
		webUserDao.save(user);
	}
	
	public void delete(Long id) {
		delete(webUserDao.findById(id));
	}
	
	public void delete(WebUser user) {
		webUserDao.delete(user);
	}
	
	public void setWebUserDao(WebUserDao webUserDao) {
		this.webUserDao = webUserDao;
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
