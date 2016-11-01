package com.sample.consumer.cep.handler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.sample.consumer.cep.subscribe.MonitorEventSubscriber;
import com.sample.consumer.cep.subscribe.StatementSubscriber;
import com.sample.consumer.pojo.Entity;
import com.sample.consumer.util.ApplicationContextProvider;


@Component
@Scope(value = "singleton")
public class MonitorEventHandler implements InitializingBean{

 
    /** Esper service */
    private EPServiceProvider epService;
    private EPStatement monitorEventStatement;
    int i = 0;

 //   @Autowired
 //   @Qualifier("monitorEventSubscriber")
    private MonitorEventSubscriber monitorEventSubscriber;
 
    
    /**
     * Configure Esper Statement(s).
     */

    public void  initService() {
        Configuration config = new Configuration();
        config.addEventTypeAutoName("com.sample.consumer.pojo");
        epService = EPServiceProviderManager.getDefaultProvider(config);
        createRequestMonitorExpression();

    }

    private void createRequestMonitorExpression() {
        //LOG.debug("create Timed Average Monitor");
    	monitorEventSubscriber = (MonitorEventSubscriber)ApplicationContextProvider.getBean("monitorEventSubscriber");
 	   
        monitorEventStatement = epService.getEPAdministrator().createEPL(monitorEventSubscriber.getStatement());
		monitorEventStatement.setSubscriber(monitorEventSubscriber);
	}
    
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		  initService();
	}

    
    /**
     * Handle the incoming Entity.
     */
    public void handleEntity(Entity event) {
      //  LOG.debug(event.toString());
        epService.getEPRuntime().sendEvent(event);

    }
  
}
