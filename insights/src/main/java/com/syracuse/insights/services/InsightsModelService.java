package com.syracuse.insights.services;

import com.syracuse.insights.models.InsightsModel;
import com.syracuse.insights.utils.databaseConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class InsightsModelService extends databaseConnection {
    public JSONArray currenUserData(InsightsModel insightsModel){
        try{
            String query = "SELECT AVG(ElectricityAmount) as avgElectricityAmount, AVG(GasAmount) as avgGasAmount\n" +
                    "FROM BillHistory where\n" +
                    "AddressID IN (SELECT ID FROM UserAddress\n" +
                    "WHERE UserID = ?)";

            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setInt(1, insightsModel.getUserID());
            JSONArray jsonArray = new JSONArray();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("avgElectricityAmount", rs.getInt("avgElectricityAmount"));
                jsonObject.put("avgGasAmount", rs.getInt("avgGasAmount"));
                jsonArray.put(jsonObject);
            }

            return jsonArray;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public JSONArray neighbourData(InsightsModel insightsModel) {
        try{
            String query = "SELECT AVG(ElectricityAmount) as avgElectricityAmount, AVG(GasAmount) as avgGasAmount\n" +
                    "FROM BillHistory where\n" +
                    "AddressID IN (SELECT ID FROM UserAddress\n" +
                    "WHERE ZipCode IN (SELECT zipcode FROM UserAddress WHERE UserID = ?))";
            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setInt(1, insightsModel.getUserID());
            JSONArray jsonArray = new JSONArray();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("avgElectricityAmount", rs.getInt("avgElectricityAmount"));
                jsonObject.put("avgGasAmount", rs.getInt("avgGasAmount"));
                jsonArray.put(jsonObject);
            }
            return jsonArray;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public JSONArray compareAppliances(InsightsModel insightsModel) {
        try{
            String currentuser = "SELECT ha.ApplianceName, ha.ApplianceRating, (aam.Count * aam.NoOfHours) as UserDailyHoursUsage,(aam.Count * aam.NoOfHours * aam.NoOfDays) as UserMonthlyHoursUsage, aam.NoOfDays as UserNoOfDays, aam.AddressID\n" +
                    "FROM HomeAppliances ha\n" +
                    "JOIN AddressApplianceMapping aam ON ha.ID = aam.ApplianceID\n" +
                    "WHERE aam.AddressID IN (\n" +
                    "    SELECT ID FROM UserAddress \n" +
                    "    WHERE Zipcode IN (\n" +
                    "        SELECT Zipcode FROM UserAddress \n" +
                    "        WHERE UserID = ?\n" +
                    "    ) AND UserID = ?\n" +
                    ")\n" +
                    "AND aam.ApplianceID IN (\n" +
                    "    SELECT ApplianceID FROM AddressApplianceMapping \n" +
                    "    WHERE AddressID IN (\n" +
                    "        SELECT ID FROM UserAddress \n" +
                    "        WHERE UserID = ?\n" +
                    "    )\n" +
                    ");";
            String neighbour = "SELECT ha.ApplianceName, ha.ApplianceRating, (aam.Count * aam.NoOfHours) as NeighboursDailyHoursUsage,(aam.Count * aam.NoOfHours * aam.NoOfDays) as NeighboursMonthlyHoursUsage, aam.NoOfDays as NeighboursNoOfDays, aam.AddressID\n" +
                    "FROM HomeAppliances ha\n" +
                    "JOIN AddressApplianceMapping aam ON ha.ID = aam.ApplianceID\n" +
                    "WHERE aam.AddressID IN (\n" +
                    "    SELECT ID FROM UserAddress \n" +
                    "    WHERE Zipcode IN (\n" +
                    "        SELECT Zipcode FROM UserAddress \n" +
                    "        WHERE UserID = ?\n" +
                    "    ) AND UserID <> ?\n" +
                    ")\n" +
                    "AND aam.ApplianceID IN (\n" +
                    "    SELECT ApplianceID FROM AddressApplianceMapping \n" +
                    "    WHERE AddressID IN (\n" +
                    "        SELECT ID FROM UserAddress \n" +
                    "        WHERE UserID = ?\n" +
                    "    )\n" +
                    ");";
            PreparedStatement stmt1 = sql_connection.prepareStatement(currentuser);
            PreparedStatement stmt2 = sql_connection.prepareStatement(neighbour);
            stmt1.setInt(1,insightsModel.getUserID());
            stmt1.setInt(2,insightsModel.getUserID());
            stmt1.setInt(3,insightsModel.getUserID());
            stmt2.setInt(1,insightsModel.getUserID());
            stmt2.setInt(2,insightsModel.getUserID());
            stmt2.setInt(3,insightsModel.getUserID());

            ResultSet rs1 = stmt1.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while(rs1.next() && rs2.next()){
                System.out.println(1);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ApplianceName", rs1.getString("ApplianceName"));
                jsonObject.put("ApplianceRating", rs1.getInt("ApplianceRating"));
                jsonObject.put("UserDailyHoursUsage", rs1.getInt("UserDailyHoursUsage"));
                jsonObject.put("NeighboursDailyHoursUsage", rs2.getInt("NeighboursDailyHoursUsage"));
                jsonObject.put("UserMonthlyHoursUsage", rs1.getInt("UserMonthlyHoursUsage"));
                jsonObject.put("NeighboursMonthlyHoursUsage", rs2.getInt("NeighboursMonthlyHoursUsage"));
                jsonObject.put("UserNoOfDays", rs1.getInt("UserNoOfDays"));
                jsonObject.put("NeighboursNoOfDays", rs2.getInt("NeighboursNoOfDays"));
                jsonArray.put(jsonObject);
            }
            return jsonArray;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }

    }
}
