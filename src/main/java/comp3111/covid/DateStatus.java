package comp3111.covid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * this class holding information for a specific date
 * @author thanhlampham
 * */
public class DateStatus {
	
	/**
	 * the current date of the status
	 * */
	private Date date;
	/**
	 * the total number of confirmed COVID-19 cases on {@link #date}
	 * */
	private int totalCases;
	/**
	 * the total number of confirmed COVID-19 cases per million people on {@link #date}
	 * */
	private double totalCasesPerMillion;
	
	private int newCases;
	/**
	 * the total number of confirmed COVID-19 deaths on {@link #date}
	 * */
	private int totalDeaths;
	
	private int newDeaths;
	/**
	 * the total number of confirmed COVID-19 deaths per million people on {@link #date} 
	 * */
	private double totalDeathsPerMillion;
	
	private int totalVaccinations;
	
	/**
	 * validates the information entered and set to 0 if the information does not exist
	 * or assign the valid value to the data members
	 * @param sDate the date of the status
	 * @param totalCases number of total confirmed cases
	 * @param totalCasesPerMillion number of total confirmed cases per million people
	 * @param totalDeaths number of total confirmed deaths
	 * @param totalDeathsPerMillion number of total confirmed deaths per million people
	 * @throws ParseException throws an exception when parsing sDate
	 * */
	public DateStatus (String sDate, String totalCases, String newCases, String totalCasesPerMillion,
				String totalDeaths, String newDeaths, String totalDeathsPerMillion, String totalVaccinations
				) throws ParseException {
		this.date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		if(totalCases == null || totalCases == "") {
			this.totalCases = 0;
		} else {
			this.totalCases = Integer.parseInt(totalCases);			
		}
		if(newCases == null || newCases == "") {
			this.newCases = 0;
		} else {
			this.newCases = Integer.parseInt(newCases);			
		}
		if(totalCasesPerMillion == null || totalCasesPerMillion == "") {
			this.totalCasesPerMillion = 0;
		} else {
			this.totalCasesPerMillion = Double.parseDouble(totalCasesPerMillion);			
		}
		if(totalDeaths == null || totalDeaths == "") {
			this.totalDeaths = 0;
		} else {
			this.totalDeaths = Integer.parseInt(totalDeaths);
		}
		if(totalDeaths == null || totalDeaths == "") {
			this.totalDeaths = 0;
		} else {
			this.totalDeaths = Integer.parseInt(totalDeaths);			
		}
		if(totalDeathsPerMillion == null || totalDeathsPerMillion == "") {
			this.totalDeathsPerMillion = 0;
		} else {
			this.totalDeathsPerMillion = Double.parseDouble(totalDeathsPerMillion);
		}
		if(totalVaccinations == null || totalVaccinations == "") {
			this.totalVaccinations = 0;
		} else {
			this.totalVaccinations = Integer.parseInt(totalVaccinations);
		}
	}
	
	
	/**
	 * @return {@link #date}
	 * */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @return {@link #totalCases}
	 * */
	public int getTotalCases() {
		return totalCases;
	}
	public int getNewCases() {
		return newCases;
	}
	/**
	 * @return {@link #totalCasesPerMillion}
	 * */
	public double getTotalCasesPerMillion() {
		return totalCasesPerMillion;
	}
	
	/**
	 * @return {@link #totalDeaths}
	 * */
	public int getTotalDeaths() {
		return totalDeaths;
	}
	
	public int getNewDeaths() {
		return newDeaths;
	}
	
	/**
	 * @return {@link #totalDeathsPerMillion}
	 * */
	public double getTotalDeathsPerMillion() {
		return totalDeathsPerMillion;
	}
	
	public int getTotalVaccinations() {
		return totalVaccinations;
	}
}
