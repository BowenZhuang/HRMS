package com.groupTen.model;

public class Weather {
	private String StateCode;
	private int Year;
	private  int Month;
	private double PCP;
	private double TAVG;
	private double TMIN;
	private double TMAX;
	private int  CDD;
	private int  HDD;
	
	public Weather(String stateCode, int year, int month, double pcp, double tavg, double tmin, double tmax, int cdd, int hdd)
	{
		this.StateCode = stateCode;
		this.Year = year;
		this.Month = month;
		this.PCP = pcp;
		this.TAVG = tavg;
		this.TMIN = tmin;
		this.TMAX = tmax;
		this.CDD = cdd;
		this.HDD = hdd;
	}
	
	public String getStateCode() {
		return StateCode;
	}

	public void setStateCode(String stateCode) {
		StateCode = stateCode;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public int getMonth() {
		return Month;
	}

	public void setMonth(int month) {
		Month = month;
	}

	public double getPCP() {
		return PCP;
	}

	public void setPCP(double pCP) {
		PCP = pCP;
	}

	public double getTAVG() {
		return TAVG;
	}

	public void setTAVG(double tAVG) {
		TAVG = tAVG;
	}

	public double getTMIN() {
		return TMIN;
	}

	public void setTMIN(double tMIN) {
		TMIN = tMIN;
	}

	public double getTMAX() {
		return TMAX;
	}

	public void setTMAX(double tMAX) {
		TMAX = tMAX;
	}

	public int getCDD() {
		return CDD;
	}

	public void setCDD(int cDD) {
		CDD = cDD;
	}

	public int getHDD() {
		return HDD;
	}

	public void setHDD(int hDD) {
		HDD = hDD;
	}

	public Weather()
	{
		
	}
	
	public String GetContent()
	{
		return this.StateCode + " " + Integer.toString(this.Year) + " " + Integer.toString(this.Month) + " " + Double.toString(this.PCP) + " " + Double.toString(this.TAVG) + " " + Integer.toString(this.CDD) + 
				" " + Integer.toString(this.HDD) + " " + Double.toString(this.TMIN) + " " + Double.toString(this.TMAX);
	}

}
