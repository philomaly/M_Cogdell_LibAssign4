package com.main.m_cogdell_libraryassignment4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/*

 * Method List for DataBaseComm Class.

 Automatically Loaded...
 * 1. connect() = connects to database
 * 2. schemaCheck() = helper method for loadSchema() method: checks if tables are already loaded
 * 3. loadSchema() = loads Schema Script from SQL_Script_Table_Insert_Scripts.sql file
 * 4. loadInitialInserts() = loads initial database inserts from script file

 Test these methods...
 * 5. insertMember() = inserts new member
 * 6. insertBook() = inserts a new book
 * 7. insertBorrow() = inserts a new borrow record
 * 8. returnBook() = returns book
 * 9. insertPayment() = for fine payment processing
 * 10. search() = book search by title, author, genre, or history (as in user's borrow history)

 Automatically Loaded...
 * 11. closeConnection() = closes the database connection
 *
 * */

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, SQLException {

        DataBaseComm tester = new DataBaseComm(); // Instantiating a DataBaseComm Object
        tester.connect();
        tester.loadSchema();

        // use this  the next line to test DataBaseComm Class Methods
        tester.search();

        System.out.println();
        tester.closeConnection();


        launch();
    }
}