package com.main.m_cogdell_libraryassignment4;

import java.sql.Date;

public class Payments {
    private static Integer paymentID;
    private static Integer fineID;
    private static Integer memberID;
    private static Date paymentDate;
    private static String method;
    private static Double amountPaid;

    public Payments(Integer paymentID, Integer fineID,
                    Integer memberID, Date paymentDate, String method, Double amountPaid) {
        this.paymentID = paymentID;
        this.fineID = fineID;
        this.memberID = memberID;
        this.paymentDate = paymentDate;
        this.method = method;
    }

    public Payments(Integer memberID) {
        this.memberID = memberID;
    }

    //Getters/Setters
    public Integer getPaymentID(){ return paymentID; }
    public void setPaymentID(Integer paymentID) { this.paymentID = paymentID; }
    public Integer getFineID() { return fineID; }
    public void setFineID(Integer fineID) { this.fineID = fineID; }
    public Integer getMemberID() { return memberID; }
    public void setMemberID(Integer memberID) { this.memberID = memberID; }
    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public Double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(Double amountPaid) { this.amountPaid = amountPaid; }
}
