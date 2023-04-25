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
            String check = "Select * from BillPaymentPreferences where UserID = ?";
            PreparedStatement checkstmt = sql_connection.prepareStatement(check);
            checkstmt.setInt(1, paymentDetails.getUserID());
            ResultSet rs = checkstmt.executeQuery();
            int rowCount  = rs.getFetchSize();
            if(rowCount>0){
                if(paymentDetails.isCard()){
                    String update = "UPDATE BillPaymentPreferences SET CardNumber, CardExpiry, CardCVV, CardDisplayNumber, PaymentMethodPreferred VALUES(?,?,?,?,?) Where UserId = ?";
                    PreparedStatement updatestmt = sql_connection.prepareStatement(update);
                    updatestmt.setString(1, paymentDetails.getCardNumber());
                    updatestmt.setDate(2, date);
                    updatestmt.setString(3, paymentDetails.getCardCVV());
                    updatestmt.setInt(4, paymentDetails.getCardDisplayNumber());
                    updatestmt.setInt(5, 1);
                    updatestmt.setInt(6, paymentDetails.getUserID());
                    updatestmt.executeUpdate();
                    return Constants.STATUS_SUCCESS;
                }
                else {
                    String updateBank = "Update BillPaymentPreferences SET BankAccTypeID, BankAccNo, BankAccUserName, BankAccRoutingNo, PaymentMethodPreferred VALUES(?,?,?,?,?) WHERE UserID = ?";
                    PreparedStatement stmtBank = sql_connection.prepareStatement(updateBank);
                    stmtBank.setInt(1, paymentDetails.getBankAccTypeID());
                    stmtBank.setString(2, paymentDetails.getBankAccNo());
                    stmtBank.setString(3, paymentDetails.getBankAccUserName());
                    stmtBank.setString(4, paymentDetails.getBankAccRoutingNo());
                    stmtBank.setInt(5, 3);
                    stmtBank.setInt(6, paymentDetails.getUserID());
                    stmtBank.executeUpdate();
                    return Constants.STATUS_SUCCESS;
                }
            }else {
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
            }
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
