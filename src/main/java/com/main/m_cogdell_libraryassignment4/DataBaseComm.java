package com.main.m_cogdell_libraryassignment4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseComm implements Insert, Retrieve, Update{

    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String user = "postgres";
    private static String password = "CrazyFastRiviera1968";

    public DataBaseComm() {
        DataBaseComm.url = url;
        DataBaseComm.user = user;
        DataBaseComm.password = password;
    }

    public void connect(){
        try{
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database...");
            System.out.println("Creating Tables....");
            String createSQL = "CREATE TABLE Authors (authorID INT PRIMARY KEY, fullName VARCHAR(50), " +
                    "nationality CHAR(30), birthYear INT);" +
                    "CREATE TABLE Books (bookID INT PRIMARY KEY, title VARCHAR(50), authorID INT" +
                    "FOREIGN KEY REFERENCES Authors (authorID), genre VARCHAR(50), " +
                    "publicationYear INT, ISBN VARCHAR(25), availableCopies INT);";
            conn.close();
            System.out.println("Database Connection Closed...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } // end connect() method

    // INSERT INTO <table> (<col1, ... , colN>) VALUES (<elementData>)
    @Override
    public void insert(){

        System.out.println("Inserting...");

    }

    @Override
    public void update(){
        System.out.println("Updating...");

    }

    /* SELECT <* or colName(s)>
       FROM <Table(s)>
       WHERE <condition(s)>
     */
    @Override
    public String retrieve() {
        return "Retrieving...";
    }

}
