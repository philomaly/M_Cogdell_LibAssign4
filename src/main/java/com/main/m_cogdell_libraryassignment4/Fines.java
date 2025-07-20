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

}
