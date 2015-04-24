//FILE          : LoginAction.java
//PROJECT       : ASQL - HWRS_ETL_DataVisualization
//PROGRAMMER    : Bowen Zhuang, Linyan Li, Xiaodong Meng
//FIRST VERSION : 2015-04-23
//DESCRIPTION   : This is the action class which deals with http request call service layer and the user service 
//				  to prevent anonymous data access, and to ensure system security
//
package com.groupTen.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.groupTen.model.User;
import com.groupTen.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private Map<String, Object> session;
	

	public UserService getUserService() {

	return userService;

	}

	public void setUserService(UserService userService) {

	this.userService = userService;

	}

	private String name;

	public String getName() {

	return name;

	}

	public void setName(String name) {

	this.name = name;

	}

	public String getPwd() {

	return pwd;

	}

	public void setPwd(String pwd) {

	this.pwd = pwd;

	}

	private String pwd;

	public String execute(){

	System.out.println("name:"+name+",pwd:"+pwd);

	User u=userService.getUser(name, pwd);

	if(u==null){

		return "error";

	}

	this.session.put("User", u);
	
	return "success";

	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
	
	

}
