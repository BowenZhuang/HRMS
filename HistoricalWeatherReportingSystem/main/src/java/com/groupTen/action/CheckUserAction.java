package com.groupTen.action;

import java.util.Map;

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
			return Action.SUCCESS;
		}else{
			return Action.LOGIN;
		}
		

	}
	
	public String Overwrite(){
		 
		User user=(User)session.get("User");
		if(user!=null){
			int userId = user.getUserID();
			this.isDelete = service.deleteDataWithExistingUser(userId);
			return Action.SUCCESS;
		}else{
			return Action.LOGIN;
		}
		

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
