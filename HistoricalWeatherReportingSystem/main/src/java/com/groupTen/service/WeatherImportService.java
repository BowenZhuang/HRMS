//FILE          : WeatherImportService.java
//PROJECT       : ASQL - HWRS_ETL_DataVisualization
//PROGRAMMER    : Bowen Zhuang, Linyan Li, Xiaodong Meng
//FIRST VERSION : 2015-04-23
//DESCRIPTION   : This is a service layer class that is used by the action classes to interact with the Data Access Object Classes.
//					It is used to load database with the user's upload
//

package com.groupTen.service;
import java.io.File;
import java.util.List;

import com.groupTen.dao.WeatherDataDao;
import com.groupTen.model.Weather;
import com.groupTen.utility.*;
public class WeatherImportService {
	private WeatherDataDao weatherDao;
	
	private CsvReader csvReader = new CsvReader();
	
	public CsvReader getCsvReader() {
		return csvReader;
	}

	public void setCsvReader(CsvReader csvReader) {
		this.csvReader = csvReader;
	}

	public WeatherDataDao getWeatherDao() {
		return weatherDao;
	}

	public void setWeatherDao(WeatherDataDao weatherDao) {
		this.weatherDao = weatherDao;
	}
	
	public boolean insertWeatherData(List<Weather> dataList,int nUserID){
		return weatherDao.insertData(dataList(),nUserID);
	}
	
	public List<Weather> dataList(){
		
		return csvReader.GetWeatherList();
	}
	
	public boolean LoadWeatherData(File strFilePath)
	{
		return csvReader.LoadCSVFile(strFilePath);
	}
	
	public boolean CheckUserExist(int nUserID)
	{
		
		boolean bExist = false;
		
		//Undo , User exist check through dao.
		System.out.println("Server.CheckuserExist");
		bExist = weatherDao.checkUserExist(nUserID);
		return bExist;
	}
	
	public boolean deleteDataWithExistingUser(int nUserID)
	{
		boolean bResult = false;
		
		System.out.println("Server.Delete Data for Existing User");
		
		bResult = weatherDao.deleteDataForExistingUser(nUserID);
		return bResult;
		
	}
	
	 
}
