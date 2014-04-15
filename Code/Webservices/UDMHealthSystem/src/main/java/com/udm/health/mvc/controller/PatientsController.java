package com.udm.health.mvc.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udm.health.displaytag.DefaultPaginatedList;
import com.udm.health.displaytag.UserMeasurementDecorator;
import com.udm.health.domain.hibernate.User;
import com.udm.health.domain.hibernate.UserAccess;
import com.udm.health.domain.hibernate.UserMeasurement;
import com.udm.health.security.SecurityHelper;
import com.udm.health.service.UserAccessService;
import com.udm.health.service.UserMeasurementService;
import com.udm.health.service.UserService;

@Controller
public class PatientsController {
	
	private static final int PAGE_SIZE = 50;
	@Autowired
	private SecurityHelper securityHelper;
	@Autowired
	private UserAccessService userAccessService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserMeasurementService userMeasurement;
	@Autowired
	private UserMeasurementDecorator measurementDecorator;
	
	@RequestMapping(value="/admin/patients", method = RequestMethod.GET)
	public String login(@RequestParam(required=false, defaultValue="1") int page, @RequestParam(required=false) String sort, 
			@RequestParam(value="dir", required=false, defaultValue="asc") String sortDirection, 
			@RequestParam(required=false) String firstName, @RequestParam(required=false) String lastName, 
			@RequestParam(required=false) String email,@RequestParam(required=false) String state, ModelMap model) {
		
		String userName = securityHelper.getUserName();
		List<UserAccess> userAccessList = userAccessService.findByMedicalStaffEmail(userName,  firstName, lastName, email, state, sort, sortDirection, computeStart(page), PAGE_SIZE);
		
		DefaultPaginatedList<UserAccess> partial = new DefaultPaginatedList<UserAccess>();
		partial.setFullListSize(userAccessService.recordCount(userName,  firstName, lastName, email, state));
		partial.setList(userAccessList);
		partial.setObjectsPerPage(PAGE_SIZE);
		partial.setPageNumber(page);
		
		if(StringUtils.isBlank(sort)){
			sort = "lastName";
			sortDirection = "desc";
			
		}
		partial.setSortCriterion(sort);
		partial.setSortDirection(SortOrderEnum.fromName(sortDirection + "ending"));
		
		model.put("resultSize", partial.getFullListSize());
		model.put("userAccessList", partial);
		return "patients";
	}
	
	
	
	@RequestMapping(value="/admin/patients/{id}", method=RequestMethod.GET)
	public @ResponseBody String patientDetails(@PathVariable Long id) {
		User user = userService.findUserById(id);
		List<UserMeasurement> measurementList = userMeasurement.getUserMeasurementByEmail(user.getEmail());
		
		return formatMeasurementList(measurementList, user);
	}
	
	
	private String formatMeasurementList(List<UserMeasurement> list, User user){
		StringBuilder result = new StringBuilder();
		result.append(measurementDecorator.getOpeningTable(user));
		for(UserMeasurement measurement : list){
			result.append(measurementDecorator.getRow(measurement));
		}
		result.append(measurementDecorator.getClosingTable());
		return result.toString();
	}
	
	private int computeStart(int page) {
		return (page - 1) * PAGE_SIZE;
	}
}
