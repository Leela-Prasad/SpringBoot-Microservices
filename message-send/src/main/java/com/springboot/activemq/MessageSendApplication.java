package com.springboot.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class MessageSendApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MessageSendApplication.class, args);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		jmsTemplate.convertAndSend("sampleQueue", "Message From Application");
	}

}
