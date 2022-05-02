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
import javafx.scene.control.ComboBox;
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

import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * Controller for javafx UI that implements Initializable
 * */
public class Controller implements Initializable {
	
	private String dataset = "COVID_Dataset_v1.0.csv";

	/**
	 * Lists holding the current selected countries of each task
	 * */
	ObservableList<Country> selectedCountriesChartA = FXCollections.observableArrayList();
	ObservableList<Country> selectedCountriesChartB = FXCollections.observableArrayList();
	ObservableList<Country> selectedCountriesTableA = FXCollections.observableArrayList();
	ObservableList<Country> selectedCountriesTableB = FXCollections.observableArrayList();
	
	
	/**
	 * Lists holding the total countries in each task
	 * */
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
    

    @FXML
    private ComboBox<String> AFeatureCountry;
    
    @FXML
    private Button AFTask2;
    
    @FXML
    private Button AFTask1;
    
    @FXML
    private Button newFeaTask1;

    @FXML
    private Button newFeaTask2;
    
    @FXML
    private Button newFeaTask3;

    @FXML
    private ComboBox<String> newFeatureCountry;
    
    
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
    	for(Country country:ListCountriesChartA) {
    		AFeatureCountry.getItems().add(country.name);
    	}
    	for(Country country:ListCountriesChartA) {
    		newFeatureCountry.getItems().add(country.name);
    	}
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
    /**
     * Triggered when a user click on the submit button on Chart A tab, 
     * gets the start date, end date and the countries of interest.
     * Then validate the date and countries selected.
     * If the input entered are valid: 
     * The method then generate the line chart showing the cumulative confirmed COVID-19 cases (per 1M)
     * with the x axis representing the dates and display with proper scale,
     * the y axis representing number of confirmed cases
     * for each selected country, the method retrieve the confirmed cases over the selected period
     * by getDateStatus method from Country class, adding to the series.
     * Then the series is displayed on the chart view
     * @param event user click on submit button on Chart A tab
     * 
     * @throws ParseExceiption the method throws an exception if occurred when parsing the dates 
     * */
    void doSubmitChartA(ActionEvent event) throws ParseException {
    
    	if(!chartDateValidation(startDateChartA, endDateChartA)) return;
    	Date startDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(startDateChartA.getValue().toString());
    	Date endDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(endDateChartA.getValue().toString());
    	selectedCountriesChartA = countriesChartA.getItems().filtered((Country item) -> item.isDone.get());
    	
    	if(!countryValidation(selectedCountriesChartA)) return;
    	
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
    
    
    /**
     * This method triggered when a user click on the submit button on Chart B tab
     * @param event is an event that a user click on submit button on Chart B tab
     * 
     * This method gets the start date, end date and the countries of interest
     * Then validate the date and countries selected
     * If the input entered are valid:
     * The method then generate the line chart showing the cumulative confirmed COVID-19 deaths (per 1M)
     * with the x axis representing the dates and display with proper scale,
     * the y axis representing number of confirmed cases
     * for each selected country, the method retrieve the death over the selected period
     * by getDateStatus method from Country class, adding to the series.
     * Then the series is displayed on the chart view
     * 
     * @throws ParseExceiption the method throws an exception if occurred when parsing the dates 
     * */
    @SuppressWarnings("unchecked")
    @FXML
    void doSubmitChartB(ActionEvent event) throws ParseException {
    	
    	if(!chartDateValidation(startDateChartB, endDateChartB)) return;
    	Date startDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(startDateChartB.getValue().toString());
    	Date endDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(endDateChartB.getValue().toString());
    	selectedCountriesChartB = countriesChartB.getItems().filtered((Country item) -> item.isDone.get());
     	
    	if(!countryValidation(selectedCountriesChartB)) return;
    	
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
    
    
    @FXML
    void doFeaTask1(ActionEvent event) throws ParseException {
    	
    	if(!singleCountryValidation(newFeatureCountry)) return;
    	
    	final NumberAxis yAxis = new NumberAxis();
    	final NumberAxis xAxis = new NumberAxis();
    	
    	yAxis.setLowerBound(0);
    	final LineChart<Number, Number> lineChartFeaTask1 = new LineChart<Number, Number>(xAxis, yAxis);
    	lineChartFeaTask1.setTitle("Correlation of vaccination rate and confirmed cases");
    	
    	for(Country country : listChartA) {
    		if(country.name.equals(newFeatureCountry.getValue())) {
    			XYChart.Series<Number, Number> series = new XYChart.Series<>();
    			series.setName(country.name);
    			for(DateStatus status: country.getDateStatus()) {
        			series.getData().add(new XYChart.Data(status.getTotalVaccinationsPerHundred(),
        					status.getNewCases()));	
    			}
    			lineChartFeaTask1.getData().add(series);
    			lineChartFeaTask1.setCreateSymbols(false);
    		}
    	}
    	consoleOutput.setContent(lineChartFeaTask1);
    }

    @FXML
    void doFeaTask2(ActionEvent event) throws ParseException {

    	if(!singleCountryValidation(newFeatureCountry)) return;
    	
    	final NumberAxis yAxis = new NumberAxis();
    	final NumberAxis xAxis = new NumberAxis();
    	
    	yAxis.setLowerBound(0);
    	final LineChart<Number, Number> lineChartFeaTask2 = new LineChart<Number, Number>(xAxis, yAxis);
    	lineChartFeaTask2.setTitle("Correlation of vaccination rate and confirmed deaths");
    	
    	for(Country country : listChartA) {
    		if(country.name.equals(newFeatureCountry.getValue())) {
    			XYChart.Series<Number, Number> series = new XYChart.Series<>();
    			series.setName(country.name);
    			for(DateStatus status: country.getDateStatus()) {
        			series.getData().add(new XYChart.Data(status.getTotalVaccinationsPerHundred(),
        					status.getNewDeaths()));
    			}
    			lineChartFeaTask2.getData().add(series);
    			lineChartFeaTask2.setCreateSymbols(false);
    		}
    	}
    	consoleOutput.setContent(lineChartFeaTask2);
    }
    
    @FXML
    void doFeaTask3(ActionEvent event) throws ParseException {
    	if(!singleCountryValidation(newFeatureCountry)) return;
    	
    	final NumberAxis yAxis = new NumberAxis();
    	final NumberAxis xAxis = new NumberAxis();
    	
    	yAxis.setLowerBound(0);
    	final LineChart<Number, Number> lineChartFeaTask3 = new LineChart<Number, Number>(xAxis, yAxis);
    	lineChartFeaTask3.setTitle("Correlation of vaccination rate and confirmed cases and deaths");
    	
    	for(Country country : listChartA) {
    		if(country.name.equals(newFeatureCountry.getValue())) {
    			XYChart.Series<Number, Number> series = new XYChart.Series<>();
    			series.setName(country.name + " vaccination rate vs deaths");
    			XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
    			series2.setName(country.name + " vaccination rate vs cases");
    			for(DateStatus status: country.getDateStatus()) {
    				series.getData().add(new XYChart.Data(status.getTotalVaccinationsPerHundred(),
    	        			status.getNewDeaths()));	
    				series2.getData().add(new XYChart.Data(status.getTotalVaccinationsPerHundred(),
    						status.getNewCases()));
    			}
    			
    			lineChartFeaTask3.getData().add(series);
    			lineChartFeaTask3.getData().add(series2);
    			lineChartFeaTask3.setCreateSymbols(false);
    		}
    	}
    	consoleOutput.setContent(lineChartFeaTask3);
    }
    
    @FXML
    void DoAFTask2(ActionEvent event) {
    	final NumberAxis yAxis = new NumberAxis();
    	final NumberAxis xAxis = new NumberAxis();

    	System.out.println(AFeatureCountry.getValue());
        final LineChart<Number, Number> lineChartAF2 = new LineChart<Number, Number>(xAxis, yAxis);
        lineChartAF2.setTitle("Correlation of deaths case and confirm cases");
        
    	for(Country country : listChartA) {
    		System.out.println(country.name);
    		if(country.name.equals(AFeatureCountry.getValue()))
    		{
    			System.out.println("Helllooooo");
	    		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
	    		series.setName(country.name);
	    		for(DateStatus status: country.getDateStatus()) {
	    			series.getData().add(new XYChart.Data(status.getTotalCasesPerMillion(),status.getTotalDeathsPerMillion()));
	    		}
	    		lineChartAF2.getData().add(series);
	    		lineChartAF2.setCreateSymbols(false);
    		}
    	}

    	consoleOutput.setContent(lineChartAF2);
    }
    @FXML
    void DoAFTask1(ActionEvent event) {
    	
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
    	consoleOutput.setContent(new TextArea(oReport));
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
    	consoleOutput.setContent(new TextArea(oReport));
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
    	consoleOutput.setContent(new TextArea(oReport));
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
    
    
    /**
     * @param startDate is the DatePicker object for start date on the UI of the current task
     * @param endDate is the DatePicker object for end date on the UI of the current task
     * @return true if the date input is valid
     * @return false if the date input is invalid
     * @throws ParseException throws an exception when parsing a date string
     * 
     * The method check if the start date and end date have been entered and entered correctly
     * Then check if the end date is after the start date
     * Then check if the period entered is within the period with data available in current data set
     * If a condition is not satisfied, then display a corresponding alert
     */
    
    boolean chartDateValidation(DatePicker startDate, DatePicker endDate) throws ParseException {
    	String firstMessage = "The period should be after the date of first COVID 19 case (November 17,2019)";
    	String secondMessage = "The period should be before the current date (July 20,2021)";
    	String thirdMessage = "Start Date must be less then End Date!";
    	String dateWarningTitle = "Date Is Invalid";
    	String startDateWarning = "A valid start date must be selected!";
    	String endDateWarning = "A valid end date must be selected!";
    	
    	Alert alert = new Alert(AlertType.ERROR, "");
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(consoleOutput.getScene().getWindow());
    	alert.getDialogPane().setHeaderText(dateWarningTitle);
    	Date firstDate = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.of(2019,11,17).toString());
    	Date lastDate = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.of(2021,7,20).toString());

    	
    	if(startDate.getValue() == null) {
    		alert.getDialogPane().setContentText(startDateWarning);
    		alert.showAndWait();
    		return false;
    	}
    	
    	if(endDate.getValue() == null) {
    		alert.getDialogPane().setContentText(endDateWarning);
    		alert.showAndWait();
    		return false;
    	}
    	
    	Date startDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(startDate.getValue().toString());
    	Date endDateObj = new SimpleDateFormat("yyyy-MM-dd").parse(endDate.getValue().toString());
    	
    	if(endDateObj.before(startDateObj) || endDateObj.equals(startDateObj)) {
    		alert.getDialogPane().setContentText(thirdMessage);
    		alert.showAndWait();
    		return false;
    	}
    	
    	if(startDateObj.before(firstDate)) {
    		alert.setAlertType(AlertType.WARNING);
    		alert.getDialogPane().setContentText(firstMessage);
    		alert.showAndWait();
    		return false;
    	}
    	
    	if(endDateObj.after(lastDate)) {
    		alert.setAlertType(AlertType.WARNING);
    		alert.getDialogPane().setContentText(secondMessage);
    		alert.showAndWait();
    		return false;
    	}
    	return true;
    }
    
    
    /**
     * This method takes the current selected countries list
     * @param list can be the selected countries list of chart A,B or table A,B
     * 
     * @return this method return true if the condition is satisfied
     * @return if no country have been selected, a warning alert box will be displayed and return false
     * 
     * This method validates the input countries of interest and check if at
     * least one country is selected then trigger a warning
     * */
    boolean countryValidation(ObservableList<Country> list) {
    	String countryWarning = "You should select at least one country!";
    	String countryWarningTitle = "No country have been selected";
    	Alert alert = new Alert(AlertType.WARNING, "");
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(consoleOutput.getScene().getWindow());
    	alert.getDialogPane().setHeaderText(countryWarningTitle);
    	alert.getDialogPane().setContentText(countryWarning);
    	
    	if(list.isEmpty()) {
    		alert.showAndWait();
    		return false;
    	}
    	return true;
    }
    
    boolean singleCountryValidation(ComboBox<String> country) {
    	String countryWarning = "You must select a country!";
    	String countryWarningTitle = "No country have been selected";
    	Alert alert = new Alert(AlertType.WARNING, "");
    	alert.initModality(Modality.APPLICATION_MODAL);
    	alert.initOwner(consoleOutput.getScene().getWindow());
    	alert.getDialogPane().setHeaderText(countryWarningTitle);
    	alert.getDialogPane().setContentText(countryWarning);
    	
    	if(country.getValue() == null) {
    		alert.showAndWait();
    		return false;
    	}
    	
    	return true;
    }
    
}


