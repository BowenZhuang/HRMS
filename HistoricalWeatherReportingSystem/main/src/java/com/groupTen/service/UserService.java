package com.groupTen.service;

import com.groupTen.dao.UserDao;
import com.groupTen.model.User;

public class UserService {

	private UserDao userDao;
	
	
	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public User getUser(String name, String pwd){
		return userDao.getUser(name,pwd);
	}
}
