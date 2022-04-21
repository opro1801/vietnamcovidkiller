package comp3111.covid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStatus {
	private Date date;
	private int totalCases;
	private float totalCasesPerMillion;
	private int totalDeaths;
	private float totalDeathsPerMillion;
	
	public DateStatus (String sDate, String totalCases, String totalCasesPerMillion,
				String totalDeaths, String totalDeathsPerMillion
				) throws ParseException {
		this.date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
		if(totalCases == null || totalCases == "") {
			this.totalCases = 0;
		} else {
			this.totalCases = Integer.parseInt(totalCases);			
		}
		if(totalCasesPerMillion == null || totalCasesPerMillion == "") {
			this.totalCasesPerMillion = 0;
		} else {
			this.totalCasesPerMillion = Float.parseFloat(totalCasesPerMillion);			
		}
		if(totalDeaths == null || totalDeaths == "") {
			this.totalDeaths = 0;
		} else {
			this.totalDeaths = Integer.parseInt(totalDeaths);
		}
		if(totalDeathsPerMillion == null || totalDeathsPerMillion == "") {
			this.totalDeathsPerMillion = 0;
		} else {
			this.totalDeathsPerMillion = Float.parseFloat(totalDeathsPerMillion);
		}
	}
	
	public Date getDate() {
		return date;
	}
	
	public int getTotalCases() {
		return totalCases;
	}
	
	public float getTotalCasesPerMillion() {
		return totalCasesPerMillion;
	}
	
	public int getTotalDeaths() {
		return totalDeaths;
	}
	
	public float getTotalDeathsPerMillion() {
		return totalDeathsPerMillion;
	}
}
