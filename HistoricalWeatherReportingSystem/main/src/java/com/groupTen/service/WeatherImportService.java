package com.groupTen.service;
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
	
	public void insertWeatherData(List<Weather> dataList,int nUserID){
		weatherDao.insertData(dataList(),nUserID);
	}
	
	public List<Weather> dataList(){
		
		return csvReader.GetWeatherList();
	}
	
	public void LoadWeatherData(String strFilePath)
	{
		csvReader.LoadCSVFile(strFilePath);
	}
	
	public boolean CheckUserExist(int nUserID)
	{
		boolean bExist = false;
		//Undo , User exist check through dao.
		return bExist;
	}
	
	
	 
}
