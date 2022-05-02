package comp3111.covid;

import java.math.BigDecimal;
import java.math.BigInteger;
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
    ArrayList<DateStatus> dateStatus = new ArrayList<DateStatus>();
    
    private double hospitalBedsNumPerThousands;
    
    private long population;

    /**
     * assign name, hospitalBedsNumPerThousands and add newDateStatus to the dateStatus array
     * @param name is the name of the country
     * @param newDateStatus is a DateStatus object for a specific date
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
        	this.population = Long.parseLong(population); 
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
     * Add newDateStatus to the dataStatus array if 
     * the date is after the date of the last element of the dateStatus array
     * since we assume that the data in the data set is sorted by date and we 
     * will be reading the date from the beginning
     * @param newDateStatus is a DateStatus object for a specific date
     * @return true if the operation succeeded, false if the operation failed
     * */
    public boolean addDateStatus(DateStatus newDateStatus) {
    	if(newDateStatus.getDate().after(this.dateStatus.get(dateStatus.size()-1).getDate())) {
    		dateStatus.add(newDateStatus);
    		return true;    		
    	}
    	return false;
    }
    
    /**
     * Loop through the dateStatus array and check if the element is within the 
     * period entered and add it to the result if condition is satisfied. 
     * The startDate must be before endDate and are validated before calling the method
     * @param startDate is a Date object indicating the start date to query the status
     * @param endDate is a Date object indicating the end date to query the status
     * @return dateStatusToGet is an array of DateStatus objects for the period from startDate to endDate
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
    
    /**
     * getter for dateStatus
     * @return dateStatus
     * */
    public ArrayList<DateStatus> getDateStatus() {
    	return this.dateStatus;
    }
    
    /**
     * getter for hospitalBedsNumPerThousands
     * @return hospitalBedsNumPerThousands
     * */
    public double getBedsNum() {
    	return this.hospitalBedsNumPerThousands;
    }
    public long getPopulation() {
    	return this.population;
    }
}

