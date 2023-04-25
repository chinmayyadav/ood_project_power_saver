package com.syracuse.PowerSaverHQ.services;

import com.syracuse.PowerSaverHQ.models.ApplianceDetails;
import com.syracuse.PowerSaverHQ.models.UserDetails;
import com.syracuse.PowerSaverHQ.utils.Constants;
import com.syracuse.PowerSaverHQ.utils.databaseConnection;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONObject;

@Service
public class AppliancesService extends databaseConnection {
    public JSONArray getApplianceData(ApplianceDetails applianceDetails){
        try{
            String query = "SELECT * FROM HomeAppliances WHERE ApplianceRating";
            PreparedStatement pstmt = sql_connection.prepareStatement(query);
//            pstmt.setFloat(1, applianceDetails.getRating());
            ResultSet rs = pstmt.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", rs.getInt("ID"));
                jsonObject.put("name", rs.getString("ApplianceName"));
                jsonObject.put("rating", rs.getFloat("ApplianceRating"));
                jsonObject.put("consumption", rs.getFloat("ApplianceConsumption"));
                jsonArray.put(jsonObject);
            }

            return jsonArray;
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return null;
    }

    public String addAppliances(ApplianceDetails applianceDetails){
        try{
            int addID = applianceDetails.getAddID();
            int applianceID = applianceDetails.getApplianceID();
            int count = applianceDetails.getCount();
            int hours = applianceDetails.getHours();
            int days = applianceDetails.getDays();

            String query = "insert into AddressApplianceMapping (AddressID, ApplianceID, Count, NoOfHours, NoOfDays )  values  (?,?,?,?,?)";

            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setInt(1,addID);
            stmt.setInt(2,applianceID);
            stmt.setInt(3,count);
            stmt.setInt(4,hours);
            stmt.setInt(5,days);

            stmt.executeUpdate();

            return Constants.STATUS_SUCCESS;

        }catch (Exception e){
            System.out.println(e);
        }
        return Constants.STATUS_ERROR;
    }

    public JSONArray getUserApplianceData(UserDetails userDetails) {
        try{
            String query = "SELECT A.AddressID, H.ApplianceName, H.ApplianceRating, A.AddressID, A.NoOfHours, A.NoOfDays FROM AddressApplianceMapping AS A\n" +
                    "JOIN UserAddress ON A.AddressID = UserAddress.ID\n" +
                    "JOIN HomeAppliances AS H ON H.ID = A.ApplianceID\n" +
                    "WHERE UserAddress.UserID = ?";
            PreparedStatement pstmt = sql_connection.prepareStatement(query);
            pstmt.setFloat(1, userDetails.getUserID());
            ResultSet rs = pstmt.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("AddressID", rs.getInt("AddressID"));
                jsonObject.put("ApplianceName", rs.getString("ApplianceName"));
                jsonObject.put("AddressID", rs.getInt("AddressID"));
                jsonObject.put("NoOfHours", rs.getInt("NoOfHours"));
                jsonObject.put("NoOfDays", rs.getInt("NoOfDays"));
                jsonObject.put("ApplianceRating", rs.getFloat("ApplianceRating"));
                jsonArray.put(jsonObject);
            }

            return jsonArray;
        }catch (Exception e){
            System.out.println(e);
        }


        return null;
    }

    public JSONArray getAddressApplianceData(UserDetails userDetails) {
        try{
            String query = "SELECT A.ID, A.AddressID, H.ApplianceName, H.ApplianceRating, A.AddressID, A.NoOfHours, A.NoOfDays FROM AddressApplianceMapping AS A\n" +
                    "JOIN UserAddress ON A.AddressID = UserAddress.ID\n" +
                    "JOIN HomeAppliances AS H ON H.ID = A.ApplianceID\n" +
                    "WHERE UserAddress.ID = ?";
            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setFloat(1, userDetails.getAddressID());
            ResultSet rs = stmt.executeQuery();
            JSONArray jsonArray = new JSONArray();
            int rowCount = rs.getFetchSize();
//            System.out.println("rowCount");
//            System.out.println(rowCount);
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("AddressApplianceMappingID", rs.getInt("ID"));
                jsonObject.put("AddressID", rs.getInt("AddressID"));
                jsonObject.put("ApplianceName", rs.getString("ApplianceName"));
                jsonObject.put("AddressID", rs.getInt("AddressID"));
                jsonObject.put("NoOfHours", rs.getInt("NoOfHours"));
                jsonObject.put("NoOfDays", rs.getInt("NoOfDays"));
                jsonObject.put("ApplianceRating", rs.getFloat("ApplianceRating"));
                jsonArray.put(jsonObject);
            }
            return jsonArray;
        }catch (Exception e){
            System.out.println(e);
        }


        return null;
    }

    public String deleteAppliance(ApplianceDetails applianceDetails) {
        try{
            String query = "DELETE FROM AddressApplianceMapping WHERE ID = ?;\n";
            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setInt(1, applianceDetails.getAddressApplianceMappingID());
            stmt.executeUpdate();
            return Constants.STATUS_SUCCESS;
        }catch (Exception e){
            System.out.println(e);
            return Constants.STATUS_ERROR;
        }
    }
}
