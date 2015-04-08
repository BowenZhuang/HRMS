package com.groupTen.service;
import java.util.List;

import com.groupTen.dao.*;
import com.groupTen.model.Weather;

public class GetDataService {
	private WeatherDataDao weatherDao;

	public WeatherDataDao getWeatherDao() {
		return weatherDao;
	}

	public void setWeatherDao(WeatherDataDao weatherDao) {
		this.weatherDao = weatherDao;
	}

	public List<Weather> findAll(int userId) {
		List<Weather> list =weatherDao.findByUserId(userId);
		// TODO Auto-generated method stub
		return list;
	}

	
	
	

}
