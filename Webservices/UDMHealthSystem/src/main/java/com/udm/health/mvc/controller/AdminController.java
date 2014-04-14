package com.udm.health.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.udm.health.domain.hibernate.User;
import com.udm.health.security.SecurityHelper;
import com.udm.health.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private SecurityHelper securityHelper;
	@Autowired
	private UserService userService;

	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public String login(ModelMap model) {
		String userName = securityHelper.getUserName();
		User user = userService.findUserByEmail(userName);
		model.put("user", user);
		return "admin";
	}
	
}
