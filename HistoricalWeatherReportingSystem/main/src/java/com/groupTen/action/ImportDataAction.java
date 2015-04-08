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

		System.out.println("fileName:"+file.getName());
		
		return "success";

	}
}
