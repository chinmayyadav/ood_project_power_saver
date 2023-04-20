package com.syracuse.PowerSaverHQ.services;


import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.utils.databaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

import static com.syracuse.PowerSaverHQ.utils.generateAccountNumber.generateAccountNumber;

@Service
public class UserService extends databaseConnection {
    public Boolean RegisterUser(UserDetails user) {


            try {
            String AccountNumber = generateAccountNumber();
            String FirstName = user.getFirstName();
            String LastName = user.getLastName();
            String Email = user.getEmail();
            String Password = user.getPassword();
            String query = "insert into UserDetails (FirstName, LastName, Email, Password, AccountNumber )  values  (?,?,?,?,?)";

            PreparedStatement stmt = sql_connection.prepareStatement(query);

            stmt.setString(1, FirstName);
            stmt.setString(2, LastName);
            stmt.setString(3, Email);
            stmt.setString(4, Password);
            stmt.setString(5, AccountNumber);
            int row = stmt.executeUpdate();
            return true;
        }

        catch (SQLException SE){
                    System.out.println("SQL SERVER ERROR");
                    System.out.println(SE.getMessage());
                    return false;
        }

    }
}
