package comp3111.covid;
import static org.junit.Assert.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
public class TestProject{
	
	String data_set = "COVID_Dataset_v1.0.csv";
	
	@Test
	public void task0() {
		String oReport1 = "";
		String oReport2 = "";
		String oReport3 = "";
		String iso_code = "HKG";
		String report1 = DataAnalysis.getConfirmedCases(data_set, "HKG");
		String report2 = DataAnalysis.getConfirmedDeaths(data_set, "HKG");
		String report3 = DataAnalysis.getRateOfVaccination(data_set, "HKG");
		oReport1 = String.format("Dataset (%s): %,d Records\n\n", data_set, 103907);
		oReport1 += String.format("[Summary (%s)]\n", iso_code);
		oReport1 += String.format("Number of Confirmed Cases: %,d\n", 11965);
		oReport1 += String.format("Number of Days Reported: %,d\n", 545);
		oReport2 = String.format("Dataset (%s): %,d Records\n\n", data_set, 103907);
		oReport2 += String.format("[Summary (%s)]\n", iso_code);
		oReport2 += String.format("Number of Deaths: %,d\n", 212);
		oReport2 += String.format("Number of Days Reported: %,d\n", 533);
		oReport3 = String.format("Dataset (%s): %,d Records\n\n", data_set, 103907);
		oReport3 += String.format("[Summary (%s)]\n", iso_code);
		oReport3 += String.format("Number of People Fully Vaccinated: %,d\n", 2065375);
		oReport3 += String.format("Population: %,d\n", 7496988);
		oReport3 += String.format("Rate of Vaccination: %.2f%%\n", 27.55);
		oReport3 += String.format("Number of Days Reported: %,d\n", 140);
		assertEquals(report1,oReport1);
		assertEquals(report2,oReport2);
		assertEquals(report3,oReport3);
	}
	@Test
	public void testNumberCountry() {
		ArrayList<Country> listCountry =  DataAnalysis.getAllCountries(data_set);
		assertEquals(listCountry.size(),231);
	}
	@Test
	public void testTableA() {
		String date = "May 07,2020";
		List<String> chosenCountries = new ArrayList<String>();
		chosenCountries.add("Afghanistan");
		chosenCountries.add("Hong Kong");
		ObservableList<TableData> data = TableHelper.getModels(chosenCountries,data_set,date,"cases");
		TableData afg = data.get(0);
		TableData hk = data.get(1);
		assertEquals(afg.totalData,3564);
		assertEquals(afg.totalDataPer1M,91.553,0.001);
		assertEquals(hk.totalData,1044);
		assertEquals(hk.totalDataPer1M,139.256,0.001);
	}
	@Test
	public void testTableB() {
		String date = "May 07,2020";
		List<String> chosenCountries = new ArrayList<String>();
		chosenCountries.add("Afghanistan");
		chosenCountries.add("Hong Kong");
		ObservableList<TableData> data = TableHelper.getModels(chosenCountries,data_set,date,"deaths");
		TableData afg = data.get(0);
		TableData hk = data.get(1);
		assertEquals(afg.totalData,106);
		assertEquals(afg.totalDataPer1M,2.723,0.001);
		assertEquals(hk.totalData,4);
		assertEquals(hk.totalDataPer1M,0.534,0.001);
	}
	@Test
	public void testCountry() throws ParseException {
		ArrayList<Country> listCountry =  DataAnalysis.getAllCountries(data_set);
		Country hk = null;
		Country hdr = null;
		Country agl = null;
		for(Country country:listCountry) {
			if(country.name.equals("Hong Kong")) {
				hk = country;
			}
			if(country.name.equals("Honduras")) {
				hdr = country;
			}
			if(country.name.equals("Anguilla")) {
				agl = country;
			}
		}
		// No information
		assertEquals(hk.getBedsNum(),0.0,0.001);
		// Have information
		assertEquals(hdr.getBedsNum(),0.7,0.001);
		
		Date startDateObj = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-07");
    	Date endDateObj = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-07");
    	Date dFeb04 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-04");

    	
    	DateStatus HKMay07 = hk.getDateStatus(startDateObj, endDateObj).get(0);
    	DateStatus HDRMay07 = hdr.getDateStatus(startDateObj, endDateObj).get(0);
    	
    	DateStatus aglFeb04 = agl.getDateStatus(dFeb04,dFeb04).get(0);
    	
    	assertEquals(aglFeb04.getNewCases(),0);
    	assertEquals(HKMay07.getDate(),startDateObj);
    	assertEquals(HKMay07.getTotalCases(),1044);
    	assertEquals(HKMay07.getNewCases(),4);
    	assertEquals(HKMay07.getTotalCasesPerMillion(),139.256,0.001);
    	assertEquals(HKMay07.getTotalDeaths(),4);
    	assertEquals(HKMay07.getTotalDeathsPerMillion(),0.534,0.001);
    	assertEquals(HKMay07.getNewDeaths(),0);
    	assertEquals(HKMay07.getTotalVaccinationsPerHundred(),0,0.001);
	}
	@Test
	public void testController() throws ParseException {
		MyApplication x =new MyApplication();
		//nothing 
		x.main(null);
		
	}
}
