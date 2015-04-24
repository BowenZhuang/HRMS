//FILE          : StateService.java
//PROJECT       : ASQL - HWRS_ETL_DataVisualization
//PROGRAMMER    : Bowen Zhuang, Linyan Li, Xiaodong Meng
//FIRST VERSION : 2015-04-23
//DESCRIPTION   : This is a service layer class that is used by the action classes to interact with the Data Access Object Classes.
//					It is used to get the state/region data that is required to setup charts
//

package com.groupTen.service;

import java.util.List;

import com.groupTen.dao.StateDao;
import com.groupTen.model.State;
public class StateService {
	private StateDao stateDao;

	public StateDao getStateDao() {
		return stateDao;
	}

	public void setStateDao(StateDao stateDao) {
		this.stateDao = stateDao;
	}
	
	
	public List<State> queryAllStates(String query){
		return this.stateDao.getStates(query);
	}
	
	public List<State> getAllStates(){
		return this.stateDao.getStates();
	}
	 
}
