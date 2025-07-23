package com.main.m_cogdell_libraryassignment4;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseComm implements Insert, Retrieve, Update {

    private static String url = "jdbc:postgresql://localhost:5432/LibrarySystem";
    private static String user = "postgres";
    private static String password = "CrazyFastRiviera1968";
    public Connection conn;
    public DatabaseMetaData meta;       // Metadata object to ensure proper connection to intended database
    private static Schema sch;          // Schema object for DDL Tables and Initial Insertions into DBase (See 'Schema' class)

    // Constructor initializes all Connection class fields
    public DataBaseComm() throws SQLException {
        DataBaseComm.url = url;
        DataBaseComm.user = user;
        DataBaseComm.password = password;
        this.conn = DriverManager.getConnection(url, user, password);
        this.meta = conn.getMetaData();
        this.sch = new Schema();
    }

    // connect() Method connects to, creates schema, and initializes/populates database with data
    public void connect(){
        try{

            // Output to console to verify connection
            System.out.println("Connected to database: " + meta.getDatabaseProductName());
            System.out.println("URL: " + meta.getURL());


            PreparedStatement schemaStmt, initStmt;
            String schema = sch.getSchema();        // Store DDL portion of script into "schema" variable for Schema Creation
            String init = sch.getInitialInserts();  // Store DML portion of script into "init" variable for initial population of dBase

            // Create Schema
            schemaStmt = conn.prepareStatement(schema);
            schemaStmt.execute();
            System.out.println("Schema Created....");

            // Initial Insertion/Database Population
            initStmt = conn.prepareStatement(init);
            initStmt.execute();
            System.out.println("Database populated...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } // end connect() method



    @Override
    public void insert() {
        System.out.println("Inserting...");
    }

    @Override
    public void update(){
        System.out.println("Updating...");
    }

    @Override
    public String retrieve() {
        return "Retrieving...";
    }

    public void dropTables(String table) {
        String drop = "DROP TABLE (table);";
    }

    public void closeConnection() throws SQLException {
        conn.close();
        System.out.println("Database Connection Closed...");
    }

}
