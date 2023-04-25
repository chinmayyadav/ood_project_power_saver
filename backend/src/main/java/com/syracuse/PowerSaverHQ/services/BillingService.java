package com.syracuse.PowerSaverHQ.services;

import com.syracuse.PowerSaverHQ.models.BillingDetails;
import com.syracuse.PowerSaverHQ.utils.Constants;
import com.syracuse.PowerSaverHQ.utils.databaseConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class BillingService extends databaseConnection {

    public JSONArray getBillData(BillingDetails billingDetails) {
        try{
            String query = "SELECT SUM(ElectricityAmount) AS SumOfElectricityAmount, SUM(GasAmount) AS SumOfGasAmount\n" +
                    "FROM BillHistory WHERE AddressID = ? AND FromDate > ? AND ToDate < ?";
            PreparedStatement stmt = sql_connection.prepareStatement(query);

            stmt.setInt(1, billingDetails.getAddID());
            stmt.setString(2, billingDetails.getFromDate());
            stmt.setString(3, billingDetails.getToDate());
            ResultSet rs = stmt.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("SumOfElectricityAmount", rs.getFloat("SumOfElectricityAmount"));
                jsonObject.put("SumOfGasAmount", rs.getFloat("SumOfGasAmount"));
                jsonArray.put(jsonObject);
            }
            return jsonArray;
        }catch(Exception e){
            return null;
        }
    }

    public JSONArray getUnpaidBill(BillingDetails billingDetails) {
        try{
            String query = "SELECT * from BillHistory\n" +
                    "WHERE AddressID = ? and isPaid = 0\n" +
                    "ORDER BY FromDate DESC;";
            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setInt(1, billingDetails.getAddID());
            ResultSet rs = stmt.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("FromDate", rs.getString("FromDate"));
                jsonObject.put("ToDate", rs.getString("ToDate"));
                jsonObject.put("ElectricityAmount", rs.getFloat("ElectricityAmount"));
                jsonObject.put("GasAmount", rs.getFloat("GasAmount"));
                jsonArray.put(jsonObject);
            }
            return jsonArray;
        } catch(Exception e){
            return null;
        }

    }

    public String getpayBill(BillingDetails billingDetails) {
        try {
            String query = "UPDATE BillHistory SET isPaid = 1\n" +
                    "WHERE ID = ?\n ";
            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setInt(1, billingDetails.getBillID());
            stmt.executeQuery();
            return Constants.STATUS_SUCCESS;
        }catch (Exception e){
            return Constants.STATUS_ERROR;
        }


    }
}
