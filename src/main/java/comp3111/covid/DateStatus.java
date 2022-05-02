package comp3111.covid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * holding total cases, total cases per million, new cases, total deaths, total deaths per million, new deaths and total vaccinations per hundred for a specific date
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
	
	private double totalVaccinationsPerHundred;
	
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
				String totalDeaths, String newDeaths, String totalDeathsPerMillion, String totalVaccinationsPerHundred
				) throws ParseException {
		this.date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		if(totalCases == null || totalCases.length() == 0) {
			this.totalCases = 0;
		} else {
			this.totalCases = Integer.parseInt(totalCases);			
		}
		if(newCases == null || newCases.length() == 0) {
			this.newCases = 0;
		} else {
			this.newCases = Integer.parseInt(newCases);			
		}
		if(totalCasesPerMillion == null || totalCasesPerMillion.length() == 0) {
			this.totalCasesPerMillion = 0;
		} else {
			this.totalCasesPerMillion = Double.parseDouble(totalCasesPerMillion);			
		}
		if(totalDeaths == null || totalDeaths.length() == 0) {
			this.totalDeaths = 0;
		} else {
			this.totalDeaths = Integer.parseInt(totalDeaths);
		}
		if(newDeaths == null || newDeaths.length() == 0) {
			this.newDeaths = 0;
		} else {
			this.newDeaths = Integer.parseInt(newDeaths);			
		}
		if(totalDeathsPerMillion == null || totalDeathsPerMillion.length() == 0) {
			this.totalDeathsPerMillion = 0;
		} else {
			this.totalDeathsPerMillion = Double.parseDouble(totalDeathsPerMillion);
		}
		if(totalVaccinationsPerHundred == null || totalVaccinationsPerHundred.length() == 0) {
			this.totalVaccinationsPerHundred = 0;
		} else {
			this.totalVaccinationsPerHundred = Double.parseDouble(totalVaccinationsPerHundred);
		}
	}
	
	
	/**
	 * Getter for date
	 * @return {@link #date}
	 * */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Getter for total cases
	 * @return {@link #totalCases}
	 * */
	public int getTotalCases() {
		return totalCases;
	}
	public int getNewCases() {
		return newCases;
	}
	/**
	 * Getter for totalCasesPerMillion
	 * @return {@link #totalCasesPerMillion}
	 * */
	public double getTotalCasesPerMillion() {
		return totalCasesPerMillion;
	}
	
	/**
	 * Getter for totalDeaths
	 * @return {@link #totalDeaths}
	 * */
	public int getTotalDeaths() {
		return totalDeaths;
	}
	/**
	 * Getter for newDeaths
	 * @return {@link #newDeaths}
	 * */
	public int getNewDeaths() {
		return newDeaths;
	}
	
	/**
	 * Getter for totaDeathsPerMillion
	 * @return {@link #totalDeathsPerMillion}
	 * */
	public double getTotalDeathsPerMillion() {
		return totalDeathsPerMillion;
	}
	
	/**
	 * Getter for totalVaccinationsPerHundred
	 * @return {@link #totalVaccinationsPerHundred}
	 * */
	public double getTotalVaccinationsPerHundred() {
		return totalVaccinationsPerHundred;
	}
}
