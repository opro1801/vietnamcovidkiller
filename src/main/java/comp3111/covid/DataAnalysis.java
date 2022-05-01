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
 
	/**
	 * @param dataset is the name of the dataset
	 * @return get the parser for the selected file
	 * */
	
	public static CSVParser getFileParser(String dataset) {
	     FileResource fr = new FileResource("dataset/" + dataset);
	     return fr.getCSVParser(true);
		}
	
	/**
	 * @param dataset is the name of the dataset
	 * @param iso_code is an iso_code for the selected country
	 * This method read the dataset and form a report about total number of record,
	 * number of confirmed cases and number of days reported for a selected country
	 * @return oReport is the report in String
	 * */
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
	
	/**
	 * @param dataset is the name of the dataset
	 * @param iso_code is an iso_code for the selected country
	 * This method read the dataset and form a report about total number of record,
	 * number of confirmed deaths and number of days reported for a selected country
	 * @return oReport is the report in String
	 * */
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
	 
	 
	/**
	 * @param dataset is the name of the dataset
	 * @param iso_code is an iso_code for the selected country
	 * This method read the dataset and form a report about total number of record,
	 * number of fully vaccinated people, population, rate of vaccination 
	 * and number of days reported for a selected country
	 * @return oReport is the report in String
	 * */	 
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
	
	/**
	* @param dataset is the name of the dataset
	* @param iso_code is an iso_code for the selected country
	* @return confirmedCases is the number of confirmed cases for the selected country
	* */
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
	 
	 
	 /**
	  * @author thanhlampham
	  * @param dataset is the selected dataset
	  * This method read the dataset then check whether a country is already in the list
	  * If not, create new instance of country for the corresponding country with the 
	  * current reading date status and add it to the countries list
	  * If the country is already in the list, then add the current reading date status
	  * to the country
	  * @return countries is a list of Country objects holding information for all countries
	  * */
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
										rec.get("new_cases"),
										rec.get("total_cases_per_million"),
										rec.get("total_deaths"),
										rec.get("new_deaths"),
										rec.get("total_deaths_per_million"),
										rec.get("total_vaccinations")
										),
								rec.get("hospital_beds_per_thousand"),
								rec.get("population")
								));
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
											rec.get("new_cases"),
											rec.get("total_cases_per_million"),
											rec.get("total_deaths"),
											rec.get("new_deaths"),
											rec.get("total_deaths_per_million"),
											rec.get("total_vaccinations")
								));
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
	 
	 
	 /**
	  * @param dataset is the current dataset
	  * @return setOfCountry is a hash map with key is the name of country and the value is iso_code
	  * */
	 public static HashMap<String, String> getListOfCountry(String dataset) {
		 HashMap<String, String> setOfCountry = new HashMap<>();
		 for (CSVRecord rec : getFileParser(dataset)) {
			 if(!setOfCountry.keySet().contains(rec.get("location"))) {
				 setOfCountry.put(rec.get("location"),rec.get("iso_code"));
			 }
		 }
		 return setOfCountry;
	 }
	 
	 /**
	  * to be completed
	  * */
	 //Factory pattern
	 @SuppressWarnings("resource")
	public static Pair<Integer,Double> getDataOfCountry(String dataset,String iso_code,String date,String type) throws InvalidInputException {
		 //int total_death = 0;
		// System.out.println("Haha");
		// System.out.println(iso_code);
		// System.out.println(date);
		 boolean too_early = false;
		 //System.out.println("Haha");
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd,yyyy");
		// System.out.println("Haha");
	     LocalDate localDate = LocalDate.parse(date, formatter);
	     //System.out.println("Haha");
	     if(localDate.isAfter(LocalDate.of(2021, 07, 20))) {
				throw new InvalidInputException("Too late date");
		}
	     //System.out.println("Haha");
	     System.out.println("Why get here");
	     for (CSVRecord rec : getFileParser(dataset)) {
	    	// System.out.println("Why dont get here");
	    	// System.out.println(rec.get("iso_code"));
	    	 if (rec.get("iso_code").equals(iso_code)) {
	    	//	System.out.println("Get there");
				String get_date = rec.get("date").strip();
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("M/d/yyyy");
				LocalDate this_date = LocalDate.parse(get_date, formatters);
				
				if(!too_early && localDate.isBefore(this_date)) {
					too_early = true;
					throw new InvalidInputException("Too early date, no deaths");
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