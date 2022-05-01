package comp3111.covid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author thanhlampham
 * this class holding information for a specific date
 * {@value #date} date is the date entered
 * {@value #totalCases} totalCases is the total number of confirmed COVID-19 cases on {@link #date}
 * {@value #totalCasesPerMillion} totalCasesPerMillion is the total number of confirmed COVID-19 cases per million people on {@link #date} 
 * {@value #totalDeaths} totalDeaths is the total number of confirmed COVID-19 deaths on {@link #date}
 * {@value #totalCasesPerMillion} totalDeathsPerMillion is the total number of confirmed COVID-19 deaths per million people on {@link #date} 
 * */
public class DateStatus {
	private Date date;
	private int totalCases;
	private float totalCasesPerMillion;
	private int totalDeaths;
	private float totalDeathsPerMillion;
	
	/**
	 * constructor
	 * the constructor validates the information entered and set to 0 if the information does not exist
	 * or assign the valid value to the data members
	 * @throws ParseException throws an exception when parsing sDate
	 * */
	public DateStatus (String sDate, String totalCases, String totalCasesPerMillion,
				String totalDeaths, String totalDeathsPerMillion
				) throws ParseException {
		this.date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		if(totalCases == null || totalCases == "") {
			this.totalCases = 0;
		} else {
			this.totalCases = Integer.parseInt(totalCases);			
		}
		if(totalCasesPerMillion == null || totalCasesPerMillion == "") {
			this.totalCasesPerMillion = 0;
		} else {
			this.totalCasesPerMillion = Float.parseFloat(totalCasesPerMillion);			
		}
		if(totalDeaths == null || totalDeaths == "") {
			this.totalDeaths = 0;
		} else {
			this.totalDeaths = Integer.parseInt(totalDeaths);
		}
		if(totalDeathsPerMillion == null || totalDeathsPerMillion == "") {
			this.totalDeathsPerMillion = 0;
		} else {
			this.totalDeathsPerMillion = Float.parseFloat(totalDeathsPerMillion);
		}
	}
	
	
	/**
	 * get {@link #date} 
	 * */
	public Date getDate() {
		return date;
	}
	
	/**
	 * get {@link #totalCases}
	 * */
	public int getTotalCases() {
		return totalCases;
	}
	
	/**
	 * get {@link #totalCasesPerMillion}
	 * */
	public float getTotalCasesPerMillion() {
		return totalCasesPerMillion;
	}
	
	/**
	 * get {@link #totalDeaths}
	 * */
	public int getTotalDeaths() {
		return totalDeaths;
	}
	
	/**
	 * get {@link #totalDeathsPerMillion}
	 * */
	public float getTotalDeathsPerMillion() {
		return totalDeathsPerMillion;
	}
}
