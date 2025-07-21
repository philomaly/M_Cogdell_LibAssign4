package com.main.m_cogdell_libraryassignment4;

import java.sql.Date;

public class Fines {

    private static Integer fineID;
    private static Integer memberID;
    private static Date borrowDate;
    private static Double amount;
    private static Boolean paidStatus;
    private static Date paymentDate;

    public Fines (Integer fineID){
        this.fineID = fineID;
    }

    // Getters/Setters
    public Integer getFineID() { return fineID; }
    public void setFineID(Integer fineID) {this.fineID = fineID; }
    public Integer getMemberID() { return memberID; }
    public void setMemberID(Integer memberID) { this.memberID = memberID; };
    public Date getBorrowDate() { return borrowDate; }
    public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public Boolean getPaidStatus() { return paidStatus; }
    public void setPaidStatus(Boolean paidStatus) { this.paidStatus = paidStatus; }
    public Date getPaymentDate(){ return paymentDate; }
    public void setPaymentDate(Date paymentDate) {this.paymentDate = paymentDate;}

}
