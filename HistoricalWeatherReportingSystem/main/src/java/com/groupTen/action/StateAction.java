package com.groupTen.action;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;

import com.groupTen.model.State;
import com.groupTen.service.StateService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
public class StateAction extends ActionSupport{
	
	private StateService stateService;
	
	@JSON(serialize=false)
	public StateService getStateService() {
		return stateService;
	}

	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}
	
	private String states;
	
	@JSON(name="data")
	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	private String term;
	
	
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String execute(){
		JSONObject s=new JSONObject();
		List<State> list= this.stateService.queryAllStates(term);
		JSONArray array=new JSONArray();
		for(State state:list){
			JSONObject json=new JSONObject();
			json.put("label", state.getStateName());
			json.put("value", state.getCode()+"");
			array.add(json);
		}
		s.put("source", array.toString());
		this.states=s.toString();
		System.out.println("result:"+this.states);
		return Action.SUCCESS;

	}

	
	
	
}
