//FILE          : GetRegionAction.java
//PROJECT       : ASQL - HWRS_ETL_DataVisualization
//PROGRAMMER    : Bowen Zhuang, Linyan Li, Xiaodong Meng
//FIRST VERSION : 2015-04-23
//DESCRIPTION   : This is the action class which deals with http request call service layer and the regions retrieving service 
//				  to get all regions from the database
//
package com.groupTen.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.groupTen.dao.StateDao;
import com.groupTen.model.State; 
import com.groupTen.service.StateService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class GetRegionAction extends ActionSupport {
	 
	private static final long serialVersionUID = -6011583833177300597L;
	 
	private StateService stateService; 
	private List<State> states;
	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	@JSON(serialize=false)
	public StateService getStateService() {
		return stateService;
	}

	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}

	public String execute(){
			
		states = stateService.getAllStates();	 
			 
		return Action.SUCCESS;
	
	}

}
