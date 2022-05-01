package comp3111.covid;

import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

final public class Country {
    final String name;
    final BooleanProperty isDone = new SimpleBooleanProperty(false);
    public ArrayList<DateStatus> dateStatus = new ArrayList<DateStatus>();

    public Country(String name, DateStatus newDateStatus) {
        this.name = name;
        this.dateStatus.add(newDateStatus);
    }

    @Override
    public String toString() {
        return name;
    }
    
    public boolean addDateStatus(DateStatus newDateStatus) {
    	if(newDateStatus.getDate().after(this.dateStatus.get(dateStatus.size()-1).getDate())) {
    		dateStatus.add(newDateStatus);
    		return true;    		
    	}
    	return false;
    }
    
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
