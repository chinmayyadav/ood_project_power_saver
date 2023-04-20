package com.syracuse.PowerSaverHQ.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
    public Connection sql_connection;
    public databaseConnection(){
        establishConnection();
    }
    public void establishConnection(){

        try {
         Class.forName("com.mysql.cj.jdbc.Driver");
        this.sql_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/power_saver_hq", "root", "root1234");


    }
        catch (ClassNotFoundException E){


    }
        catch (SQLException SE){
        System.out.println("SQL EXCEPTION");
        System.out.println(SE.getMessage());
    }
    }


}
