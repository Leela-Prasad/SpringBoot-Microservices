package com.virtualpairprogrammers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PositionQueueListener {

	@Autowired
	private Data data;
	
	
	@JmsListener(destination="positionQueue	")
	public void process(Map<String,String> message) {
		data.updatePosition(message);
		//System.out.println("consuming message ..." + message);
	}
}
