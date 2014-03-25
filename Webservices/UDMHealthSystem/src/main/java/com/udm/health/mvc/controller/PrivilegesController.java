package com.udm.health.mvc.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udm.health.domain.hibernate.Authority;
import com.udm.health.domain.hibernate.UserAuthority;
import com.udm.health.service.AuthorityService;
import com.udm.health.service.UserAuthorityService;
import com.udm.health.validation.Validator;

@Controller
public class PrivilegesController {
	
private static final Logger log = LoggerFactory.getLogger(PrivilegesController.class);
	
	@Autowired
	private UserAuthorityService userAuthorityService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private Validator validator;

	

	
	@RequestMapping(value="/admin/privileges", method=RequestMethod.GET)
	public String list(ModelMap model) {
		List<Authority> authorities = authorityService.findAll();
		model.put("privileges", authorities);
		return "privileges";
	}
	
	@RequestMapping(value="/admin/privileges/all", method=RequestMethod.GET)
	public @ResponseBody List<String> findAllPrivileges() {
		List<Authority> authorities = authorityService.findAll();
		return (List<String>) CollectionUtils.collect(authorities, new Transformer() {
			@Override
			public Object transform(Object input) {
				return ((Authority) input).getAuthorityType();
			}
		});
	}
	
	@RequestMapping(value="/admin/privileges/assigned/{username}", method=RequestMethod.GET)
	public @ResponseBody List<String> findAssignedPrivileges(@PathVariable String username) {
		List<UserAuthority> authorities = userAuthorityService.findByUserName(username);
		return (List<String>) CollectionUtils.collect(authorities, new Transformer() {
			@Override
			public Object transform(Object input) {
				return ((UserAuthority)input).getAuthority().getAuthorityType();
			}
		});
	}
	
	@RequestMapping(value="/admin/privileges/savePrivileges/{username}", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody String savePrivileges(@PathVariable String username, @RequestBody List<String> privileges) {
		try {
			userAuthorityService.saveAuthorities(username, privileges);
		} catch (Exception e) {
			log.warn("Failed to update privileges.", e);
			return e.getMessage();
		}
		return "Privileges updated.";
	}
	
	
	@RequestMapping(value="/admin/privileges/{id}/delete", method=RequestMethod.GET)
	public String deletePrivilege(@PathVariable Long id, RedirectAttributes redirect) {
		Authority authority = authorityService.findById(id);
		if (authority != null) {
			boolean inUse = userAuthorityService.isAuthorityInUse(authority);
			if (inUse) {
				redirect.addFlashAttribute("message", "Cannot delete privilege because it is in use.");
			} else {
				authorityService.delete(authority);
			}
		}
		return "redirect:/admin/privileges";
	}
	
	
	
	public void setUserAuthorityService(UserAuthorityService userAuthorityService) {
		this.userAuthorityService = userAuthorityService;
	}
	
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}
	

	public void setValidator(Validator validator) {
		this.validator = validator;
	}
}
