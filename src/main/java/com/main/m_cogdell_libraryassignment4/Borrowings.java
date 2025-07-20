package com.main.m_cogdell_libraryassignment4;

import java.sql.Date;

public class Borrowings {
    private static Integer memberID;
    private static String fullName;
    private static String email;
    private static String phone;
    private static Date joinDate;
    private static Boolean activeStatus;

    public Borrowings(Integer memberID, String fullName,String email, String phone,
                      Date joinDate, Boolean activeStatus) {
        this.memberID = memberID;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.joinDate = joinDate;
        this.activeStatus = activeStatus;

    }

    public Borrowings(Integer memberID) {
        this.memberID = memberID;
    }

    public Integer getMemberID() { return memberID; }
    public void setMemberID(Integer memberID) { this.memberID = memberID; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone(){ return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }
    public Boolean getActiveStatus() { return activeStatus; }

}
