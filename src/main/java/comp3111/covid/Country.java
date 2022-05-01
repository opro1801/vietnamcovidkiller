package comp3111.covid;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


/**
 * This class representing a country with its data
 * @author thanhlampham
 * */
final public class Country {
	/**
	 * name of the country
	 * */
    final String name;
    /**
     * whether this country is selected
     * */
    final BooleanProperty isDone = new SimpleBooleanProperty(false);

    /**
     * an array with each element holding a status for a specific date
     * */
    private ArrayList<DateStatus> dateStatus = new ArrayList<DateStatus>();
    
    private double hospitalBedsNumPerThousands;
    
    private int population;

    /**
     * Constructor
     * @param name is name of the country
     * @param newDateStatus is a DateStatus object for a specific date
     * the constructor assign name and add newDateStatus to the dateStatus array
     * */
    public Country(String name, DateStatus newDateStatus, String hospitalBedsNumPerThousands, String population) throws ParseException {
        this.name = name;
        this.dateStatus.add(newDateStatus);
        if(hospitalBedsNumPerThousands == null || hospitalBedsNumPerThousands.length() == 0) {
        	this.hospitalBedsNumPerThousands = 0;
        } else {
        	this.hospitalBedsNumPerThousands = Double.parseDouble(hospitalBedsNumPerThousands);        	
        }
        if(population == null || population.length() == 0) {
        	this.population = 0;
        } else {
        	this.population = Integer.parseInt(population);        	
        }
    }

    /**
     * @return name of country
     * */
    @Override
    public String toString() {
        return name;
    }
    
    /**
     * @param newDateStatus is a DateStatus object for a specific date
     * @return true if the operation succeeded, false if the operation failed
     * This method add the input newDateStatus to the dataStatus array if
     * the date is after the date of the last element of the dateStatus array
     * since we assume that the data in the data set is sorted by date and we 
     * will be reading the date from the beginning
     * */
    public boolean addDateStatus(DateStatus newDateStatus) {
    	if(newDateStatus.getDate().after(this.dateStatus.get(dateStatus.size()-1).getDate())) {
    		dateStatus.add(newDateStatus);
    		return true;    		
    	}
    	return false;
    }
    
    /**
     * @param startDate is a Date object indicating the start date to query the status
     * @param endDate is a Date object indicating the end date to query the status
     * @return dateStatusToGet is an array of DateStatus objects for the period from startDate to endDate
     * This method loop through the dateStatus array and check if the element is within the 
     * period entered and add it to the result if condition is satisfied
     * The startDate must be before endDate and are validated before calling the method
     * */
    
    public ArrayList<DateStatus> getDateStatus(Date startDate, Date endDate) {
    	ArrayList<DateStatus> dateStatusToGet = new ArrayList<DateStatus>();
    	for(DateStatus status : dateStatus) {
    		if(status.getDate().equals(startDate) ||
    			status.getDate().equals(endDate) ||
    			(status.getDate().after(startDate) &&
    			status.getDate().before(endDate))) {
    			dateStatusToGet.add(status);
    		}
    	}
    	return dateStatusToGet;
    }
    
    public ArrayList<DateStatus> getDateStatus() {
    	return this.dateStatus;
    }
    public double getBedsNum() {
    	return this.hospitalBedsNumPerThousands;
    }
    public int getPopulation() {
    	return this.population;
    }
}
