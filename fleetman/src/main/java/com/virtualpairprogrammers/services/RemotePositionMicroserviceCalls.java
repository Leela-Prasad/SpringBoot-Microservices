package com.virtualpairprogrammers.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.virtualpairprogrammers.controllers.Position;

//name is required to let feign know the name of the 
//microservice that is registered with eureka
@FeignClient(name="FLEETMAN-POSITION-TRACKER")
public interface RemotePositionMicroserviceCalls {

	// /vehicles/{name}
	//Here hostname and port is automatically prefixed by feign.
	@RequestMapping(method=RequestMethod.GET, value="/vehicles/{name}")
	public Position getLatestPositionForVehicle(@PathVariable("name") String name);
	
}
