package com.groupTen.action;

import java.io.File;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.groupTen.service.WeatherImportService;
import com.opensymphony.xwork2.ActionSupport;
import com.groupTen.model.User; 

public class ImportDataAction extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File file;
	private String contentType;
	private Map<String,Object> session;
	
    public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	 
	private String filename;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	 
	public WeatherImportService getImportService() {
		return importService;
	}
	public void setImportService(WeatherImportService importService) {
		this.importService = importService;
	}
	private WeatherImportService importService;
	
	public String execute(){
		
		Object currentUser=this.session.get("User");
		if(currentUser==null){
			return "error";
		}
		
		
		int userId =((User)currentUser).getUserID(); 
		if(importService.CheckUserExist(userId))
		{
			System.out.println("UserID have data in database");
			
		}
		else
		{
			System.out.println("UserID have not data in database");
			importService.LoadWeatherData(this.file);
			importService.insertWeatherData(importService.dataList(), userId);
		}
		
		
		
		
		return "success";

	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
}
