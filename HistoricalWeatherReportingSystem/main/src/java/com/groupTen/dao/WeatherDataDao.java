package com.groupTen.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.groupTen.model.*;

public class WeatherDataDao extends JdbcDaoSupport {

	public void insertData(final List<Weather> dataList,final int userId){
		 String sql = "INSERT INTO weather" +
	                "(UserID,StateCode,Year,Month,CDD,HDD,PCP,TMIN,TMAX,TAVG) " +
	                "VALUES(?,?,?,?,?,?,?,?,?,?)";
	         
	        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter(){
	            @Override
	            public int getBatchSize() {
	                return dataList.size();
	            }
	            @Override
	            public void setValues(PreparedStatement ps, int i) throws SQLException {
	                Weather weather = dataList.get(i);
	                ps.setInt(1, userId);
	                ps.setString(2, weather.getStateCode());
	                ps.setInt(3, weather.getYear());
	                ps.setInt(4, weather.getMonth());
	                ps.setInt(5, weather.getCDD());
	                ps.setInt(6, weather.getHDD());
	                ps.setFloat(7, (float)weather.getPCP());
	                ps.setFloat(8, (float)weather.getTMIN());
	                ps.setFloat(9, (float)weather.getTMAX());
	                ps.setFloat(10, (float)weather.getTAVG());
	     
	            }
				
	            
	        } );
	}
	
	public List<Weather> findByUserId(int userId) {
		String sql = "SELECT * FROM weather WHERE UserID = " + userId;
		 
		List<Weather> weathers = new ArrayList<Weather>();
	
		List<Map> rows = getJdbcTemplate().queryForList(sql);
		for (Map row : rows) {
			Weather weather = new Weather();
			
			weather.setUserID((int)row.get("UserID"));
			weather.setState((String)row.get("stateName"));
			weather.setYear((int)row.get("Year"));
			weather.setMonth((int)row.get("Month"));
			weather.setCDD((int)row.get("CDD"));
			weather.setHDD((int)row.get("HDD"));
			weather.setPCP((float)row.get("PCP"));
			weather.setTMIN((float)row.get("TMIN"));
			weather.setTMAX((float)row.get("TMAX"));
			weather.setTAVG((float)row.get("TAVG"));
			
			weathers.add(weather);
		}
		
		return weathers;
		// TODO Auto-generated method stub
		
	} 
}
