package com.sample.consumer.pojo;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.handler.annotation.Payload;

import com.sample.consumer.cep.handler.MonitorEventHandler;
import com.sample.consumer.cep.subscribe.MonitorEventSubscriber;
import com.sample.consumer.util.ApplicationContextProvider;

@MessageEndpoint
public class ConsumerServiceActivator {
	
	@Autowired
	MonitorEventHandler monitorEventHandler;
	
    public void disparaEvento(@Payload String payload){
    	monitorEventHandler = (MonitorEventHandler)ApplicationContextProvider.getBean("monitorEventHandler");
  	    
    	Entity myEntity = ParserJson.parseEntity(payload);
    	/*Temperature temp = new Temperature();
    	if(payload.equals("") || payload.equals("\"20::Celsius\"")){
    		return;
    	}
		   String[] attr = payload.split("::");
		   temp.setCurrentTemp(Double.parseDouble(attr[0]));
		   temp.setTempScale(attr[1]);
		   temp.setTimeOfReading(Calendar.getInstance().getTime());*/
		   monitorEventHandler.handleEntity(myEntity);
    	
    }
    
}
