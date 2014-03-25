package com.udm.health.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.udm.health.domain.hibernate.WebUser;
import com.udm.health.mvc.controller.dto.NewWebUser;
import com.udm.health.service.UserAuthorityService;
import com.udm.health.service.WebUserService;

@Controller
public class WebUsersController {

	private static final Logger log = LoggerFactory.getLogger(WebUsersController.class);
	
	@Autowired
	private WebUserService webUserService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserAuthorityService userAuthorityService;
	
	@ModelAttribute("newUser")
	public NewWebUser createNewWebUser(ModelMap model) {
		return new NewWebUser();
	}
	
	@RequestMapping(value="/admin/users/{id}/delete", method=RequestMethod.GET)
	public String deleteUser(@PathVariable Long id) {
		WebUser webUser = webUserService.find(id);
		if(webUser != null){
			webUserService.delete(id);
			userAuthorityService.deleteAuthorities(webUser.getUsername());
		}
		return "redirect:/admin/users";		
	}
	
	@RequestMapping(value="/admin/users", method=RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<WebUser> users = webUserService.findAll();
		model.put("users", users);
		return "users";
	}
	
	@RequestMapping(value="/admin/users/{id}/changePassword/{password}", method=RequestMethod.GET)
	public @ResponseBody String changePassword(@PathVariable Long id, @PathVariable String password) {
		try {
			webUserService.changePassword(id, password);
		} catch (Exception e) {
			log.error("Failed to update password.", e);
			return e.getMessage();
		}
		return "Password changed.";
	}
	
	@RequestMapping(value="/admin/users/{id}/enable/{enabled}", method=RequestMethod.GET)
	public @ResponseBody String enable(@PathVariable Long id, @PathVariable Boolean enabled) {
		webUserService.enable(id, enabled);
		return "";
	}
	
	@RequestMapping(value="/admin/users/new", method=RequestMethod.POST)
	@Transactional
	public String newUser(@Valid @ModelAttribute NewWebUser newUser, BindingResult bindingResult, RedirectAttributes redirect) {
		if (bindingResult.hasErrors()) {
			redirect.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
			redirect.addFlashAttribute("newUser", newUser);
			return "redirect:/admin/users";
		}

		String encodedPassword = passwordEncoder.encode(newUser.getPassword());
		WebUser user = new WebUser(newUser.getUsername(), encodedPassword, newUser.getEnabled());
		
		webUserService.save(user);
		
		userAuthorityService.saveAuthorities(user.getUsername(), newUser.getPrivileges());
		
		return "redirect:/admin/users";
	}
	
	public void setWebUserService(WebUserService webUserService) {
		this.webUserService = webUserService;
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	public void setUserAuthorityService(UserAuthorityService userAuthorityService) {
		this.userAuthorityService = userAuthorityService;
	}

}
