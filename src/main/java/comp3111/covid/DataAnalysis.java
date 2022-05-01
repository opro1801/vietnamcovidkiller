package comp3111.covid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javafx.util.Pair;
import org.apache.commons.csv.*;
import edu.duke.*;
import java.util.HashSet;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * 
 * Data Explorer on COVID-19
 * @author <a href=mailto:namkiu@ust.hk>Namkiu Chan</a>
 * @version	1.1
 * 
 */
public class DataAnalysis {
 
	public static CSVParser getFileParser(String dataset) {
	     FileResource fr = new FileResource("dataset/" + dataset);
	     return fr.getCSVParser(true);
		}
	

	public static String getConfirmedCases(String dataset, String iso_code) {
		String oReport = "";	
		long confirmedCases = 0;
		long numRecord = 0;
		long totalNumRecord = 0;
		
		for (CSVRecord rec : getFileParser(dataset)) {
			
			if (rec.get("iso_code").equals(iso_code)) {
				String s = rec.get("new_cases");
				if (!s.equals("")) {
					confirmedCases += Long.parseLong(s);
					numRecord++;
				}
			}		
			totalNumRecord++;
		}
		
		oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
		oReport += String.format("[Summary (%s)]\n", iso_code);
		oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
		oReport += String.format("Number of Days Reported: %,d\n", numRecord);
		
		return oReport;
	}
	
	 public static String getConfirmedDeaths(String dataset, String iso_code) {
			String oReport = "";	
			long confirmedDeaths = 0;
			long numRecord = 0;
			long totalNumRecord = 0;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("iso_code").equals(iso_code)) {
					String s = rec.get("new_deaths");
					if (!s.equals("")) {
						confirmedDeaths += Long.parseLong(s);
						numRecord++;
					}
				}		
				totalNumRecord++;
			}
			
			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
			oReport += String.format("[Summary (%s)]\n", iso_code);
			oReport += String.format("Number of Deaths: %,d\n", confirmedDeaths);
			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
			
			return oReport;
	 }
	 
	 public static String getRateOfVaccination(String dataset, String iso_code) {
			String oReport = "";	
			long fullyVaccinated = 0;
			long numRecord = 0;
			long totalNumRecord = 0;
			long population = 0;
			float rate = 0.0f;
						
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("iso_code").equals(iso_code)) {
					
					String s1 = rec.get("people_fully_vaccinated");
					String s2 = rec.get("population");		
					if (!s1.equals("") && !s2.equals("")) {
						fullyVaccinated = Long.parseLong(s1);
						population = Long.parseLong(s2);						
						numRecord++;
					}
				}		
				totalNumRecord++;
			}
			
			if (population !=0)
				rate = (float) fullyVaccinated / population * 100;
			
			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
			oReport += String.format("[Summary (%s)]\n", iso_code);
			oReport += String.format("Number of People Fully Vaccinated: %,d\n", fullyVaccinated);
			oReport += String.format("Population: %,d\n", population);
			oReport += String.format("Rate of Vaccination: %.2f%%\n", rate);
			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
			
			return oReport;
	 }
	 
	 public static long getNumberOfConfirmedCases(String dataset, String iso_code) {
			long confirmedCases = 0;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("iso_code").equals(iso_code)) {
					String s = rec.get("new_cases");
					if (!s.equals("")) {
						confirmedCases += Long.parseLong(s);
					}
				}		
			}
			
			return confirmedCases;
	 }
	 
	 public static ArrayList<Country> getAllCountries(String dataset) {
		 	long numberCountries = 0;
		    ArrayList<Country> countries = new ArrayList<Country>();
		 	HashSet<String> isoCodes = new HashSet<String>();
		 	
		 	for	(CSVRecord rec: getFileParser(dataset)) {
		 		if (!isoCodes.contains(rec.get("iso_code"))) {
		 			try {
						countries.add(new Country(rec.get("location"), 
								new DateStatus(
										rec.get("date"), 
										rec.get("total_cases"),
										rec.get("total_cases_per_million"),
										rec.get("total_deaths"),
										rec.get("total_deaths_per_million")
										)));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 			isoCodes.add(rec.get("iso_code"));
		 			numberCountries++;
		 		} else {
		 			try {
						countries.get(countries.size()-1).addDateStatus(
									new DateStatus(
											rec.get("date"),
											rec.get("total_cases"),
											rec.get("total_cases_per_million"),
											rec.get("total_deaths"),
											rec.get("total_deaths_per_million")
											)
								);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 		}
		 	}
		 	return countries;
	 }
	 
	 
	 public static HashMap<String, String> getListOfCountry(String dataset) {
		 HashMap<String, String> setOfCountry = new HashMap<>();
		 for (CSVRecord rec : getFileParser(dataset)) {
			 if(!setOfCountry.keySet().contains(rec.get("location"))) {
				 setOfCountry.put(rec.get("location"),rec.get("iso_code"));
			 }
		 }
		 return setOfCountry;
	 }
	 
	 //Factory pattern
	 @SuppressWarnings("resource")
	public static Pair<Integer,Double> getDataOfCountry(String dataset,String iso_code,String date,String type) throws InvalidInputException {

		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd,yyyy");

	     LocalDate localDate = LocalDate.parse(date, formatter);

	     for (CSVRecord rec : getFileParser(dataset)) {
	    	 int count=0;
	    	 if (rec.get("iso_code").equals(iso_code)) {
	    		
				String get_date = rec.get("date").strip();
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("M/d/yyyy");
				LocalDate this_date = LocalDate.parse(get_date, formatters);
				if(count==0) {
					if(localDate.isBefore(this_date)) {
						return new Pair<Integer,Double>(0,0.0);
					}
					count++;
				}
				if(localDate.isEqual(this_date)) {
					//Factory here
					// CASE 1 : DEATH
					if(type.equals("deaths"))
					{
						String get_death = rec.get("total_deaths");
						if(get_death==null || get_death == "") {
							return new Pair<Integer,Double>(0, (double) 0);
						}
						else {
							int total_death = Integer.parseInt(get_death.strip());
							String total_death_million = rec.get("total_deaths_per_million");
							double total_death_per_million = Double.parseDouble(total_death_million.strip());
							ArrayList<Integer> results = new ArrayList<>();
							//System.out.println("Haha1");
							return new Pair<Integer,Double>(total_death, total_death_per_million);
						}
					}
					// CASE 2 : CASE
					else if(type.equals("cases")) {
						String get_case = rec.get("total_cases");
						if(get_case==null || get_case == "") {
							return new Pair<Integer,Double>(0, (double) 0);
						}
						else {
							int total_case = Integer.parseInt(get_case.strip());
							String total_case_million = rec.get("total_cases_per_million");
							double total_case_per_million = Double.parseDouble(total_case_million.strip());
							ArrayList<Integer> results = new ArrayList<>();
							//System.out.println("Haha2");
							System.out.println(total_case+"and"+total_case_per_million);
							return new Pair<Integer,Double>(total_case, total_case_per_million);
						}
					}
				}
	    	 }	
	     }
	     //System.out.println("Haha");
	     return null;
	 }
}
class InvalidInputException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidInputException(String string) {
		super(string);
	}
	public InvalidInputException(String message, Throwable throwable) {
	    super(message, throwable);
	}
};