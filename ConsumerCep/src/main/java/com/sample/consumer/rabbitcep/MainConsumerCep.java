package com.sample.consumer.rabbitcep;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public final class MainConsumerCep {

	private MainConsumerCep() { }
	
	public static void main(final String... args) {

		@SuppressWarnings("resource")
		final AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		context.registerShutdownHook();

	}
}

