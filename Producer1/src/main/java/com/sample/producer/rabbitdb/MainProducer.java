package com.sample.producer.rabbitdb;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class MainProducer {
     
	private static final Logger LOGGER = Logger.getLogger(MainProducer.class);
	
	private MainProducer() { }

	public static void main(final String... args) {
        
		LOGGER.info("Send ngsi Entity" );
		
		@SuppressWarnings("resource")
		final AbstractApplicationContext context =	new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	    CachingConnectionFactory connectionFactory = (CachingConnectionFactory) context.getBean("connectionFactory");

	    RabbitTemplate template = new RabbitTemplate(connectionFactory);
	    template.setRoutingKey("si.test.queue");
	    String payload = "{\"contextElement\":{\"type\" : \"SensorTemperatura\",\"id\" : \"T1\",\"attributes\" : [{ \"name\" : \"temperature\",\"type\" : \"centigrade\",\"value\" : \"28\"}]}}";
	    for (int i = 0; i < 90000; i++) {
	    	template.convertAndSend(payload);
	    }
	    context.registerShutdownHook();

	}
}

