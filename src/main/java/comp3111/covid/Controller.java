package comp3111.covid;

import java.util.ArrayList;
import java.util.Date;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	
	private String dataset = "COVID_Dataset_v1.0.csv";
	
//	ObservableList<Country> list = FXCollections.observableArrayList();

	ObservableList<Country> selectedCountries = FXCollections.observableArrayList();
	
	ObservableList<Country> list = FXCollections.observableArrayList(item	-> { return new Observable[] { item.isDone }; });
	
    @FXML
    private Button submit;

    @FXML
    private Button buttonConfirmedCases;

    @FXML
    private Button buttonConfirmedDeaths;

    @FXML
    private Button buttonRateOfVaccination;

    @FXML
    private ListView<Country> countriesList;

    @FXML
    private DatePicker dateOfInterest;

    @FXML
    private Button loadCountry;

    @FXML
    private Tab tabApp1;

    @FXML
    private Tab tabApp2;

    @FXML
    private Tab tabApp3;

    @FXML
    private Tab tabReport1;

    @FXML
    private Tab tabReport2;

    @FXML
    private Tab tabReport3;

    @FXML
    private Tab tabTaskZero;

    @FXML
    private TextArea textAreaConsole;

    @FXML
    private TextField textfieldDataset;

    @FXML
    private TextField textfieldISO;

    @FXML
    private DatePicker endDate;

//    @FXML
//    private LineChart<?, ?> lineChart;

    @FXML
    private DatePicker startDate;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       	ArrayList<Country> countries = DataAnalysis.getAllCountries(dataset);
    	list.removeAll(list);
    	list.addAll(countries);
    	countriesList.getItems().addAll(list);
    	countriesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	countriesList.setCellFactory(CheckBoxListCell.forListView((Country item )-> item.isDone ));
    }

    @FXML
    void selectCountry(MouseEvent event) {
    	selectedCountries = countriesList.getSelectionModel().getSelectedItems();
    }
    
    @SuppressWarnings("unchecked")
    @FXML
    void doSubmit(ActionEvent event) throws ParseException {
    	Date startDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(startDate.getValue().toString());
    	Date endDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(endDate.getValue().toString());
    	selectedCountries = countriesList.getItems().filtered((Country item) -> item.isDone.get());
//    	String report = "";
//    	for(Country country : selectedCountries) {
//    		report += country.name + "\n";
//    		ArrayList<DateStatus> currentDateStatus = country.getDateStatus(startDateObj, endDateObj);
//    		for(DateStatus status: currentDateStatus) {
//    			report += status.getDate().toString() + " " + status.getTotalCasesPerMillion() + "\n";
//    		}
//    	}
//    	textAreaConsole.setText(report);
    	final NumberAxis yAxis = new NumberAxis();
    	final NumberAxis xAxis = new NumberAxis();
    	
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        
    	for(Country country : selectedCountries) {
    		XYChart.Series series = new XYChart.Series();
    		series.setName(country.name);
    		ArrayList<DateStatus> currentDateStatus = country.getDateStatus(startDateObj, endDateObj);
    		for(DateStatus status: currentDateStatus) {
    			series.getData().add(new XYChart.Data(new SimpleDateFormat("dd/MM/yyyy").format(status.getDate()), status.getTotalCasesPerMillion()));    		
    		}
    		lineChart.getData().add(series);
    		lineChart.setCreateSymbols(false);
    	}
//    	XYChart.Series series = new XYChart.Series();
//    	series.setName("USA");
//    	series.getData().add(new XYChart.Data(Date.UTC(2020, 3, 12, 0, 0, 0), 23.2));
//    	series.getData().add(new XYChart.Data(Date.UTC(2020, 4, 12, 0, 0, 0), 14.6));
//    	series.getData().add(new XYChart.Data(Date.UTC(2020, 5, 12, 0, 0, 0), 25.234));
//    	
//    	lineChart.getData().add(series);
    	
    }
    /**
     *  Task Zero
     *  To be triggered by the "Confirmed Cases" button on the Task Zero Tab 
     *  
     */
    @FXML
    void doConfirmedCases(ActionEvent event) {
    	String iDataset = textfieldDataset.getText();
    	String iISO = textfieldISO.getText();
    	String oReport = DataAnalysis.getConfirmedCases(iDataset, iISO);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Confirmed Deaths" button on the Task Zero Tab
     *  
     */
    @FXML
    void doConfirmedDeaths(ActionEvent event) {
    	String iDataset = textfieldDataset.getText();
    	String iISO = textfieldISO.getText();
    	String oReport = DataAnalysis.getConfirmedDeaths(iDataset, iISO);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rate of Vaccination" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRateOfVaccination(ActionEvent event) {
    	String iDataset = textfieldDataset.getText();
    	String iISO = textfieldISO.getText();
    	String oReport = DataAnalysis.getRateOfVaccination(iDataset, iISO);
    	textAreaConsole.setText(oReport);
    }  

    
}


