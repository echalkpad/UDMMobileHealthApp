package com.udm.health.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udm.health.domain.hibernate.State;

@Controller
public class StatesController {
	
	@RequestMapping(value="/admin/states/all", method=RequestMethod.GET)
	public @ResponseBody List<String> findAllStates() {
		List<String> stateList = getAllStates();
		return stateList;
	}
	
	private List<String> getAllStates(){
		List<String> stateList = new ArrayList<String>();
		for(State state: State.values()){
			stateList.add(state.toString());
		}
		return stateList;
	}

}
