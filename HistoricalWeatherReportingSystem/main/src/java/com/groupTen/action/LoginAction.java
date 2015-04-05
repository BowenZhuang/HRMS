package com.groupTen.action;
import com.groupTen.model.User;
import com.groupTen.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

	 
	private UserService userService;
	

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

	return "success";

	}
	
	

}
