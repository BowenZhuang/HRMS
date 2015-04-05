package com.groupTen.dao;
import com.groupTen.model.*;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
public class UserDao extends JdbcDaoSupport {
	public User getUser(String userName, String pwd){
		
		User user =null;
		String sql = this.getSql(userName, pwd);
		System.out.println("sql: "+ sql);
		Integer id = (Integer)getJdbcTemplate().queryForObject(sql, Integer.class);
		
		if(id!=null){
			
			user = new User();
			user.setUserID(id.intValue());
			user.setUsrName(userName);
			user.setPwd(pwd);
		
		}
		
		return user;
	}
	
	public String getSql(String userName, String pwd){
		String sql ="";
		if(userName!=null && pwd!=null){
			
			if((!userName.trim().equals(""))){
				sql = "select ID from user where name='"+userName+"' and password='"+pwd+"';";
			}else{
				sql = "";
			}
		
		}else{
			sql = "";
		}
		return sql;
	}
}
