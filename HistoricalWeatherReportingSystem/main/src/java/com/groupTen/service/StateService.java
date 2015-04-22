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
