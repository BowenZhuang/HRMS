//FILE          : GetDataService.java
//PROJECT       : ASQL - HWRS_ETL_DataVisualization
//PROGRAMMER    : Bowen Zhuang, Linyan Li, Xiaodong Meng
//FIRST VERSION : 2015-04-23
//DESCRIPTION   : This is a service layer class that is used by the action classes to interact with the Data Access Object Classes.
//					It is used to get data that is required to setup charts
//

package com.groupTen.service;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.groupTen.dao.*;
import com.groupTen.model.Chart;
import com.groupTen.model.Serie;

public class GetDataService {
	private WeatherDataDao weatherDao;
	private final static String Precipitation="Precipitation";
	private final static String CHDD="CHDD";
	private final static String Temperature="Temperature";
	public WeatherDataDao getWeatherDao() {
		return weatherDao;
	}

	public void setWeatherDao(WeatherDataDao weatherDao) {
		this.weatherDao = weatherDao;
	}

	
	public Chart getChart(String st,String dt,String region,int userId){
		System.out.println("query user chart: st:"+st+" dt:"+dt+" region:"+region+" userId:"+userId);
		Chart chart=null;
		StringBuilder selectedSQL=new StringBuilder();
		StringBuilder builder=new StringBuilder();
		if(dt!=null&&dt.length()>0){
			if(dt.equals("Year")){
				selectedSQL.append("select y.year year");
				builder.append(" from year y join weather w on y.year=w.year ");
			}else if(dt.equals("Monthly")){
				selectedSQL.append("select d.year year, d.month month");
				builder.append(" from test.year_month d join weather w on d.year=w.Year and d.month=w.Month ");
			}else if(dt.equals("Quarterly")){
				selectedSQL.append("select d.year year,d.quarter quarter");
				builder.append(" from test.year_month d  join weather w on d.year=w.Year and d.month=w.Month ");
			}
			builder.append(" join state s on s.code=w.stateCode ");
		}
		
		if(region!=null&&region.length()>0&&!region.equals("-1")){
			builder.append(" where w.stateCode="+region);
		}
		if(userId>0&&region!="-1"){
			builder.append(" and w.UserID="+userId);
		}else if(userId>0&&region.equals("-1")){
			builder.append(" where w.UserID="+userId);
		}
		
		if(dt!=null&&dt.length()>0){
			if(dt.equals("Year")){
				builder.append(" group by y.year ");
			}else if(dt.equals("Monthly")){
				builder.append(" group by d.year,d.month");
			}else if(dt.equals("Quarterly")){
				builder.append(" group by d.year,d.quarter");
			}
		}
		
		if(st.equals(Precipitation)){
			String baseSelectedSQL=selectedSQL.toString();
			String sql1=baseSelectedSQL+",avg(w.PCP) as pcp ";
			String[] sqls={sql1+builder.toString()};
			chart=this.getPrecipitationChart(sqls, dt);
		}
		
		
		if(st.equals(Temperature)){
			String baseSelectedSQL=selectedSQL.toString();
			String sql1=baseSelectedSQL+",avg(w.TMIN) as temperature ";
			String sql2=baseSelectedSQL+",avg(w.TMAX) as temperature ";
			String sql3=baseSelectedSQL+",avg(w.TAVG) as temperature ";
			String[] sqls={sql1+builder.toString(),sql2+builder.toString(),sql3+builder.toString()};
			chart=this.getTemperatureChart(sqls, dt);
		}
		if(st.equals(CHDD)){
			String baseSelectedSQL=selectedSQL.toString();
			String sql1=baseSelectedSQL+",avg(w.CDD) as temperature ";
			String sql2=baseSelectedSQL+",avg(w.HDD) as temperature ";
			String[] sqls={sql1+builder.toString(),sql2+builder.toString()};
			chart=this.getCHDDChart(sqls, dt);
		}
		
		return chart;
	}
	
