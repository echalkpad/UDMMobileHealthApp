package com.udm.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udm.health.dao.AuthorityDao;
import com.udm.health.domain.hibernate.Authority;

@Component
public class AuthorityService {
	
	@Autowired
	private AuthorityDao authorityDao;
	
	public List<Authority> findAll() {
		return authorityDao.findAll();
	}
	
	public Authority findById(Long id) {
		return authorityDao.findById(id);
	}
	
	public Authority findByName(String authorityName) {
		return authorityDao.findByName(authorityName);
	}
	
	public void save(Authority authority) {
		authorityDao.save(authority);
	}
	
	public void delete(Authority authority) {
		authorityDao.delete(authority);
	}
	
	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}
}
