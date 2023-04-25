package com.syracuse.PowerSaverHQ.models;

import java.sql.Date;

public class BillingDetails {
    private Date fromDate;
    private Date toDate;
    private int addID;
    private boolean isPaid;

    public Date getFromDate() {
    private int billID;
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getAddID() {
        return addID;
    }

    public void setAddID(int addID) {
        this.addID = addID;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }
}
