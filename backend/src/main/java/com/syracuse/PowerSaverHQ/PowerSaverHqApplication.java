package com.syracuse.PowerSaverHQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PowerSaverHqApplication {

//	public static final String DB_URI = "jdbc:mysql//localhost:3306/POWER_SAVER_HQ";

	public static void main(String[] args) {
		SpringApplication.run(PowerSaverHqApplication.class, args);
	}

}
