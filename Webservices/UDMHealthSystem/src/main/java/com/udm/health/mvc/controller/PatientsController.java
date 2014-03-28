package com.udm.health.mvc.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.udm.health.displaytag.DefaultPaginatedList;
import com.udm.health.domain.hibernate.UserAccess;
import com.udm.health.security.SecurityHelper;
import com.udm.health.service.UserAccessService;

@Controller
public class PatientsController {
	
	private static final int PAGE_SIZE = 50;
	@Autowired
	private SecurityHelper securityHelper;
	@Autowired
	private UserAccessService userAccessService;
	
	@RequestMapping(value="/admin/patients", method = RequestMethod.GET)
	public String login(@RequestParam(required=false, defaultValue="1") int page, @RequestParam(required=false) String sort, @RequestParam(value="dir", required=false, defaultValue="asc") String sortDirection, ModelMap model) {
		String userName = securityHelper.getUserName();
		List<UserAccess> userAccessList = userAccessService.findByMedicalStaffEmail(userName, sort, sortDirection, computeStart(page), PAGE_SIZE);
		
		DefaultPaginatedList<UserAccess> partial = new DefaultPaginatedList<UserAccess>();
		partial.setFullListSize(userAccessService.recordCount(userName));
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
	
	private int computeStart(int page) {
		return (page - 1) * PAGE_SIZE;
	}
}
