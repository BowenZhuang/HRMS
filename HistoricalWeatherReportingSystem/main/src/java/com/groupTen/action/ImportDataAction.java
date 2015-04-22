package com.groupTen.action;

import java.io.File;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.groupTen.service.WeatherImportService;
import com.opensymphony.xwork2.ActionSupport;
import com.groupTen.model.User; 
import org.apache.log4j.Logger;
import java.util.Date;

public class ImportDataAction extends ActionSupport implements SessionAware {
	
	/**
	 * 
	 */
	static Logger log = Logger.getLogger(ImportDataAction.class);
	static String appName = "HWRS";
	
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
		Date time;
		
//		// log: AGREE to over-write
//		time = new Date();	
//		log.info(appName + ":/t" +  time.toString() + ": The user '" + ((User)currentUser).getUsrName() + "' has agreed to overwrite their dababase data using the uploaded file;");
//		return "error";
		
		// log: DISAGREE to over-write
//		time = new Date();	
//		log.info(appName + ":/t" +  time.toString() + ": The user '" + ((User)currentUser).getUsrName() + "' has disagreed to overwrite their dababase;");
//		return "error";		

		if(importService.CheckUserExist(userId))
		{
			System.out.println("UserID have data in database");
																					
			time = new Date();													//log data existing
			log.info(appName + ":/t" +  time.toString() + ": The user '" + ((User)currentUser).getUsrName() + "' has data existing in the database;");
			
			return "error";
			
		}
		else
		{
			// load data when user has no data existing in the db
			System.out.println("UserID have not data in database");
			

			boolean csvParseResult = importService.LoadWeatherData(this.file);
			
			// csv parsing fail
			if (!csvParseResult){
				time = new Date();													//log:  FAIL to parse csv
				log.info(appName + ":/t" +  time.toString() + ": The user '" + ((User)currentUser).getUsrName() + "' Attempted ETL; Result: Fail to parse the csv file;");
				return "error";
			}
			
			
			// csv parsing success
			if (csvParseResult){
				boolean dbResult = importService.insertWeatherData(importService.dataList(), userId);
				
				if (dbResult){
					time = new Date();													//log: SUCCESS  inserted every row
					log.info(appName + ":/t" +  time.toString() + ": The user '" + ((User)currentUser).getUsrName() + "' Attempted ETL; Result: SUCCESS;");
					
					time = new Date();													
					log.info(appName + ":/t" +  time.toString() + ": ETL completed;");
					
				}else{
					time = new Date();													//log: FAIL at least one row is not inserted
					log.info(appName + ":/t" +  time.toString() + ": The user '" + ((User)currentUser).getUsrName() + "' Attempted ETL; Result: FAIL;");
					
				}	
			}
				
			
		}
		
		return "success";

	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
}
