package com.udm.health.mvc.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.displaytag.properties.SortOrderEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udm.health.displaytag.DefaultPaginatedList;
import com.udm.health.domain.hibernate.RequestLog;
import com.udm.health.domain.hibernate.Service;
import com.udm.health.response.ResponseStatus;
import com.udm.health.security.SecurityHelper;
import com.udm.health.service.RequestLogService;
import com.udm.health.service.ServiceService;
import com.udm.health.util.XMLUtil;

@Controller
public class RequestLogController {

	private static final int PAGE_SIZE = 50;
	private static final String VIEW_LOG_XML_ROLE = "VIEW_LOG_XML";
	private static final String ADMIN_ROLE = "ADMIN";
	private Calendar toDate = Calendar.getInstance();
	private Calendar fromDate;
	private DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
	private Logger logger = LoggerFactory.getLogger(RequestLogController.class);

	@Autowired
	private SecurityHelper securityHelper;
	
	@Autowired
	private RequestLogService requestLogService;
	
	@Autowired
	private ServiceService serviceService;
	
	private List<ResponseStatus> statusList;
	private List<Service> serviceList;

	public RequestLogController(){
		statusList = new ArrayList<ResponseStatus>(Arrays.asList(ResponseStatus.values()));
		toDate = Calendar.getInstance();	
	}
	
	

	@RequestMapping(value="/admin/logs", method=RequestMethod.GET)
	public String displayLogs(@RequestParam(required=false, defaultValue="1") int page, @RequestParam(required=false) String sort, @RequestParam(value="dir", required=false, defaultValue="asc") String sortDirection, @RequestParam(required=false) String service,  @RequestParam(required=false) String status, @RequestParam(required=false) String fromDate, @RequestParam(required=false) String toDate, ModelMap model) {
		logger.debug("Entering logs page");
				
		if(StringUtils.isBlank(toDate) && StringUtils.isBlank(fromDate) && StringUtils.isBlank(service) && StringUtils.isBlank(status)){
			toDate = getDefaultToDate();
			fromDate = getDefaultFromDate();
		}
		
		List<RequestLog> logs = requestLogService.findLogsByFilters(computeStart(page), PAGE_SIZE, service, status, sort, sortDirection, fromDate, toDate);
		
		
		DefaultPaginatedList<RequestLog> partial = new DefaultPaginatedList<RequestLog>();
		partial.setFullListSize(Long.valueOf(requestLogService.recordCount(service, status,fromDate,toDate)).intValue());
		partial.setList(logs);
		partial.setObjectsPerPage(PAGE_SIZE);
		partial.setPageNumber(page);
		
		if(StringUtils.isBlank(sort)){
			sort = "receivedDate";
			sortDirection = "desc";
			
		}
		partial.setSortCriterion(sort);
		partial.setSortDirection(SortOrderEnum.fromName(sortDirection + "ending"));	// because displaytag is stupid
		
		serviceList = serviceService.findAll();
		
		model.put("resultSize", partial.getFullListSize());
		model.put("logs", partial);
		model.put("serviceList", serviceList);
		model.put("statusList", statusList);
		return "logs";
	}


	@RequestMapping(value="/admin/logs/requestXml/{id}", method=RequestMethod.GET)
	public @ResponseBody String requestXml(@PathVariable Long id) {
		String xml = "";
		if (securityHelper.isUserInRole(VIEW_LOG_XML_ROLE)) {
			RequestLog log = requestLogService.findById(id);
			if (log != null) {
				xml = XMLUtil.prettyFormat(log.getRequestXml(),2);
				if(!securityHelper.isUserInRole(ADMIN_ROLE)){
					xml = securityHelper.removeSecurityHeaders(xml);
				}
			}
		} else {
			xml = "You do not have permission to view this.";
		}
		return xml;
	}
	
	@RequestMapping(value="/admin/logs/responseXml/{id}", method=RequestMethod.GET)
	public @ResponseBody String responseXml(@PathVariable Long id) {
		String xml = "";
		if (securityHelper.isUserInRole(VIEW_LOG_XML_ROLE)) {
			RequestLog log = requestLogService.findById(id);
			if (log != null) {
				xml = XMLUtil.prettyFormat(log.getResponseXml(),2);
			}
		} else {
			xml = "You do not have permission to view this.";
		}
		return xml;
	}

	public String getDefaultFromDate(){
		fromDate = Calendar.getInstance();
		fromDate.add(Calendar.DAY_OF_MONTH, -7);
		return dateFormat.format(fromDate.getTime());
	}
	
	
	public String getDefaultToDate(){
		return dateFormat.format(toDate.getTime());
	}
	
	private int computeStart(int page) {
		return (page - 1) * PAGE_SIZE;
	}
	
	public void setRequestLogService(RequestLogService requestLogService) {
		this.requestLogService = requestLogService;
	}

	public void setSecurityHelper(SecurityHelper securityHelper) {
		this.securityHelper = securityHelper;
	}
	
	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}
}