package com.virtualpairprogrammers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
// Enable Eureka Client makes our eureka clients tightly 
// coupled with Eureka, so if we want to go for zookeper,cloud foundry
// Clients we have to change this annotation in all the clients
// so it is better to use Discovery clients so that all clients
// are abstracted from the implementation
//@EnableEurekaClient
public class PositionreceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(PositionreceiverApplication.class, args);
	}
}
