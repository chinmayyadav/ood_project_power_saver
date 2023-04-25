package com.syracuse.PowerSaverHQ.models;

import com.syracuse.PowerSaverHQ.utils.hashingFunction;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class PaymentDetails extends hashingFunction {
    private int bankAccTypeID;
    private int userID;
    private int paymentMethodPreferred;
    private String cardNumber;
    private String cardExpiry;
    private String cardCVV;
    private String bankAccNo;
    private String bankAccUserName;
    private String bankAccRoutingNo;
    private int cardDisplayNumber;
    private boolean isCard;

    public void PaymentDetails(int bankAccTypeID, int userID, int paymentMethodPreferred, String cardNumber, String cardExpiry, String cardCVV, String bankAccNo, String bankAccUserName, String bankAccRoutingNo, int cardDisplayNumber ){
        this.bankAccTypeID = bankAccTypeID;
        this.userID = userID;
        this.paymentMethodPreferred = paymentMethodPreferred;
        this.cardNumber = toHash(cardNumber);
        this.cardExpiry = cardExpiry;
        this.cardCVV = toHash(cardCVV);
        this.bankAccNo = toHash(bankAccNo);
        this.bankAccUserName = bankAccUserName;
        this.bankAccRoutingNo = toHash(bankAccRoutingNo);
        this.cardDisplayNumber = cardDisplayNumber;
    }

    public int getBankAccTypeID() {
        return bankAccTypeID;
    }

    public void setBankAccTypeID(int bankAccTypeID) {
        this.bankAccTypeID = bankAccTypeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPaymentMethodPreferred() {
        return paymentMethodPreferred;
    }

    public void setPaymentMethodPreferred(int paymentMethodPreferred) {
        this.paymentMethodPreferred = paymentMethodPreferred;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = toHash(cardNumber);
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = toHash(cardCVV);
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = toHash(bankAccNo);
    }

    public String getBankAccUserName() {
        return bankAccUserName;
    }

    public void setBankAccUserName(String bankAccUserName) {
        this.bankAccUserName = bankAccUserName;
    }

    public String getBankAccRoutingNo() {
        return bankAccRoutingNo;
    }

    public void setBankAccRoutingNo(String bankAccRoutingNo) {
        this.bankAccRoutingNo = toHash(bankAccRoutingNo);
    }

    public int getCardDisplayNumber() {
        return cardDisplayNumber;
    }

    public void setCardDisplayNumber(int cardDisplayNumber) {
        this.cardDisplayNumber = cardDisplayNumber;
    }

    public boolean isCard() {
        return isCard;
    }

    public void setCard(boolean card) {
        isCard = card;
    }
}
