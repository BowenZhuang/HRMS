//FILE          : Chart.java
//PROJECT       : ASQL - HWRS_ETL_DataVisualization
//PROGRAMMER    : Bowen Zhuang, Linyan Li, Xiaodong Meng
//FIRST VERSION : 2015-04-23
//DESCRIPTION   : This is the POJO of chart data 
//

package com.groupTen.model;

import java.util.List;

public class Chart {

	private String title;
	
	private String yAxisTitle;
	
	private List<Serie> series;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getyAxisTitle() {
		return yAxisTitle;
	}

	public void setyAxisTitle(String yAxisTitle) {
		this.yAxisTitle = yAxisTitle;
	}

	public List<Serie> getSeries() {
		return series;
	}

	public void setSeries(List<Serie> series) {
		this.series = series;
	}
	
}
