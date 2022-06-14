
package model;



public class IndyWinnerModel{
	
	int id;
	
	int year;

	String driver;

	double averageSpeed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public double getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public IndyWinnerModel(int id, int year, String driver, double averageSpeed) {
		super();
		this.id = id;
		this.year = year;
		this.driver = driver;
		this.averageSpeed = averageSpeed;
	}

	public IndyWinnerModel(int year, String driver, double averageSpeed) {
		super();
		this.year = year;
		this.driver = driver;
		this.averageSpeed = averageSpeed;
	}

	public IndyWinnerModel() {
		super();
	}

	
	
		
}
