package com.springboot.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class MessageReceiveApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MessageReceiveApplication.class, args);
		/*JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		System.out.println(jmsTemplate.getReceiveTimeout());
		jmsTemplate.setReceiveTimeout(1000);
		System.out.println(jmsTemplate.receiveAndConvert("sampleQueue"));*/
	}

}
