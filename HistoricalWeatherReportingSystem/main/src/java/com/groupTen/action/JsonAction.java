//FILE          : GetRegionAction.java
//PROJECT       : ASQL - HWRS_ETL_DataVisualization
//PROGRAMMER    : Bowen Zhuang, Linyan Li, Xiaodong Meng
//FIRST VERSION : 2015-04-23
//DESCRIPTION   : This is the action class which deals with http request call service layer, the session and the data service 
//				  to collect user's selection and get the right data loaded to the chart
//
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