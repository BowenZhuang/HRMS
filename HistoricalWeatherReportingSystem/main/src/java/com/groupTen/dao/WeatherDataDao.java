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
}
