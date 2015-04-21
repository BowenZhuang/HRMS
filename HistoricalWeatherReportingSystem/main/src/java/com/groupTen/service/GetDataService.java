package com.groupTen.service;
import java.util.ArrayList;
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
				builder.append(" from year y left join weather w on y.year=w.year ");
			}else if(dt.equals("Monthly")){
				selectedSQL.append("select d.year year, d.month month");
				builder.append(" from test.year_month d left join weather w on d.year=w.Year and d.month=w.Month ");
			}else if(dt.equals("Quarterly")){
				selectedSQL.append("select d.year year,d.quarter quarter");
				builder.append(" from test.year_month d left join weather w on d.year=w.Year and d.month=w.Month ");
			}
			builder.append(" left join state s on s.code=w.stateCode ");
		}
		
		if(region!=null&&region.length()>0){
			builder.append(" and s.code='"+region+"'");
		}
		if(userId>0){
			builder.append(" and w.UserID="+userId);
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
	
	public Chart getPrecipitationChart(String[] sqls,String dt){
		Chart chart=new Chart();
		chart.setTitle(dt+" Precipitation");
		chart.setyAxisTitle("Precipitation (mm)");
		List<String> xAxis=new ArrayList<String>();
		List<Serie> series=new ArrayList<Serie>();
		for(String sql:sqls){
			List<Map> data=this.weatherDao.queryBySQL(sql);
			Serie serieMin=new Serie();
			serieMin.setName("PCP");
			for(Map map:data){
				String year=String.valueOf(map.get("year"));
				String pcp=String.valueOf(map.get("pcp"));
				if(xAxis.size()<=data.size()){
					if(dt!=null&&dt.length()>0){
						if(dt.equals("Monthly")){
							year+="-"+String.valueOf(map.get("month"));
						}else if(dt.equals("Quarterly")){
							year+="-"+String.valueOf(map.get("quarter"));
						}
					}
					xAxis.add(year);
				}
				
				if(pcp.equals("null")){
					serieMin.addData(0.0);
				}else{
					serieMin.addData(Double.parseDouble(pcp));
				}
			}
			series.add(serieMin);
		}
		chart.setxAxis(xAxis);
		chart.setSeries(series);
		return chart;
	}
	
	
	public Chart getCHDDChart(String[] sqls,String dt){
		Chart chart=new Chart();
		chart.setTitle(dt+" Temperature");
		chart.setyAxisTitle("Temperature (°C)");
		List<String> xAxis=new ArrayList<String>();
		List<Serie> series=new ArrayList<Serie>();
		
		int index=0;
		for(String sql:sqls){
			List<Map> data=this.weatherDao.queryBySQL(sql);
			Serie serieMin=new Serie();
			if(index==0){
				serieMin.setName("CDD");
			}else if(index==1){
				serieMin.setName("HDD");
			}
			for(Map map:data){
				String year=String.valueOf(map.get("year"));
				String temperature=String.valueOf(map.get("temperature"));
				if(xAxis.size()<=data.size()){
					if(dt!=null&&dt.length()>0){
						if(dt.equals("Monthly")){
							year+="-"+String.valueOf(map.get("month"));
						}else if(dt.equals("Quarterly")){
							year+="-"+String.valueOf(map.get("quarter"));
						}
					}
					xAxis.add(year);
				}
				
				if(temperature.equals("null")){
					serieMin.addData(0.0);
				}else{
					serieMin.addData(Double.parseDouble(temperature));
				}
			}
			series.add(serieMin);
			index++;
		}
		chart.setxAxis(xAxis);
		chart.setSeries(series);
		return chart;
	}
	
	
	public Chart getTemperatureChart(String[] sqls,String dt){
		Chart chart=new Chart();
		chart.setTitle(dt+" Temperature");
		chart.setyAxisTitle("Temperature (°C)");
		List<String> xAxis=new ArrayList<String>();
		List<Serie> series=new ArrayList<Serie>();
		
		int index=0;
		for(String sql:sqls){
			List<Map> data=this.weatherDao.queryBySQL(sql);
			Serie serieMin=new Serie();
			if(index==0){
				serieMin.setName("TMIN");
			}else if(index==1){
				serieMin.setName("TMAX");
			}else if(index==2){
				serieMin.setName("TAVG");
			}
			for(Map map:data){
				String year=String.valueOf(map.get("year"));
				String temperature=String.valueOf(map.get("temperature"));
				if(xAxis.size()<=data.size()){
					if(dt!=null&&dt.length()>0){
						if(dt.equals("Monthly")){
							year+="-"+String.valueOf(map.get("month"));
						}else if(dt.equals("Quarterly")){
							year+="-"+String.valueOf(map.get("quarter"));
						}
					}
					xAxis.add(year);
				}
				
				if(temperature.equals("null")){
					serieMin.addData(0.0);
				}else{
					serieMin.addData(Double.parseDouble(temperature));
				}
			}
			series.add(serieMin);
			index++;
		}
		chart.setxAxis(xAxis);
		chart.setSeries(series);
		return chart;
	}
	
	

}
