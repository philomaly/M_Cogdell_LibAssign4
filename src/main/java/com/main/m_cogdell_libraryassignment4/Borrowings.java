package com.main.m_cogdell_libraryassignment4;

import java.sql.Date;

public class Borrowings {
    private static Integer memberID;
    private static Integer bookID;
    private static String borrowDate;
    private static String dueDate;
    private static String returnDate;

    public Borrowings() {}

    public Integer getMemberID() { return memberID; }
    public void setMemberID(Integer memberID) { this.memberID = memberID; }
    public Integer getBookID() { return bookID; }
    public void setBookID(Integer bookID) { this.bookID = bookID; }
    public String getBorrowDate() { return borrowDate; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }
    public String getdueDate(){ return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }


}
