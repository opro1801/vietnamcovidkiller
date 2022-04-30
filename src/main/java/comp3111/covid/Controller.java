package comp3111.covid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    private DatePicker dateTableA;
    
    @FXML
    private ListView<Country> countriesTableB;
    
    @FXML
    private Button submitTableB;
    
    @FXML
    private DatePicker dateTableB;
    
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
    
    @FXML
    void doSubmitTabelA(ActionEvent event) throws ParseException{
    	//Get date
    	Date interestDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateTableA.getValue().toString());
    	AlertType type = AlertType.ERROR;
    	Alert alert = new Alert(type, "");
    	
    	//Error handling
    	//Error message
    	String firstMessage = "The date must be after the date of first COVID 19 case (November 17,2019)";
    	String secondMessage = "The date must be not after the current date (July 20,2021)";
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(consoleOutput.getScene().getWindow());
    	//alert.getDialogPane().setContentText("The date must be after the date of first COVID 19 case (November 17,2019)");
    	alert.getDialogPane().setHeaderText("Date Error");
    	// Base on information we can get the first case is this
    	Date firstDate = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.of(2019,11,17).toString());
    	Date lastDate = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.of(2021,7,20).toString());
    	
    	if(interestDate.before(firstDate)) {
    		alert.getDialogPane().setContentText(firstMessage);
    		alert.showAndWait();
    		return;
    	}
    	if(interestDate.after(lastDate)) {
    		alert.getDialogPane().setContentText(secondMessage);
    		alert.showAndWait();
    		return;
    	}
    	selectedCountriesTableA = countriesTableA.getItems().filtered((Country item)->item.isDone.get());
    	String iDataset = textfieldDataset.getText();
    	SimpleDateFormat dateFormat= new SimpleDateFormat("MMM dd,yyyy");
    	String date = dateFormat.format(interestDate);
    	
    	//setup the table
    	TableView<TableData> result = new TableView<TableData>();
    	TableColumn<TableData,String> countries = new TableColumn<TableData,String>("Country");
    	TableColumn<TableData,Integer> totalCases = new TableColumn<TableData,Integer>("Total Cases");
    	TableColumn<TableData,Double> totalCasePer1M = new TableColumn<TableData,Double>("Total Cases (per 1M)");
    	
    	countries.setCellValueFactory(new PropertyValueFactory<>("countryName"));
    	totalCases.setCellValueFactory(new PropertyValueFactory<>("totalData"));
    	totalCasePer1M.setCellValueFactory(new PropertyValueFactory<>("totalDataPer1M"));
    	
    	countries.prefWidthProperty().bind(result.widthProperty().multiply(0.3));
    	totalCases.prefWidthProperty().bind(result.widthProperty().multiply(0.3));
    	totalCasePer1M.prefWidthProperty().bind(result.widthProperty().multiply(0.4));
    	result.getColumns().setAll(countries,totalCases,totalCasePer1M);
    	result.prefWidthProperty().bind(consoleOutput.widthProperty());

    	List<String> chosenCountries = chosenACountry();
    	System.out.println("Chosen country is"+chosenCountries);
    	result.setItems(TableHelper.getModels(chosenCountries,iDataset,date,"cases"));
    	consoleOutput.setContent(result);
    }
    
    //helper 
    List<String> chosenACountry(){
    	ArrayList<String> result = new ArrayList<>();
    	for(Country country:selectedCountriesTableA) {
    		result.add(country.name);
    	}
    	return result;
    }
    
    @FXML
    void doSubmitTabelB(ActionEvent event) throws ParseException{
    	//Get date
    	Date interestDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateTableB.getValue().toString());
    	AlertType type = AlertType.ERROR;
    	Alert alert = new Alert(type, "");
    	
    	//Error handling
    	//Error message
    	String firstMessage = "The date must be after the date of first COVID 19 case (November 17,2019)";
    	String secondMessage = "The date must be not after the current date (July 20,2021)";
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(consoleOutput.getScene().getWindow());
    	//alert.getDialogPane().setContentText("The date must be after the date of first COVID 19 case (November 17,2019)");
    	alert.getDialogPane().setHeaderText("Date Error");
    	// Base on information we can get the first case is this
    	Date firstDate = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.of(2019,11,17).toString());
    	Date lastDate = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.of(2021,7,20).toString());
    	
    	if(interestDate.before(firstDate)) {
    		alert.getDialogPane().setContentText(firstMessage);
    		alert.showAndWait();
    		return;
    	}
    	if(interestDate.after(lastDate)) {
    		alert.getDialogPane().setContentText(secondMessage);
    		alert.showAndWait();
    		return;
    	}
    	
    	selectedCountriesTableB = countriesTableB.getItems().filtered((Country item)->item.isDone.get());
    	String iDataset = textfieldDataset.getText();
    	SimpleDateFormat dateFormat= new SimpleDateFormat("MMM dd,yyyy");
    	String date = dateFormat.format(interestDate);
    	
    	//setup the table
    	TableView<TableData> result = new TableView<TableData>();
    	TableColumn<TableData,String> countries = new TableColumn<TableData,String>("Country");
    	TableColumn<TableData,Integer> totalCases = new TableColumn<TableData,Integer>("Total Deaths");
    	TableColumn<TableData,Double> totalCasePer1M = new TableColumn<TableData,Double>("Total Deaths (per 1M)");
    	
    	countries.setCellValueFactory(new PropertyValueFactory<>("countryName"));
    	totalCases.setCellValueFactory(new PropertyValueFactory<>("totalData"));
    	totalCasePer1M.setCellValueFactory(new PropertyValueFactory<>("totalDataPer1M"));
    	
    	countries.prefWidthProperty().bind(result.widthProperty().multiply(0.3));
    	totalCases.prefWidthProperty().bind(result.widthProperty().multiply(0.3));
    	totalCasePer1M.prefWidthProperty().bind(result.widthProperty().multiply(0.4));
    	result.getColumns().setAll(countries,totalCases,totalCasePer1M);
    	result.prefWidthProperty().bind(consoleOutput.widthProperty());

    	List<String> chosenCountries = chosenACountry();
    	System.out.println("Chosen country is"+chosenCountries);
    	result.setItems(TableHelper.getModels(chosenCountries,iDataset,date,"deaths"));
    	consoleOutput.setContent(result);
    }
    //helper
    List<String> chosenBCountry(){
    	ArrayList<String> result = new ArrayList<>();
    	for(Country country:selectedCountriesTableB) {
    		result.add(country.name);
    	}
    	return result;
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


