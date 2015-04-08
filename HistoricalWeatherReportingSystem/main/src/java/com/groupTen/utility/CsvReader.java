package com.groupTen.utility;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.groupTen.model.*;
import com.opencsv.CSVReader;

public class CsvReader {
	
	private List<Weather> weathers = new ArrayList<Weather>();
	public void LoadCSVFile(String filePath)
	{
			weathers.clear();
		   try {
			CSVReader reader = new CSVReader(new FileReader(filePath), ',' , '"' , 0);
			
			String[] headLine;
			try {
				headLine = reader.readNext();
				
				for(int m = 0; m < headLine.length; m++)
				{
					headLine[m] = ReplaceWhiteSpace(headLine[m]);
				}
				
				 String[] nextLine;
				  try {
					while((nextLine = reader.readNext()) !=  null)
					  {
						Weather wTemp = new Weather();
						 for(int i = 0; i < headLine.length; i++)
						 {
						//	 System.out.println(headLine[i]);
							 if(headLine[i].equals("StateCode"))
							 {
								 String strStateCode = ReplaceWhiteSpace(nextLine[i]);
								 wTemp.setStateCode(strStateCode);
							 }
							 else if(headLine[i].equals("YearMonth"))
							 {
								 String strYearMonth = nextLine[i];
								 strYearMonth =  ReplaceWhiteSpace(strYearMonth);
								 int nYear = Integer.parseInt(strYearMonth.substring(0, 4));
								 int nMonth = Integer.parseInt(strYearMonth.substring(4, strYearMonth.length()));
								 
								 wTemp.setYear(nYear);
								 wTemp.setMonth(nMonth);
							 }
							 else if(headLine[i].equals("PCP"))
							 {
								 String strPCP = nextLine[i];
								 strPCP = ReplaceWhiteSpace(strPCP);
								 Double dPCP = Double.parseDouble(strPCP);
								 dPCP = dPCP * 25.4;
								 
								 DecimalFormat newFormat = new DecimalFormat("#.#");
								 dPCP =  Double.valueOf(newFormat.format(dPCP));
								 
								 wTemp.setPCP(dPCP);
							 }
							 else if(headLine[i].equals("CDD"))
							 {
								 String strCDD = nextLine[i];
								 strCDD = ReplaceWhiteSpace(strCDD);
								 int nCDD = Integer.parseInt(strCDD);
								 wTemp.setCDD(nCDD);
							 }
							 else if(headLine[i].equals("HDD"))
							 {
								 String strHDD = nextLine[i];
								 strHDD = ReplaceWhiteSpace(strHDD);
								 int nHDD = Integer.parseInt(strHDD);
								 wTemp.setHDD(nHDD);
							 }
							 else if(headLine[i].equals("TAVG"))
							 {
								 String strAVG = nextLine[i];
								 strAVG = ReplaceWhiteSpace(strAVG);
								 Double dAVG = Double.parseDouble(strAVG);
								 dAVG = ConvertFTOC(dAVG);
								 
								 wTemp.setTAVG(dAVG);
								 
							 }
							 else if(headLine[i].equals("TMIN"))
							 {
								 String strMIN = nextLine[i];
								 strMIN = ReplaceWhiteSpace(strMIN);
								 Double dMIN = Double.parseDouble(strMIN);
								 dMIN = ConvertFTOC(dMIN);
								 
								 wTemp.setTMIN(dMIN);
							 }
							 else if(headLine[i].equals("TMAX"))
							 {
								 String strMAX = nextLine[i];
								 strMAX = ReplaceWhiteSpace(strMAX);
								 Double  dMAX = Double.parseDouble(strMAX);
								 dMAX = ConvertFTOC(dMAX);
								 
								 wTemp.setTMAX(dMAX);
							 }
								 
						 }
						  
						weathers.add(wTemp);
						 
					  }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String  ReplaceWhiteSpace(String strTemp)
	{
		return strTemp.replaceAll("\\s", "");
	}
	
	public Double ConvertFTOC(Double dFarenheit)
	{
		Double dResult = 0.0;
		dResult = (dFarenheit - 30)/2;
		DecimalFormat newFormat = new DecimalFormat("#.#");
		dResult =  Double.valueOf(newFormat.format(dResult));
		return dResult;
	}
	
	public void OutPutContent()
	{
		for(Weather w : weathers)
		{
			System.out.println(w.GetContent());
		}
	}
	
	public  List<Weather> GetWeatherList()
	{
		return weathers;
	}

}
