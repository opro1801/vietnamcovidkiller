package comp3111.covid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

//TODO Table 
/**
 * @author nguyenkhanhatlong
 * Holding data in a table for a country
 * */
public class TableData{
	String countryName;
	int totalData;
	double totalDataPer1M;
	/**
	 * Initialize countryName, totalData, totalDataPer1M
	 * @param countryName is the name of the country
	 * @param totalData is either totalCases or totalDeaths of the selected country
	 * @param totalDataPer1M is either totalCasesPerMillion or totalDeathsPerMillion of the selected country
	 * */
	public TableData(String countryName,int totalData,double totalDataPer1M) {
		this.countryName = countryName;
		this.totalData = totalData;
		this.totalDataPer1M = totalDataPer1M;
	}
	
	/**
	 * Getter for countryName
	 * @return {@link #countryName}
	 * */
	public String getCountryName() {
		return this.countryName;
	}
	
	/**
	 * Setter for countryName
	 * @param countryName is the name of the country
	 * */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	/**
	 * Getter for totalData
	 * @return {@link #totalData}
	 * */
	public int getTotalData() {
		return this.totalData;
	}
	
	/**
	 * Setter for totalData
	 * @param totalData is either total cases or total deaths for the selected country
	 * */
	public void setTotalData(int totalData) {
		this.totalData = totalData;
	}
	
	/**
	 * Getter for totalDataPer1M
	 * @param totalDataPer1M is either total cases per million or total deaths per million of the selected country
	 * */
	public double getTotalDataPer1M() {
		return this.totalDataPer1M;
	}
	
	/**
	 * Setter for totalDataPer1M
	 * @param totalDataPer1M is either total cases per million or total deaths per million of the selected country
	 * */
	public void setTotalDataPer1M(double totalDataPer1M) {
		this.totalDataPer1M = totalDataPer1M;
	}
}
// A factory pattern
/**
 * Helper class for generating table
 * */
class TableHelper{
	/**
	 * @param chosenCountries is the chosen countries list 
	 * @param dataset is the name of the current dataset
	 * @param date is the query date
	 * @param type is either cases or deaths
	 * @return an observableArrayList converted from the final_result which is the data for a table showing cases or deaths data from the dataset for all the chosen countries on a selected date
	 * */
	static public ObservableList<TableData> getModels(List<String> chosenCountries,String dataset,String date,String type){
		List<TableData> final_result = new ArrayList<TableData>();
		HashMap<String, String> countryList = DataAnalysis.getListOfCountry(dataset);
		//System.out.println("Hello out");
		//System.out.println(chosenCountries);
		for(String country:chosenCountries) {
			//System.out.println("Hello sin");
			try{
				Pair<Integer,Double> result = null;
				//Factory here
				if(type.equals("cases")){
					result = DataAnalysis.getDataOfCountry(dataset,countryList.get(country),date,"cases");
				}
				else if(type.equals("deaths")) {
					result = DataAnalysis.getDataOfCountry(dataset,countryList.get(country),date,"deaths");
				}
				final_result.add(new TableData(country,result.getKey(),result.getValue()));
			}
			catch(Exception e) {
				
			}
		}
		return FXCollections.observableArrayList(final_result);
	}
}