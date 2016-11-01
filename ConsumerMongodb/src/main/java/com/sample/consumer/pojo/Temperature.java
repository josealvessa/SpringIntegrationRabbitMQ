package com.sample.consumer.pojo;

import java.util.Date;

public class Temperature {
	
	private double currentTemp; //value of temperature.
	private String tempScale; //Celsius, Fahrenheit, Kelvin, etc.
	/** Time temerature reading was taken. */
    private Date timeOfReading;
	
	public double getCurrentTemp() {
		return currentTemp;
	}
	public void setCurrentTemp(double currentTemp) {
		this.currentTemp = currentTemp;
	}
	public String getTempScale() {
		return tempScale;
	}
	public void setTempScale(String tempScale) {
		this.tempScale = tempScale;
	}
	public Date getTimeOfReading() {
		return timeOfReading;
	}
	public void setTimeOfReading(Date timeOfReading) {
		this.timeOfReading = timeOfReading;
	}

}
