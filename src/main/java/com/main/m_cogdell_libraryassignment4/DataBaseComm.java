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
            System.out.println("Creating and Loading Tables....");

            Schema sch = new Schema();
            String schema = sch.getSchema();
            String init = sch.getInitialInserts();

            // Display output for verification
            System.out.println(schema);
            System.out.println(init);

            System.out.println("Tables and Initial Insert Queries Loaded...");



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
