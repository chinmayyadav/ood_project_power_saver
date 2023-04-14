package com.syracuse.PowerSaverHQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.*;

import java.awt.*;
import java.sql.*;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PowerSaverHqApplication {

//	public static final String DB_URI = "jdbc:mysql//localhost:3306/POWER_SAVER_HQ";

	public static void main(String[] args) {
		SpringApplication.run(PowerSaverHqApplication.class, args);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/POWER_SAVER_HQ", "root", "root1234");

			String query = "show tables;";

			PreparedStatement stmt = connection.prepareStatement(query);

			ResultSet results = stmt.executeQuery();

//			System.out.println(results);
//			Statement stmt=con.createStatement();
//			ResultSet rs=stmt.executeQuery("select * from emp");
			while(results.next())
				System.out.println(results.getString(1)+"  ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
