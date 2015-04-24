//FILE          : UserService.java
//PROJECT       : ASQL - HWRS_ETL_DataVisualization
//PROGRAMMER    : Bowen Zhuang, Linyan Li, Xiaodong Meng
//FIRST VERSION : 2015-04-23
//DESCRIPTION   : This is a service layer class that is used by the action classes to interact with the Data Access Object Classes.
//					It is used to get users' data to verify their identity
//

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
