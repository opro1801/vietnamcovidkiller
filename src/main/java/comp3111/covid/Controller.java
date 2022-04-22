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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	
	private String dataset = "COVID_Dataset_v1.0.csv";
	
//	ObservableList<Country> list = FXCollections.observableArrayList();

	ObservableList<Country> selectedCountriesChartA = FXCollections.observableArrayList();
	ObservableList<Country> selectedCountriesChartB = FXCollections.observableArrayList();
	ObservableList<Country> selectedCountriesTableA = FXCollections.observableArrayList();
	ObservableList<Country> selectedCountriesTableB = FXCollections.observableArrayList();
	
	ObservableList<Country> listChartA = FXCollections.observableArrayList(item	-> { return new Observable[] { item.isDone }; });
	ObservableList<Country> listChartB = FXCollections.observableArrayList(item	-> { return new Observable[] { item.isDone }; });
	ObservableList<Country> listTableA = FXCollections.observableArrayList(item	-> { return new Observable[] { item.isDone }; });
	ObservableList<Country> listTableB = FXCollections.observableArrayList(item	-> { return new Observable[] { item.isDone }; });
	
    @FXML
    private Button submitChartA;

    @FXML
    private Button buttonConfirmedCases;

    @FXML
    private Button buttonConfirmedDeaths;

    @FXML
    private Button buttonRateOfVaccination;

    @FXML
    private ListView<Country> countriesChartA;

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
    private DatePicker endDateChartA;

    @FXML
    private DatePicker startDateChartA;
    
    @FXML
    private DatePicker endDateChartB;
    
    @FXML
    private DatePicker startDateChartB;
    
    @FXML
    private ListView<Country> countriesChartB;
    
    @FXML
    private Button submitChartB;
    
    @FXML
    private ScrollPane consoleOutput;
    
    @FXML
    private ListView<Country> countriesTableA;
    
    @FXML
    private Button submitTableA;
    
    
    @FXML
    private ListView<Country> countriesTableB;
    
    @FXML
    private Button submitTableB;
    
    @SuppressWarnings("unused")
	@Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       	ArrayList<Country> ListCountriesChartA = DataAnalysis.getAllCountries(dataset);
       	ArrayList<Country> ListCountriesChartB = DataAnalysis.getAllCountries(dataset);
       	ArrayList<Country> ListCountriesTableA = DataAnalysis.getAllCountries(dataset);
       	ArrayList<Country> ListCountriesTableB = DataAnalysis.getAllCountries(dataset);
    	listChartA.removeAll(listChartA);
    	listChartB.removeAll(listChartB);
    	listTableA.removeAll(listTableA);
    	listTableA.removeAll(listTableB);
    	listChartA.addAll(ListCountriesChartA);
    	listChartB.addAll(ListCountriesChartB);
    	listTableA.addAll(ListCountriesTableA);
    	listTableB.addAll(ListCountriesTableB);
    	countriesChartA.getItems().addAll(listChartA);
    	countriesChartA.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	countriesChartA.setCellFactory(CheckBoxListCell.forListView((Country item )-> item.isDone ));
    	countriesChartB.getItems().addAll(listChartB);
    	countriesChartB.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	countriesChartB.setCellFactory(CheckBoxListCell.forListView((Country item)-> item.isDone ));
    	countriesTableA.getItems().addAll(listTableA);
    	countriesTableA.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	countriesTableA.setCellFactory(CheckBoxListCell.forListView((Country item )-> item.isDone ));
    	countriesTableB.getItems().addAll(listTableB);
    	countriesTableB.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	countriesTableB.setCellFactory(CheckBoxListCell.forListView((Country item )-> item.isDone ));
    }

    @FXML
    void selectCountry(MouseEvent event) {
    	selectedCountriesChartA = countriesChartA.getSelectionModel().getSelectedItems();
    }
    
    void doSubmitTabelA(ActionEvent event) throws ParseException{
    	Date interestDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateChartB.getValue().toString());
    }
    
    @SuppressWarnings("unchecked")
    @FXML
    void doSubmitChartA(ActionEvent event) throws ParseException {
    	Date startDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(startDateChartA.getValue().toString());
    	Date endDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(endDateChartA.getValue().toString());
    	AlertType type = AlertType.ERROR;
    	Alert alert = new Alert(type, "");
    	
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(consoleOutput.getScene().getWindow());
    	alert.getDialogPane().setContentText("Start Date must be less then End Date!");
    	alert.getDialogPane().setHeaderText("Date Error");
    	if(endDateObj.before(startDateObj)) {
    		alert.showAndWait();
    		return;
    	}
    	selectedCountriesChartA = countriesChartA.getItems().filtered((Country item) -> item.isDone.get());

    	int startDateInt = getTimeInt(startDateObj);
    	int endDateInt = getTimeInt(endDateObj);
    	final NumberAxis yAxis = new NumberAxis();
    	final NumberAxis xAxis = new NumberAxis();
    	
    	xAxis.setAutoRanging(false);
    	xAxis.setLowerBound(startDateInt);
    	xAxis.setUpperBound(endDateInt);
    	xAxis.setTickUnit((int)((endDateInt - startDateInt) / 10));
    	xAxis.setTickLabelFormatter(new StringConverter<Number>() {
    	    private final SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy");
    	    @Override
    	    public String toString(Number object) {
    	      return format.format(new Date(object.longValue()*100000));
    	    }
    	    @Override
    	    public Number fromString(String string) {
    	      return null;
    	    }
    	  });
    	
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Cumulative Confirmed COVID-19 Cases (per 1M)");
    	for(Country country : selectedCountriesChartA) {
    		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
    		series.setName(country.name);
    		ArrayList<DateStatus> currentDateStatus = country.getDateStatus(startDateObj, endDateObj);
    		for(DateStatus status: currentDateStatus) {
    			series.getData().add(new XYChart.Data(getTimeInt(status.getDate()), status.getTotalCasesPerMillion()));    		
    		}
    		lineChart.getData().add(series);
    		lineChart.setCreateSymbols(false);
    	}

    	consoleOutput.setContent(lineChart);
    }
    
    @SuppressWarnings("unchecked")
    @FXML
    void doSubmitChartB(ActionEvent event) throws ParseException {
    	Date startDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(startDateChartB.getValue().toString());
    	Date endDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(endDateChartB.getValue().toString());
    	selectedCountriesChartB = countriesChartB.getItems().filtered((Country item) -> item.isDone.get());
    	AlertType type = AlertType.ERROR;
    	Alert alert = new Alert(type, "");
    	
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(consoleOutput.getScene().getWindow());
    	alert.getDialogPane().setContentText("Start Date must be less then End Date!");
    	alert.getDialogPane().setHeaderText("Date Error");
    	if(endDateObj.before(startDateObj)) {
    		alert.showAndWait();
    		return;
    	}
    	
    	
    	int startDateInt = getTimeInt(startDateObj);
    	int endDateInt = getTimeInt(endDateObj);
    	final NumberAxis yAxis = new NumberAxis();
    	final NumberAxis xAxis = new NumberAxis();
    	
    	xAxis.setAutoRanging(false);
    	xAxis.setLowerBound(startDateInt);
    	xAxis.setUpperBound(endDateInt);
    	xAxis.setTickUnit((int)((endDateInt - startDateInt) / 10));
    	xAxis.setTickLabelFormatter(new StringConverter<Number>() {
    	    private final SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy");
    	    @Override
    	    public String toString(Number object) {
    	      return format.format(new Date(object.longValue()*100000));
    	    }
    	    @Override
    	    public Number fromString(String string) {
    	      return null;
    	    }
    	  });
    	
        final LineChart<Number, Number> lineChartB = new LineChart<Number, Number>(xAxis, yAxis);
        lineChartB.setTitle("Cumulative Confirmed COVID-19 Deaths (per 1M)");
    	for(Country country : selectedCountriesChartB) {
    		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
    		series.setName(country.name);
    		ArrayList<DateStatus> currentDateStatus = country.getDateStatus(startDateObj, endDateObj);
    		for(DateStatus status: currentDateStatus) {
    			series.getData().add(new XYChart.Data(getTimeInt(status.getDate()), status.getTotalDeathsPerMillion()));    		
    		}
    		lineChartB.getData().add(series);
    		lineChartB.setCreateSymbols(false);
    	}

    	consoleOutput.setContent(lineChartB);
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

    int getTimeInt(Date date) {
//    	long current = status.getDate().getTime();
    	int current = (int)(date.getTime()/100000);
    	return current;
    }
    
    Date getDateFromInt(int timeInt) {
    	long milliseconds = timeInt;
    	milliseconds*=100000;
    	Date date = new Date(milliseconds);
    	return date;
    }
    
}


