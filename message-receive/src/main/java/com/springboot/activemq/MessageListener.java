package com.springboot.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	@JmsListener(destination="sampleQueue")
	public void processMessage(String message) {
		System.out.println("Processing Message :" + message);
		
		/*if(true)
			throw new RuntimeException("crashed!!!");*/
		
		System.out.println("Processing Completes");
	}
}
