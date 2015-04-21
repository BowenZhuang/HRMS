package com.groupTen.action;
 

import com.groupTen.model.*;
import com.groupTen.service.GetDataService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class JsonAction extends ActionSupport implements SessionAware,ServletRequestAware {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GetDataService dataService;
	private Map<String,Object> session;
	private HttpServletRequest request;
	@JSON(serialize=false)
	public GetDataService getDataService() {
		return dataService;
	}
	public void setDataService(GetDataService dataService) {
		this.dataService = dataService;
	}

	private Chart chart;
	
	@JSON(name="data")
	public Chart getChart() {
		return chart;
	}
	public void setChart(Chart chart) {
		this.chart = chart;
	}
	
	public String execute(){
		
		//if(session.get("User")==null){
		//	return Action.ERROR;
		//}
		User user=(User)session.get("User");
		int userId = user.getUserID();
		String st=this.request.getParameter("st");
		String dt=this.request.getParameter("dt");
		String region=this.request.getParameter("region");
		this.chart=this.dataService.getChart(st, dt, region,userId);	
		System.out.println(this.chart);
		return Action.SUCCESS;

	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
}