	private long timeParse(int year,int month){
		final Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month);
	    cal.set(Calendar.MILLISECOND, 0);
	    return cal.getTimeInMillis();
	}
	
	
	private long timeParse(int year){
		final Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, year);
	    return cal.getTimeInMillis();
	}
	
	
	private long getTime(int year,int month){
		if(month<1){
			return this.timeParse(year);
		}else{
			return this.timeParse(year, month);
		}
	}
	
	
	
	public Chart getPrecipitationChart(String[] sqls,String dt){
		Chart chart=new Chart();
		chart.setTitle(dt+" Precipitation");
		chart.setyAxisTitle("Precipitation (mm)");
		List<Serie> series=new ArrayList<Serie>();
		for(String sql:sqls){
			List<Map> data=this.weatherDao.queryBySQL(sql);
			Serie serie=new Serie();
			serie.setName("PCP");
			for(int i=0;i<data.size();i++){
				Map map=(Map)data.get(i);
				int year=Integer.parseInt(String.valueOf(map.get("year")));
				String pcp=String.valueOf(map.get("pcp"));
				long time=this.getTime(year,-1);
				if(dt!=null&&dt.length()>0){
					if(dt.equals("Monthly")){
						int month=Integer.parseInt(String.valueOf(map.get("month")));
						time=this.getTime(year, month);
					}else if(dt.equals("Quarterly")){
						int quarter=Integer.parseInt(String.valueOf(map.get("quarter")));
						int month=quarter*3;
						time=this.getTime(year, month);
					}
				}
				if(pcp.equals("null")){
					Double[] values={new Double(time),0.0};
					serie.addData(values);
				}else{
					Double[] values={new Double(time),Double.parseDouble(pcp)};
					serie.addData(values);
				}
			}
			serie.setUnit("mm");
			series.add(serie);
		}
		chart.setSeries(series);
		return chart;
	}
	
	
	public Chart getCHDDChart(String[] sqls,String dt){
		Chart chart=new Chart();
		chart.setTitle(dt+" Days");
		chart.setyAxisTitle("Days");
		List<Serie> series=new ArrayList<Serie>();
		int index=0;
		for(String sql:sqls){
			List<Map> data=this.weatherDao.queryBySQL(sql);
			Serie serie=new Serie();
			if(index==0){
				serie.setName("CDD");
			}else if(index==1){
				serie.setName("HDD");
			}
			for(int i=0;i<data.size();i++){
				Map map=(Map)data.get(i);
				int year=Integer.parseInt(String.valueOf(map.get("year")));
				String temperature=String.valueOf(map.get("temperature"));
				long time=this.getTime(year,-1);
				if(dt!=null&&dt.length()>0){
					if(dt.equals("Monthly")){
						int month=Integer.parseInt(String.valueOf(map.get("month")));
						time=this.getTime(year, month);
					}else if(dt.equals("Quarterly")){
						int quarter=Integer.parseInt(String.valueOf(map.get("quarter")));
						int month=quarter*3;
						time=this.getTime(year, month);
					}
				}
				
				if(temperature.equals("null")){
					Double[] values={new Double(time),0.0};
					serie.addData(values);
				}else{
					Double[] values={new Double(time),Double.parseDouble(temperature)};
					serie.addData(values);
				}
				serie.setUnit("Days");
			}
			series.add(serie);
			index++;
		}
		chart.setSeries(series);
		return chart;
	}
	
	
	public Chart getTemperatureChart(String[] sqls,String dt){
		Chart chart=new Chart();
		chart.setTitle(dt+" Temperature");
		chart.setyAxisTitle("Temperature (°C)");
		List<Serie> series=new ArrayList<Serie>();
		
		int index=0;
		for(String sql:sqls){
			List<Map> data=this.weatherDao.queryBySQL(sql);
			Serie serie=new Serie();
			if(index==0){
				serie.setName("TMIN");
			}else if(index==1){
				serie.setName("TMAX");
			}else if(index==2){
				serie.setName("TAVG");
			}
			for(int i=0;i<data.size();i++){
				Map map=(Map)data.get(i);
				int year=Integer.parseInt(String.valueOf(map.get("year")));
				String temperature=String.valueOf(map.get("temperature"));
				long time=this.getTime(year,-1);
				if(dt!=null&&dt.length()>0){
					if(dt.equals("Monthly")){
						int month=Integer.parseInt(String.valueOf(map.get("month")));
						time=this.getTime(year, month);
					}else if(dt.equals("Quarterly")){
						int quarter=Integer.parseInt(String.valueOf(map.get("quarter")));
						int month=quarter*3;
						time=this.getTime(year, month);
					}
				}
				
				if(temperature.equals("null")){
					Double[] values={new Double(time),0.0};
					serie.addData(values);
				}else{
					Double[] values={new Double(time),Double.parseDouble(temperature)};
					serie.addData(values);
				}
			}
			serie.setUnit("Â°C");
			series.add(serie);
			index++;
		}
		chart.setSeries(series);
		return chart;
	}
	
	

}
