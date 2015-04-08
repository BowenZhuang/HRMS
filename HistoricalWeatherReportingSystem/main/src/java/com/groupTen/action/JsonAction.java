package com.groupTen.action;
 

import com.groupTen.model.*;
import com.groupTen.service.GetDataService;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class JsonAction extends ActionSupport{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GetDataService dataService;
	@JSON(serialize=false)
	public GetDataService getDataService() {
		return dataService;
	}
	public void setDataService(GetDataService dataService) {
		this.dataService = dataService;
	}

	
	private List<Weather> weathers;
	@JSON(name="data")
	public List<Weather> getWeathers() {
		return weathers;
	}
	public void setWeathers(List<Weather> weathers) {
		this.weathers = weathers;
	}
	
	
	public String execute(){

		int userId = 1;
		weathers=dataService.findAll(userId);

		return Action.SUCCESS;

	}
}