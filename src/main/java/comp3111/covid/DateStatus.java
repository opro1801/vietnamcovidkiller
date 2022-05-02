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
	private long totalCases;
	/**
	 * the total number of confirmed COVID-19 cases per million people on {@link #date}
	 * */
	private double totalCasesPerMillion;
	
	private long newCases;
	/**
	 * the total number of confirmed COVID-19 deaths on {@link #date}
	 * */
	private long totalDeaths;
	
	private long newDeaths;
	/**
	 * the total number of confirmed COVID-19 deaths per million people on {@link #date} 
	 * */
	private double totalDeathsPerMillion;
	
	private double totalVaccinationsPerHundred;
	
	private long fullyVaccinated;
	
	/**
	 * validates the information entered and set to 0 if the information does not exist
	 * or assign the valid value to the data members
	 * @param sDate the date of the status
	 * @param totalCases number of total confirmed cases
	 * @param totalCasesPerMillion number of total confirmed cases per million people
	 * @param totalDeaths number of total confirmed deaths
	 * @param totalDeathsPerMillion number of total confirmed deaths per million people
	 * @param totalVaccinationsPerHundred number of vaccinations per hundred
	 * @param fullyVaccinated number of people fully vaccinated
	 * @throws ParseException throws an exception when parsing sDate
	 * */
	public DateStatus (String sDate, String totalCases, String newCases, String totalCasesPerMillion,
				String totalDeaths, String newDeaths, String totalDeathsPerMillion, String totalVaccinationsPerHundred, String fullyVaccinated
				) throws ParseException {
		this.date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		if(totalCases == null || totalCases.length() == 0) {
			this.totalCases = 0;
		} else {
			this.totalCases = Long.parseLong(totalCases);			
		}
		if(newCases == null || newCases.length() == 0) {
			this.newCases = 0;
		} else {
			this.newCases = Long.parseLong(newCases);			
		}
		if(totalCasesPerMillion == null || totalCasesPerMillion.length() == 0) {
			this.totalCasesPerMillion = 0;
		} else {
			this.totalCasesPerMillion = Double.parseDouble(totalCasesPerMillion);			
		}
		if(totalDeaths == null || totalDeaths.length() == 0) {
			this.totalDeaths = 0;
		} else {
			this.totalDeaths = Long.parseLong(totalDeaths);
		}
		if(newDeaths == null || newDeaths.length() == 0) {
			this.newDeaths = 0;
		} else {
			this.newDeaths = Long.parseLong(newDeaths);			
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
		if(fullyVaccinated == null || fullyVaccinated.length() == 0) {
			this.fullyVaccinated = 0;
		} else {
			this.fullyVaccinated = Long.parseLong(fullyVaccinated);
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
	public long getTotalCases() {
		return totalCases;
	}
	public long getNewCases() {
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
	public long getTotalDeaths() {
		return totalDeaths;
	}
	/**
	 * Getter for newDeaths
	 * @return {@link #newDeaths}
	 * */
	public long getNewDeaths() {
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
	/**
	 * Getter for fullyVaccinated
	 * @return {@link #fullyVaccinated}
	 * */
	public long getFullyVaccinated() {
		return fullyVaccinated;
	}
}
