package com.syracuse.PowerSaverHQ.services;

import com.syracuse.PowerSaverHQ.models.AddressesDetails;
import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.utils.Constants;
import org.apache.tomcat.util.bcel.Const;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.syracuse.PowerSaverHQ.utils.databaseConnection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Service
public class AddressServices extends databaseConnection{

    public JSONArray getAddressesUser(UserDetails userDetails) {
        try{
            String query = "SELECT AddressLine1, AddressLine2, City, State, ZipCode, ApartmentNumber FROM UserAddress WHERE UserID = ?";
            PreparedStatement pstmt = sql_connection.prepareStatement(query);
            pstmt.setFloat(1, userDetails.getUserID());
            ResultSet rs = pstmt.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("AddressLine1", rs.getString("AddressLine1"));
                jsonObject.put("AddressLine2", rs.getString("AddressLine2"));
                jsonObject.put("City", rs.getString("City"));
                jsonObject.put("State", rs.getString("State"));
                jsonObject.put("ZipCode", rs.getInt("ZipCode"));
                jsonObject.put("ApartmentNumber", rs.getInt("ApartmentNumber"));

                jsonArray.put(jsonObject);
            }

            return jsonArray;
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    public String saveAddressUser(AddressesDetails addressesDetails) {
        try{
            int userID = addressesDetails.getUserID();
            String add1 = addressesDetails.getAddressLine1();
            String add2 = addressesDetails.getAddressLine2();
            String city = addressesDetails.getCity();
            String state = addressesDetails.getState();
            int zipcode = addressesDetails.getZipcode();
            int aptno = addressesDetails.getApartmentNumber();

            String query = "Insert into UserAddress (UserID, AddressLine1, AddressLine2, City, State, ZipCode, ApartmentNumber) Values (?,?,?,?,?,?,?)";
            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setInt(1, userID);
            stmt.setString(2, add1);
            stmt.setString(3, add2);
            stmt.setString(4, city);
            stmt.setString(5, state);
            stmt.setInt(6, zipcode);
            stmt.setInt(7, aptno);

            stmt.executeUpdate();

            return Constants.STATUS_SUCCESS;

        }catch(Exception e) {
            return Constants.STATUS_ERROR;
        }
    }

    public JSONArray getAddressesID(UserDetails userDetails) {
        try{
            String query = "SELECT AddressLine1, AddressLine2, City, State, ZipCode, ApartmentNumber FROM UserAddress WHERE ID = ?";
            PreparedStatement pstmt = sql_connection.prepareStatement(query);
            pstmt.setFloat(1, userDetails.getAddressID());
            ResultSet rs = pstmt.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("AddressLine1", rs.getString("AddressLine1"));
                jsonObject.put("AddressLine2", rs.getString("AddressLine2"));
                jsonObject.put("City", rs.getString("City"));
                jsonObject.put("State", rs.getString("State"));
                jsonObject.put("ZipCode", rs.getInt("ZipCode"));
                jsonObject.put("ApartmentNumber", rs.getInt("ApartmentNumber"));
                jsonArray.put(jsonObject);
            }

            return jsonArray;
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }
}
