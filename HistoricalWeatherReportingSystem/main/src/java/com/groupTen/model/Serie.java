package com.groupTen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Serie {

	private String name;
	
	private List<Double[]> data=new ArrayList<Double[]>();
	
	private Map<String,String> tooltip=new HashMap<String,String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Double[]> getData() {
		return data;
	}

	public void setData(List<Double[]> data) {
		this.data = data;
	}

	public void addData(Double[] ds){
		this.data.add(ds);
	}

	public Map<String, String> getTooltip() {
		return tooltip;
	}

	public void setTooltip(Map<String, String> tooltip) {
		this.tooltip = tooltip;
	}
	
	public void setUnit(String unit){
		this.tooltip.put("valueSuffix", unit);
	}
}
