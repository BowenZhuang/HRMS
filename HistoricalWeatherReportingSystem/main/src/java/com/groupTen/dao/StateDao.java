//FILE          : StateDao.java
//PROJECT       : ASQL - HWRS_ETL_DataVisualization
//PROGRAMMER    : Bowen Zhuang, Linyan Li, Xiaodong Meng
//FIRST VERSION : 2015-04-23
//DESCRIPTION   : This is the data access object class which connects to database to do sql query search to get the required state names
//
package com.groupTen.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.groupTen.model.State;
public class StateDao extends JdbcDaoSupport {
	
	private State buildState(Map row){
		State state=new State();
		state.setStateName(String.valueOf(row.get("stateName")));
		state.setCode(Integer.parseInt(String.valueOf(row.get("code"))));
		state.setId(Integer.parseInt(String.valueOf(row.get("id"))));
		return state;
	}
	
	
	public List<State> getStates(String query){
		List<State> states=new ArrayList<State>();
		String sql ="select * from state where stateName like '%"+query+"%'";
		System.out.println("sql: "+ sql);
		List<Map> rows = getJdbcTemplate().queryForList(sql);
		for(Map row:rows){
			State state=this.buildState(row);
			states.add(state);
		}
		return states;
	}
	
	public List<State> getStates(){
		List<State> states=new ArrayList<State>();
		String sql ="select * from state";
		System.out.println("sql: "+ sql);
		List<Map> rows = getJdbcTemplate().queryForList(sql);
		for(Map row:rows){
			State state=this.buildState(row);
			states.add(state);
		}
		return states;
	}


	 
}
