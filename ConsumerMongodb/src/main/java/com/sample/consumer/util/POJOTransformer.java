package com.sample.consumer.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;

import com.sample.consumer.pojo.Temperature;

@MessageEndpoint
public class POJOTransformer {
	
	@Transformer
	public Temperature toTemp(String s){
		Temperature temp = new Temperature();
		String[] attr = s.split("::");
		temp.setCurrentTemp(Double.parseDouble(attr[0]));
		temp.setTempScale(attr[1]);
		temp.setTimeOfReading(Calendar.getInstance().getTime());
		return temp;
	}
}
