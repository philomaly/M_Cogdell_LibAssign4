package com.main.m_cogdell_libraryassignment4;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DataBaseComm implements Update {

    private static String url = "jdbc:postgresql://localhost:5432/LibrarySystem";
    private static String user = "postgres";
    private static String password = "CrazyFastRiviera1968";
    public Connection conn;
    public DatabaseMetaData meta;       // Metadata object to ensure proper connection to intended database
    private static Schema sch;          // Schema object for DDL Tables and Initial Insertions into DBase (See 'Schema' class)
    public PreparedStatement schemaStmt, initStmt, newMember, newBook,
            newBorrowRecord, newPaymentRecord, bookByTitleSearch, bookByAuthorSearch, bookByGenreSearch;
    Members membersObject;
    Books booksObject;
    Borrowings borrowingsObject;
    Payments paymentsObject;
    Scanner in = new Scanner(System.in);
    public Boolean schemaInitialized;

    // Constructor initializes path and credentials upon object instantiation
    public DataBaseComm() throws SQLException {
        DataBaseComm.url = url;
        DataBaseComm.user = user;
        DataBaseComm.password = password;

    }

//======================================================================================================================

    // Connect to Database
    public void connect() throws SQLException {

        this.conn = DriverManager.getConnection(url, user, password);
        this.meta = conn.getMetaData();
        this.sch = new Schema();
        System.out.println("Connected to database: " + meta.getDatabaseProductName());
        System.out.println("URL: " + meta.getURL());
        System.out.println();

    }

//======================================================================================================================

// schemaCheck() Helper Method: Checks whether tables/schema are already loaded in database, or not
    public Boolean schemaCheck(String tableName) throws SQLException {
        ResultSet tableExists = meta.getTables(null, null, tableName, null);
        if (tableExists.next()) {
            tableExists.close();
            return true;
        } else {
            return false;
        }
    } // end schemaCheck Method

//======================================================================================================================

    // Load Schema
    public void loadSchema() throws SQLException {
        // Create Schema
        schemaInitialized = schemaCheck("authors");
        if(!schemaInitialized) {
            String schema = sch.getSchema();        // Store DDL portion of script into "schema" variable for Schema Creation
            schemaStmt = conn.prepareStatement(schema);
            schemaStmt.execute();
            System.out.println("Schema Created....");
        } else {
            System.out.println("Schema Already Loaded...");
            return;
        }
    } // end loadSchema Method

//======================================================================================================================

    // Load Initial Insertion Queries
    public void loadInitialInserts() throws SQLException {
        // Initial Insertion/Database Population
        String init = sch.getInitialInserts();  // Store DML portion of script into "init" variable for initial population of dBase
        initStmt = conn.prepareStatement(init);
        initStmt.execute();
        System.out.println("Database populated...");
    } // end loadInitialInserts Method

//======================================================================================================================

    public void insertMember() throws SQLException {
        // Insert: new members, new books, borrowings record, payment record

        // Insert New Member
        membersObject = new Members();

        System.out.println("Enter Member ID...");
        membersObject.setMemberID(in.nextInt());
        in.nextLine(); // corrects /n buffer issue left by in.nextInt()

        System.out.println("Enter Full Name...");
        membersObject.setFullName(in.nextLine());

        System.out.println("Enter email... ");
        membersObject.setEmail(in.next());

        System.out.println("Enter Phone number (no spaces)...");
        membersObject.setPhone(in.next());

        System.out.println("Enter Join date (YYYY-MM-DD)...");
        membersObject.setJoinDate(in.next());

        System.out.println("Enter Active Status (true or false)");
        membersObject.setActiveStatus(in.nextBoolean());

        System.out.println("Inserting New Member...");
        String addMemberQuery = "INSERT INTO Members (memberID, fullName, email, phone, joinDate, activeStatus)" +
                "VALUES(?, ?, ?, ?, CAST(? AS DATE), ?)";
        newMember = conn.prepareStatement(addMemberQuery);
        newMember.setInt(1, membersObject.getMemberID());
        newMember.setString(2, membersObject.getFullName());
        newMember.setString(3, membersObject.getEmail());
        newMember.setString(4, membersObject.getPhone());
        newMember.setString(5, membersObject.getJoinDate());
        newMember.setBoolean(6, membersObject.getActiveStatus());
        newMember.execute();

    } // end insertMember Method

//======================================================================================================================

    // Insert New Book
    public void insertBook() throws SQLException {
        booksObject = new Books();

        System.out.println("Enter Book ID...");
        booksObject.setBookID(in.nextInt());
        in.nextLine();

        System.out.println("Enter Title...");
        booksObject.setTitle(in.nextLine());

        System.out.println("Enter Genre...");
        booksObject.setGenre(in.nextLine());

        System.out.println("Enter Publication Year...");
        booksObject.setPublicationYear(in.nextInt());
        in.nextLine();

        System.out.println("Enter ISBN (No Spaces)...");
        booksObject.setISBN(in.next());

        System.out.println("Enter Number of Availabe Copies...");
        booksObject.setAvailableCopies(in.nextInt());
        in.nextLine();

        System.out.println("Inserting New Book...");
        String addBookQuery = "INSERT INTO Books (bookID, title, genre, publicationYear, ISBN, availableCopies)" +
                "VALUES(?,?,?,?,?,?)";
        newBook = conn.prepareStatement(addBookQuery);
        newBook.setInt(1, booksObject.getBookID());
        newBook.setString(2, booksObject.getTitle());
        newBook.setString(3, booksObject.getGenre());
        newBook.setInt(4, booksObject.getPublicationYear());
        newBook.setString(5, booksObject.getISBN());
        newBook.setInt(6, booksObject.getAvailableCopies());
        newBook.execute();

    } // end insertBook Method

//======================================================================================================================

    // Insert New Borrowings Record
    public void insertBorrow() throws SQLException {

        borrowingsObject = new Borrowings(in.nextInt(), in.nextLine(), in.nextLine(), in.nextLine(),
                in.nextLine(), in.nextBoolean());
        System.out.println("Inserting New Borrow Record...");
        String addBorrowQuery = "INSERT INTO Borrowings (memberID, fullName, email, phone, joinDate, activeStatus)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        newBorrowRecord = conn.prepareStatement(addBorrowQuery);
        newBorrowRecord.setInt(1, borrowingsObject.getMemberID());
        newBorrowRecord.setString(2, borrowingsObject.getFullName());
        newBorrowRecord.setString(3, borrowingsObject.getEmail());
        newBorrowRecord.setString(4, borrowingsObject.getPhone());
        newBorrowRecord.setString(5, borrowingsObject.getJoinDate());
        newBorrowRecord.setBoolean(6, borrowingsObject.getActiveStatus());
        newBorrowRecord.execute();

    } // end insertBorrow Method

//======================================================================================================================

    // Insert New Payment Record
    public void insertPayment() throws SQLException {

        paymentsObject = new Payments(in.nextInt(), in.nextInt(), in.nextInt(),
                in.nextLine(), in.nextLine(), in.nextDouble());
        String newPayment = "INSERT INTO Payment (paymentID, fineID, memberID, paymentDate, method, amountPaid) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        newPaymentRecord = conn.prepareStatement(newPayment);
        newPaymentRecord.setInt(1, paymentsObject.getPaymentID());
        newPaymentRecord.setInt(2, paymentsObject.getFineID());
        newPaymentRecord.setInt(3, paymentsObject.getMemberID());
        newPaymentRecord.setString(4, paymentsObject.getPaymentDate());
        newPaymentRecord.setString(5, paymentsObject.getMethod());
        newPaymentRecord.setDouble(6, paymentsObject.getAmountPaid());
        newPaymentRecord.execute();

    } // end insertPayment Method

//======================================================================================================================

    @Override
    public void update(){
        // Update: Return date of a borrowed book, payment status of a fine
        System.out.println("Updating...");
    }


    public String bookByTitleSearch() {
        // Search for books by title, author, and genre

        // Borrowing history for a member
        return "";
    } // end search() method

    public void closeConnection() throws SQLException {
        conn.close();
        System.out.println("Database Connection Closed...");
    }

}
