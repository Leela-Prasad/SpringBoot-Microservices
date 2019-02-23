package com.virtualpairprogrammers.services;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.virtualpairprogrammers.controllers.Position;

@Service
public class PositionTrackingExternalService {

	@Autowired
	private LoadBalancerClient loadBalancer;
	
	//In fallback mechanism we will wrap the code which may throw an exception into a separate method
	//and annotate that method with @HystrixCommand which indicates the fallback that needs to be 
	//run in case of failures.
	@HystrixCommand(fallbackMethod="handleExternalServiceDown")
	public Position getLastestPositionForVehicleFromRemoteMicroservice(String name) {
		RestTemplate rest = new RestTemplate();
		
		System.out.println("Calling Eureka");
		ServiceInstance serviceInstance = loadBalancer.choose("FLEETMAN-POSITION-TRACKER");
		
		if(serviceInstance== null) {
			System.out.println("No instances of Position Tracker");
			throw new RuntimeException("Fleetman Position Tracker is Crashed!!!");
		}
		
		System.out.println("Found Instances for Position Tracker");
		String physicalLocation = serviceInstance.getUri().toString();
		System.out.println("PHYSICAL LOCATION :::: " + physicalLocation);
		
		Position response = rest.getForObject(physicalLocation + "/vehicles/" + name, Position.class);
		System.out.println("SUCCESS!!!");
		return response;
	}
	
	// Fallback Method signature should be same as that of 
	// method where exception is originating.
	public Position handleExternalServiceDown(String name) {
		System.out.println("Running Fallback");
		Position position = new Position();
		position.setLat(new BigDecimal("41.0"));
		position.setLongitude(new BigDecimal("0.0"));
		position.setTimestamp(new Date());
		return position;
	}
}
