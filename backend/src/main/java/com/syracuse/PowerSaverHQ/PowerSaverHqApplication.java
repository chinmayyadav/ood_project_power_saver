package com.syracuse.PowerSaverHQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PowerSaverHqApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerSaverHqApplication.class, args);
		System.out.println("Ayush lawda");
	}

}
