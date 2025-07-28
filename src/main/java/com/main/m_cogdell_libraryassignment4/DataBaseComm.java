package com.main.m_cogdell_libraryassignment4;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DataBaseComm {

    private static String url = "jdbc:postgresql://localhost:5432/LibrarySystem";
    private static String user = "postgres";
    private static String password = "CrazyFastRiviera1968";
    public Connection conn;
    public DatabaseMetaData meta;       // Metadata object to ensure proper connection to intended database
    private static Schema sch;          // Schema object for DDL Tables and Initial Insertions into DBase (See 'Schema' class)
    public PreparedStatement schemaStmt, initStmt, newMember, newBook,
            newBorrowRecord, newPaymentRecord, bookSearch, returnBook;
    public ResultSet tableExists, results, borrowRecordMessage, bookReturn, paymentResult;
    Members membersObject;
    Books booksObject, count;
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
        tableExists = meta.getTables(null, null, tableName, null);
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

        borrowingsObject = new Borrowings();

        System.out.println("Enter Member ID...");
        borrowingsObject.setMemberID(in.nextInt());
        in.nextLine();

        System.out.println("Enter Book ID...");
        borrowingsObject.setBookID(in.nextInt());
        in.nextLine();

        System.out.println("Attempting new borrow record insertion...");
        String addBorrowQuery = "SELECT borrowBook(?, ?)";
        newBorrowRecord = conn.prepareStatement(addBorrowQuery);
        newBorrowRecord.setInt(1, borrowingsObject.getMemberID());
        newBorrowRecord.setInt(2, borrowingsObject.getBookID());
        borrowRecordMessage = newBorrowRecord.executeQuery();

        // Display text returned by borrowBook() function
        if(borrowRecordMessage.next()) {
            String message = borrowRecordMessage.getString(1);
            System.out.println(message);
        }

    } // end insertBorrow Method

//======================================================================================================================

    // Return a Book ( write a stored procedure/function ) to implement

    public void ReturnBook() throws SQLException {
        borrowingsObject.getBookID();
        System.out.println("Enter Member ID...");
        int memberID = in.nextInt();
        in.nextLine();

        System.out.println("Enter Book ID...");
        int bookID = in.nextInt();
        in.nextLine();

        System.out.println("Enter Return Date (YYYY/MM/DD)...");
        String returnDate = in.next();

        System.out.println("Processing Return...");

        // Call returnBook function
        String returnBookFunction = "SELECT returnBook(?, ?, CAST(? AS DATE))";
        returnBook = conn.prepareStatement(returnBookFunction);
        returnBook.setInt(1, memberID);
        returnBook.setInt(2, bookID);
        returnBook.setString(3, returnDate);
        bookReturn = returnBook.executeQuery();

        // Display message
        if(bookReturn.next()) {
            String message = bookReturn.getString(1);
            System.out.println(message);
        }

        bookReturn.close();
        returnBook.close();

    } // end ReturnBook() Method

//======================================================================================================================

    // Insert New Payment Record
    public void insertPayment() throws SQLException {

        paymentsObject = new Payments();

        System.out.println("Enter Fine ID...");
        int fineID = in.nextInt();
        in.nextLine();

        System.out.println("Enter Payment Amount...");
        double amount = in.nextDouble();
        in.nextLine();

        System.out.println("Processing Payment...");

        // Call payFine Function
        String payFineFunction = "SELECT payFine(?, ?)";
        newPaymentRecord = conn.prepareStatement("payFineFunction");
        newPaymentRecord.setInt(1, fineID);
        newPaymentRecord.setDouble(2, amount);
        paymentResult = newPaymentRecord.executeQuery();

        if(paymentResult.next()) {
            String message = paymentResult.getString(1);
            System.out.println(message);
        }

        paymentResult.close();
        newPaymentRecord.close();

    }

//======================================================================================================================

    public void Search() throws SQLException {
        // Search for books by title, author, and genre

        System.out.println("Enter Book Search Type: (title, author, or genre)...");
        String searchType = in.nextLine();
        System.out.println();
        String title, author, genre;

        if(searchType.contains("title")) {
            System.out.println("Enter Title of Book...");
            title = in.nextLine();
            System.out.println();
            String titleQuery = "SELECT * FROM Books WHERE Title ILIKE ?;";
            bookSearch = conn.prepareStatement(titleQuery);
            bookSearch.setString(1, "%" + title + "%");
        } else if (searchType.contains("author")) {
            System.out.println("Enter Author's Name...");
            author = in.nextLine();
            System.out.println();
            String authorQuery = "SELECT * FROM Authors A JOIN Books B ON B.authorID = A.authorID " +
                    "WHERE A.fullName ILIKE ?;";
            bookSearch = conn.prepareStatement(authorQuery);
            bookSearch.setString(1, "%" + author + "%");
        } else {
            System.out.println("Enter Genre of Book...");
            genre = in.nextLine();
            System.out.println();
            String genreQuery = "SELECT * FROM Books WHERE Genre ILIKE ?;";
            bookSearch = conn.prepareStatement(genreQuery);
            bookSearch.setString(1, "%" + genre + "%");
        }

        results = bookSearch.executeQuery();
        Boolean found = false;
        while(results.next()) {
            found = true;
            System.out.println("Book ID: " + results.getInt("bookID"));
            System.out.println("Title: " + results.getString("title"));
            System.out.println("Author ID: " + results.getString("authorid"));
            System.out.println("Genre: " + results.getString("genre"));
            System.out.println("Publication Year: " + results.getString("publicationyear"));
            System.out.println("ISBN: " + results.getString("isbn"));
            System.out.println("Copies Available: " + results.getString("availablecopies"));
            System.out.println();
        }
        if(!found) {
            System.out.println("Results Not Found...");
        }
        results.close();

    } // end search() method

//======================================================================================================================

    public void closeConnection() throws SQLException {
        conn.close();
        System.out.println("Database Connection Closed...");
    }

}
