package com.udm.health.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udm.health.domain.hibernate.MedicalStaff;
import com.udm.health.domain.hibernate.User;
import com.udm.health.domain.hibernate.WebUser;
import com.udm.health.mvc.controller.dto.NewMedicalStaff;
import com.udm.health.service.MedicalStaffService;
import com.udm.health.service.UserAccessService;
import com.udm.health.service.UserAuthorityService;
import com.udm.health.service.UserService;
import com.udm.health.service.WebUserService;

@Controller
public class MedicalStaffController {
	
	@Autowired
	private MedicalStaffService medicalStaffService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private UserAuthorityService userAuthorityService;
	
	@Autowired 
	private UserAccessService userAccessService;
	
	@ModelAttribute("newUser")
	public NewMedicalStaff createNewMedicalStaff(ModelMap model) {
		return new NewMedicalStaff();
	}
	
	
	@RequestMapping(value="/admin/staff", method=RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<MedicalStaff> staffList = medicalStaffService.findAll();
		model.put("staffList", staffList);
		return "staff";
	}
	
	
	@RequestMapping(value="/admin/staff/new", method=RequestMethod.POST)
	@Transactional
	public String newUser(@Valid @ModelAttribute NewMedicalStaff newUser, BindingResult bindingResult, RedirectAttributes redirect) {
		if (bindingResult.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
			redirect.addFlashAttribute("newUser", newUser);
			return "redirect:/admin/staff";
		}

		User user = userService.createUser(newUser);
		
		medicalStaffService.createMedicalStaff(user, newUser.getSpeciality());
		
		String encodedPassword = passwordEncoder.encode(newUser.getPassword());
		WebUser webUser = new WebUser(newUser.getUsername(), encodedPassword, newUser.getEnabled());
		
		webUserService.save(webUser);
		
		return "redirect:/admin/staff";
	}
	
	
	@RequestMapping(value="/admin/staff/{id}/changePassword/{password}", method=RequestMethod.GET)
	public @ResponseBody String changePassword(@PathVariable Long id, @PathVariable String password) {
		try {
			MedicalStaff medicalStaff = medicalStaffService.findById(id);
			WebUser webUser = webUserService.findByUserName(medicalStaff.getUser().getEmail());
			String encodedPassword = passwordEncoder.encode(password);
			userService.changePassword(medicalStaff.getUser().getIdUser(), encodedPassword);
			webUserService.changePassword(webUser.getId(), password);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Password changed.";
	}
	
	
	@RequestMapping(value="/admin/staff/{id}/delete", method=RequestMethod.GET)
	public String deleteUser(@PathVariable Long id) {
		MedicalStaff medicalStaff = medicalStaffService.findById(id);
		WebUser webUser = webUserService.findByUserName(medicalStaff.getUser().getEmail());
		if(webUser != null){
			userAccessService.deleteUserAccess(id);
			medicalStaffService.delete(medicalStaff);
			webUserService.delete(webUser.getId());
			userAuthorityService.deleteAuthorities(webUser.getUsername());
			
		}
		return "redirect:/admin/staff";		
	}
	
	

}
