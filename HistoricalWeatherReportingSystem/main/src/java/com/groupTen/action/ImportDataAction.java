package com.groupTen.action;

import java.io.File;

import com.groupTen.service.WeatherImportService;
import com.opensymphony.xwork2.ActionSupport;

public class ImportDataAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File file;
	private String contentType;
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
		
		int userId = 1; 
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
}
