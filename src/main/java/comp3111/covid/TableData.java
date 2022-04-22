package comp3111.covid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

//TODO Table 
public class TableData{
	String countryName;
	int totalData;
	double totalDataPer1M;
	public TableData(String countryName,int totalData,double totalDataPer1M) {
		this.countryName = countryName;
		this.totalData = totalData;
		this.totalDataPer1M = totalDataPer1M;
	}
	public String getCountryName() {
		return this.countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getTotalData() {
		return this.totalData;
	}
	public void setTotalData(int totalData) {
		this.totalData = totalData;
	}
	public double getTotalDataPer1M() {
		return this.totalDataPer1M;
	}
	public void setTotalDataPer1M(double totalDataPer1M) {
		this.totalDataPer1M = totalDataPer1M;
	}
}
// A factory pattern
class TableHelper{
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