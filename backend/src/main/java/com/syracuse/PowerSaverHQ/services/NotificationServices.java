package com.syracuse.PowerSaverHQ.services;

import com.syracuse.PowerSaverHQ.models.NotificationPreferance;
import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.utils.Constants;
import com.syracuse.PowerSaverHQ.utils.databaseConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class NotificationServices extends databaseConnection {
    public String savePreference(NotificationPreferance notificationPreferance) {
        try{
            String query = "Insert into NotificationPreferences(AddressID, EmailNotification, PhoneNotification, ServicePhoneCalls) values(?,?,?,?)";
            PreparedStatement pstmt = sql_connection.prepareStatement(query);
            pstmt.setInt(1,notificationPreferance.getAddressID());
            pstmt.setBoolean(2, notificationPreferance.isEmailNotification());
            pstmt.setBoolean(3, notificationPreferance.isPhoneNotification());
            pstmt.setBoolean(4, notificationPreferance.isServicePhoneCalls());
            pstmt.executeUpdate();
            return Constants.STATUS_SUCCESS;
        }catch(Exception e) {
            return Constants.STATUS_ERROR;
        }
    }

    public JSONArray getPreference(UserDetails userDetails) {
        try{
            String query = "SELECT * FROM NotificationPreferences AS N\n" +
                    "JOIN UserAddress AS U ON N.AddressID = U.ID\n" +
                    "WHERE U.UserID = ? order by N.ID DESC limit 1";
            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setInt(1, userDetails.getUserID());
            ResultSet rs = stmt.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("addressID", rs.getInt("AddressID"));
                jsonObject.put("EmailNotification", rs.getBoolean("EmailNotification"));
                jsonObject.put("PhoneNotification", rs.getBoolean("PhoneNotification"));
                jsonObject.put("ServicePhoneCalls", rs.getBoolean("ServicePhoneCalls"));
                jsonArray.put(jsonObject);
            }
            return jsonArray;
        }catch (Exception e){
            return null;
        }
    }
}
