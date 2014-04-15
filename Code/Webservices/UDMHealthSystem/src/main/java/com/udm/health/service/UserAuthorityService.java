package com.udm.health.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udm.health.dao.UserAuthorityDao;
import com.udm.health.domain.hibernate.Authority;
import com.udm.health.domain.hibernate.UserAuthority;

@Component
public class UserAuthorityService {
private static final Logger log = LoggerFactory.getLogger(UserAuthorityService.class);
	
	@Autowired
	private UserAuthorityDao userAuthorityDao;
	
	@Autowired
	private AuthorityService authorityService;
	
	public List<UserAuthority> findByUserName(String username) {
		return userAuthorityDao.findByUserName(username);
	}
	
	public void deleteAuthorities(String username) {
		List<UserAuthority> existingAuthorities = userAuthorityDao.findByUserName(username);
		for (UserAuthority existing : existingAuthorities) {
				userAuthorityDao.delete(existing);
			}
		}
	
	public void saveAuthorities(String username, List<String> authorities) {
		List<UserAuthority> existingAuthorities = userAuthorityDao.findByUserName(username);
		for (UserAuthority existing : existingAuthorities) {
			if ( ! authorities.remove(existing.getAuthority())) {
				userAuthorityDao.delete(existing);
			}
		}
		for (String newAuthority : authorities) {
			Authority authority = authorityService.findByName(newAuthority);
			if (authority != null) {
				userAuthorityDao.save(new UserAuthority(username, authority));
			} else {
				log.warn(String.format("Not granting authority[%s] to user[%s], because the authority does not exist.", newAuthority, username));
			}
		}
	}
	
	public boolean isAuthorityInUse(Authority authority) {
		List<UserAuthority> userAuthorities = userAuthorityDao.findByAuthority(authority);
		return ! userAuthorities.isEmpty();
	}
	
	public void setUserAuthorityDao(UserAuthorityDao userAuthorityDao) {
		this.userAuthorityDao = userAuthorityDao;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}
}
