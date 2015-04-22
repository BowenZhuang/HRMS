package com.groupTen.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Types;
import java.util.HashMap;



import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.groupTen.model.*;

public class WeatherDataDao extends JdbcDaoSupport {
	
	public boolean insertData(final List<Weather> dataList,final int userId){
		
		boolean ret = true;
		
		 String sql = "INSERT INTO weather" +
	                "(UserID,StateCode,Year,Month,CDD,HDD,PCP,TMIN,TMAX,TAVG) " +
	                "VALUES(?,?,?,?,?,?,?,?,?,?)";
		 int result[] = getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter(){
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
		 
		 for (int rowResult : result){
			 if (rowResult == 0){
				 
				 ret = false;    // return when one row is not inserted properly
			 }
		 }
		 
		 return ret;
	}
	
	
	
	
	private Weather buildWeather(Map row){
		Weather weather = new Weather();
		weather.setStateCode(String.valueOf(row.get("stateName")));
		weather.setYear(Integer.parseInt(String.valueOf(row.get("Year"))));
		weather.setMonth(Integer.parseInt(String.valueOf(row.get("Month"))));
		weather.setCDD(Integer.parseInt(String.valueOf(row.get("CDD"))));
		weather.setHDD(Integer.parseInt(String.valueOf(row.get("HDD"))));
		weather.setPCP(Float.parseFloat(String.valueOf(row.get("PCP"))));
		weather.setTMIN(Float.parseFloat(String.valueOf(row.get("TMIN"))));
		weather.setTMAX(Float.parseFloat(String.valueOf(row.get("TMAX"))));
		weather.setTAVG(Float.parseFloat(String.valueOf(row.get("TAVG"))));
		return weather;
	}
	
	
	public List<Weather> findByUserId(int userId) {
				
		String sql = "SELECT UserID, s.stateName, `Year`,`Month`,CDD, HDD,PCP,TMIN,TMAX, TAVG FROM weather JOIN state s ON s.code = weather.stateCode AND UserID = " + userId;
		List<Weather> weathers = new ArrayList<Weather>();
	
		List<Map> rows = getJdbcTemplate().queryForList(sql);
		for(Map row:rows){
			Weather weather=this.buildWeather(row);
			weathers.add(weather);
		}
		return weathers;
		
	}
	
	public List<Map> queryBySQL(String sql){
		System.out.println("execute sql:"+sql);
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	
	public boolean checkUserExist(int nUserID)
	{
		System.out.println("Dao.checkUserExist");
		boolean bResult = false;
		String sql  = "Select count(*) From test.weather where UserID = " + nUserID;
		int count = getJdbcTemplate().queryForInt(sql);
		
		if(count> 0 )
		{
			bResult = true;
		}
		
		return bResult;
	}


	
	public boolean deleteDataForExistingUser(int nUserID)
	{
		System.out.println("Delete Data For Existing User");
		boolean bResult = false;
		
		String	sql = " Delete from test.weather where UserID = ?";
	
		Object[] params = {nUserID};
		int[] types = {Types.BIGINT};
		int count = getJdbcTemplate().update(sql, params, types);
		
		if(count > 0)
		{
			bResult  = true;
		}
		
		return bResult;
	
	}
}
