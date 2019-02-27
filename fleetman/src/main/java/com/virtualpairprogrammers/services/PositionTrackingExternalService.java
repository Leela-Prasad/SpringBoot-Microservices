package com.virtualpairprogrammers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.virtualpairprogrammers.controllers.Position;
import com.virtualpairprogrammers.data.VehicleRepository;
import com.virtualpairprogrammers.domain.Vehicle;

@Service
public class PositionTrackingExternalService {

	@Autowired
	private LoadBalancerClient loadBalancer;
	
	@Autowired
	private VehicleRepository repository;
	
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
		
		response.setUpToDate(true);

		return response;
	}
	
	// Fallback Method signature should be same as that of 
	// method where exception is originating.
	public Position handleExternalServiceDown(String name) {
		System.out.println("Running Fallback");
		
		Vehicle vehicle = repository.findByName(name);
		
		Position position = new Position();
		position.setLat(vehicle.getLat());
		position.setLongitude(vehicle.getLongitude());
		position.setTimestamp(vehicle.getLastRecordedPosition());
		position.setUpToDate(false);
		return position;
	}
}
