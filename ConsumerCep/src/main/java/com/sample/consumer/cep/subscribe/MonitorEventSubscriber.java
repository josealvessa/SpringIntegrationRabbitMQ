package com.sample.consumer.cep.subscribe;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sample.consumer.pojo.Entity;

/*import com.software.project.amqp.MessageQueueManager;
import com.software.project.util.ApplicationContextProvider;
*/
/**
 * Wraps Esper Statement and Listener. No dependency on Esper libraries.
 */
@Component
public class MonitorEventSubscriber implements StatementSubscriber {

    /** Logger */
    private static Logger LOG = LoggerFactory.getLogger(MonitorEventSubscriber.class);
 //   private  MessageQueueManager manager;
    /**
     * {@inheritDoc}
     */
    public String getStatement() {
        Entity e  = new Entity();   
      //  e.attributes.get(2).value
         //   return "select avg(cast(ConsumerPosition.lat, double)) as avg_val from ConsumerPosition.win:time_batch(5 sec)";
    	 return "select avg(cast(Entity.attributes[0].value, double)) as avg_val from Entity.win:length_batch(3)";
    }

    /**
     * Listener method called when Esper has detected a pattern match.
     * @throws Exception 
     */
    public void update(Map<String, Double> eventMap) throws Exception {
        StringBuilder sb = new StringBuilder();
        // average temp over 10 secs
        Double avg = (Double) eventMap.get("avg_val");

        sb.append("---------------------------------");
        sb.append("\n- [MONITOR] Average Temp = " + avg);
        sb.append("\n---------------------------------");
        System.out.println(sb.toString());
        LOG.debug(sb.toString());
            
     /*  String imeiConsumer = String.valueOf(eventMap.get("Consumer.imei"));
       String imeiConsumer = String.valueOf(eventMap.get("consumerPosition.imei"));
       String distance = String.valueOf(eventMap.get("distance"));
       double d = Double.parseDouble(distance);*/
    
 /*      
         manager = (MessageQueueManager) ApplicationContextProvider.getBean("messageQueueManager");
      // MessageQueueManager manager2 = (MessageQueueManager) ApplicationContextProvider.getBean("messageQueueManager");
         manager.createQueue(imeiConsumer);
	     manager.sendMessage(distance, imeiConsumer);
       
	     manager.createQueue(imeiConsumer);
	     manager.sendMessage(distance, imeiConsumer);*/
      
 
    }
}
