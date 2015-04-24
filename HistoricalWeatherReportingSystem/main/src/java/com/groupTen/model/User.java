//FILE          : Chart.java
//PROJECT       : ASQL - HWRS_ETL_DataVisualization
//PROGRAMMER    : Bowen Zhuang, Linyan Li, Xiaodong Meng
//FIRST VERSION : 2015-04-23
//DESCRIPTION   : This is the POJO of user data 
//
package com.groupTen.model;

public class User {
	
	private int UserID;
	private String usrName;
	private String pwd;
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
