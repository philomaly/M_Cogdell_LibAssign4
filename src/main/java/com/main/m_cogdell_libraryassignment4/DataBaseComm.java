package com.main.m_cogdell_libraryassignment4;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DataBaseComm implements Insert, Search, Update {

    private static String url = "jdbc:postgresql://localhost:5432/LibrarySystem";
    private static String user = "postgres";
    private static String password = "CrazyFastRiviera1968";
    public Connection conn;
    public DatabaseMetaData meta;       // Metadata object to ensure proper connection to intended database
    private static Schema sch;          // Schema object for DDL Tables and Initial Insertions into DBase (See 'Schema' class)
    public PreparedStatement schemaStmt, initStmt, newMember, newBook,
            newBorrowRecord, newPaymentRecord;
    Members membersObject;
    Books booksObject;
    Borrowings borrowingsObject;
    Payments paymentsObject;
    Scanner in = new Scanner(System.in);

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

//=====================================================================================================

    @Override
    public void insert() throws SQLException {
        // Insert: new members, new books, borrowings record, payment record

        // Insert New Member
        membersObject = new Members(in.nextInt(), in.nextLine(), in.nextLine(), in.nextLine(),
                in.nextLine(), in.nextBoolean());
        System.out.println("Inserting New Member...");
        String addMemberQuery = "INSERT INTO Members (memberID, fullName, email, phone, joinDate, activeStatus)" +
                "VALUES(?,?,?,?,?,?)";
        newMember = conn.prepareStatement(addMemberQuery);
        newMember.setInt(1, membersObject.getMemberID());
        newMember.setString(2, membersObject.getFullName());
        newMember.setString(3, membersObject.getEmail());
        newMember.setString(4, membersObject.getPhone());
        newMember.setString(5, membersObject.getJoinDate());
        newMember.setBoolean(6, membersObject.getActiveStatus());
        newMember.execute();

        // Insert New Book
        booksObject = new Books(in.nextInt(), in.nextLine(), in.nextLine(), in.nextInt(),
                in.nextLine(), in.nextInt());
        System.out.println("Inserting New Book...");
        String addBookQuery = "INSERT INTO Books (bookID, title, genre, publicationYear, ISBN, availableCopies)" +
                "VALUES(?,?,?,?,?,?)";
        newBook = conn.prepareStatement(addBookQuery);
        newBook.setInt(1, booksObject.getBookID());
        newBook.setString(2, booksObject.getTitle());
        newBook.setString(3, booksObject.getGenre());
        newBook.setInt(3, booksObject.getPublicationYear());
        newBook.setString(4, booksObject.getISBN());
        newBook.setInt(5, booksObject.getAvailableCopies());
        newBook.execute();

        // Insert New Borrowings Record
        borrowingsObject = new Borrowings(in.nextInt(), in.nextLine(), in.nextLine(), in.nextLine(),
                in.nextLine(), in.nextBoolean());

        // Insert New Payment Record
    }

    //==================================================================================================

    @Override
    public void update(){
        // Update: Return date of a borrowed book, payment status of a fine
        System.out.println("Updating...");
    }

    @Override
    public String search() {
        // Search for books by title, author, and genre
        // Borrowing history for a member
        return "";
    } // end search() method

    public void closeConnection() throws SQLException {
        conn.close();
        System.out.println("Database Connection Closed...");
    }

}
