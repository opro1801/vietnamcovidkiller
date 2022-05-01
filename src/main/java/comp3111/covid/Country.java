package comp3111.covid;

import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


/**
 * @author thanhlampham
 * @version 1.0
 * This class representing a country with its data
 * {@value #name} name holds the name of the country
 * {@value #isDone} isDone indicates the country is selected or not
 * {@value #dateStatus} dateStatus is an array with each element holding a status for a specific date
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
     * array of status for each date
     * */
    private ArrayList<DateStatus> dateStatus = new ArrayList<DateStatus>();

    /**
     * Constructor
     * @param name is name of the country
     * @param newDateStatus is a DateStatus object for a specific date
     * the constructor assign name and add newDateStatus to the dateStatus array
     * */
    public Country(String name, DateStatus newDateStatus) {
        this.name = name;
        this.dateStatus.add(newDateStatus);
    }

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
}
