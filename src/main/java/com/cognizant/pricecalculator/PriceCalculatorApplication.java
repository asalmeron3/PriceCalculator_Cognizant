package com.cognizant.pricecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class PriceCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceCalculatorApplication.class, args);
	}

}
