//FILE          : CheckUserAction.java
//PROJECT       : ASQL - HWRS_ETL_DataVisualization
//PROGRAMMER    : Bowen Zhuang, Linyan Li, Xiaodong Meng
//FIRST VERSION : 2015-04-23
//DESCRIPTION   : This is the action class which deals with http request call service layer and the weather import service 
//					logic to check if the user has existing data in the database
//
package com.groupTen.action;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;

import com.groupTen.model.User;
import com.groupTen.service.WeatherImportService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CheckUserAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = -1779270090476899134L;
	private WeatherImportService service;
	private Map<String,Object> session;
	static Logger log = Logger.getLogger(CheckUserAction.class);
	static String appName = "HWRS";
	private boolean isExist = false;
	private boolean isDelete = false;
	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}

	@JSON(serialize=false)
	public WeatherImportService getService() {
		return service;
	}
	
	public void setService(WeatherImportService service) {
		this.service = service;
	}
	
	public String execute(){
		 
		User user=(User)session.get("User");
		if(user!=null){
			int userId = user.getUserID();
			this.isExist = service.CheckUserExist(userId);
			Date time = new Date();													//log data existing
			log.info(appName + ":/t" +  time.toString() + ": The user '" + ((User)user).getUsrName() + "' has data existing in the database;");
			
		} 
		return Action.SUCCESS;

	}
	
	public String Overwrite(){
		
		User user=(User)session.get("User");
		if(user!=null){
			int userId = user.getUserID();
			this.isDelete = service.deleteDataWithExistingUser(userId);
			Date time = new Date();	
			log.info(appName + ":/t" +  time.toString() + ": The user '" + ((User)user).getUsrName() + "' has agreed to overwrite their dababase data using the uploaded file;");
		 
		} 
		return Action.SUCCESS;
		 
		

	}
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public void setSession(Map<String, Object> s) {
		this.session = s;
		
	}
	
}
