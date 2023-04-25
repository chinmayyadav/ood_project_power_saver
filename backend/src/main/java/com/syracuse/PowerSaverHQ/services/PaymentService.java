package com.syracuse.PowerSaverHQ.services;

import com.syracuse.PowerSaverHQ.models.PaymentDetails;
import com.syracuse.PowerSaverHQ.utils.Constants;
import com.syracuse.PowerSaverHQ.utils.databaseConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class PaymentService extends databaseConnection {
    public String savePreference(PaymentDetails paymentDetails) {
        try{
            Date date = Date.valueOf(paymentDetails.getCardExpiry());
            String query = "Insert into BillPaymentPreferences(BankAccTypeID, UserID, PaymentMethodPreferred, CardNumber, CardExpiry, CardCVV, BankAccNo, BankAccUserName, BankAccRoutingNo, CardDisplayNumber) Values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setInt(1, paymentDetails.getBankAccTypeID());
            stmt.setInt(2, paymentDetails.getUserID());
            stmt.setInt(3, paymentDetails.getPaymentMethodPreferred());
            stmt.setString(4, paymentDetails.getCardNumber());
            stmt.setDate(5, date);
            stmt.setString(6, paymentDetails.getCardCVV());
            stmt.setString(7, paymentDetails.getBankAccNo());
            stmt.setString(8, paymentDetails.getBankAccUserName());
            stmt.setString(9, paymentDetails.getBankAccRoutingNo());
            stmt.setInt(10, paymentDetails.getCardDisplayNumber());
            stmt.executeUpdate();
            return Constants.STATUS_SUCCESS;
        }catch (Exception e){
            System.out.println(e);
            return Constants.STATUS_ERROR;
        }
    }

    public JSONArray getCardNumber(PaymentDetails paymentDetails) {
        try {
            String query = "Select ID, CardDisplayNumber from BillPaymentPreferences where UserID = ?";
            PreparedStatement stmt = sql_connection.prepareStatement(query);
            stmt.setInt(1, paymentDetails.getUserID());
            ResultSet rs = stmt.executeQuery();
            JSONArray jsonArray = new JSONArray();
            while(rs.next()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("CardDisplayNumber", rs.getString("CardDisplayNumber"));
                jsonObject.put("ID", rs.getString("ID"));
                jsonArray.put(jsonObject);
            }
            return jsonArray;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
