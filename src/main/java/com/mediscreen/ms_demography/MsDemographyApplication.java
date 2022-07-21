package com.mediscreen.ms_demography;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class MsDemographyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDemographyApplication.class, args);
	}

}
