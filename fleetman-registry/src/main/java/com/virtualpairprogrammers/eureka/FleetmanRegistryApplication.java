package com.virtualpairprogrammers.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FleetmanRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FleetmanRegistryApplication.class, args);
	}

}