package com.udm.health.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.udm.health.domain.hibernate.MedicalStaff;
import com.udm.health.domain.hibernate.User;
import com.udm.health.domain.hibernate.UserAccess;
import com.udm.health.security.SecurityHelper;
import com.udm.health.service.MedicalStaffService;
import com.udm.health.service.UserAccessService;
import com.udm.health.service.UserService;

@Controller
public class UserAccessController {
	
	@Autowired
	private SecurityHelper securityHelper;
	@Autowired
	private UserService userService;
	@Autowired
	private MedicalStaffService medicalStaffService;
	@Autowired
	private UserAccessService userAccessService;

	@RequestMapping(value="/admin/lookup", method=RequestMethod.GET)
	public String listUsers(ModelMap model) {

		String userName = securityHelper.getUserName();
		MedicalStaff medicalStadff = medicalStaffService.findByEmail(userName);
		List<User> userList = userService.findAvailableUserForMedicalStaffId(medicalStadff.getId());
		model.put("userList", userList);
		return "lookup";
	}
	
	@RequestMapping(value="/admin/lookup/{id}/add", method=RequestMethod.GET)
	public String addUser(@PathVariable Long id) {
		String userName = securityHelper.getUserName();
		User user = userService.findUserById(id);
		MedicalStaff medicalStaff = medicalStaffService.findByEmail(userName);
		UserAccess userAccess = new UserAccess(user, medicalStaff);
		userAccessService.saveUserAccess(userAccess);
		return "redirect:/admin/lookup";		
	}
};
