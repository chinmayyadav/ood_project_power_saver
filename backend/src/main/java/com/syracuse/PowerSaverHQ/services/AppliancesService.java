package com.syracuse.PowerSaverHQ.services;

import com.syracuse.PowerSaverHQ.models.ApplianceDetails;
import com.syracuse.PowerSaverHQ.utils.databaseConnection;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@Service
public class AppliancesService extends databaseConnection {
    public JSONArray getApplianceData(float rating){
        try{
            String query = "SELECT * FROM HomeAppliances WHERE ApplianceRating = ?";
            PreparedStatement pstmt = sql_connection.prepareStatement(query);
            pstmt.setFloat(1, rating);
            ResultSet rs = pstmt.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", rs.getInt("ID"));
                jsonObject.put("name", rs.getString("ApplianceName"));
                jsonObject.put("rating", rs.getDouble("ApplianceRating"));
                jsonObject.put("consumption", rs.getDouble("ApplianceConsumption"));
                jsonArray.put(jsonObject);
            }

            return jsonArray;
        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    public boolean addAppliances(ApplianceDetails applianceDetails){
        try{
            int addID = applianceDetails.getAddID();
            int applianceID = applianceDetails.getApplianceID();
            int count = applianceDetails.getCount();
            float hours = applianceDetails.getHours();
            int days = applianceDetails.getDays();

            String query = "insert into AddressApplianceMapping (AddressID, ApplianceID, Count, NoOfHours, NoOfDays )  values  (?,?,?,?,?)";

            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setInt(1,addID);
            stmt.setInt(2,applianceID);
            stmt.setInt(3,count);
            stmt.setFloat(4,hours);
            stmt.setInt(5,days);

            stmt.executeUpdate();

            return true;

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public JSONArray getUserApplianceData(int id) {
        try{
            String query = "SELECT A.AddressID, H.ApplianceName, A.AddressID, A.NoOfHours, A.NoOfDays FROM AddressApplianceMapping AS A\n" +
                    "JOIN UserAddress ON A.AddressID = UserAddress.ID\n" +
                    "JOIN HomeAppliances AS H ON H.ID = A.ApplianceID\n" +
                    "WHERE UserAddress.UserID = ?";
            PreparedStatement pstmt = sql_connection.prepareStatement(query);
            pstmt.setFloat(1, id);
            ResultSet rs = pstmt.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("AddressID", rs.getInt("AddressID"));
                jsonObject.put("ApplianceName", rs.getString("ApplianceName"));
                jsonObject.put("AddressID", rs.getInt("AddressID"));
                jsonObject.put("NoOfHours", rs.getInt("NoOfHours"));
                jsonObject.put("NoOfDays", rs.getInt("NoOfDays"));
                jsonArray.put(jsonObject);
            }

            return jsonArray;
        }catch (Exception e){
            System.out.println(e);
        }


        return null;
    }
}
