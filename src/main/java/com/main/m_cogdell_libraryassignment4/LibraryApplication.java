package com.main.m_cogdell_libraryassignment4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DataBaseComm tester = new DataBaseComm(); // Instantiating a DataBaseComm Object
        tester.insert();
        tester.update();
        String variable = tester.retrieve();
        System.out.println(variable.toString());
        tester.connect();
        launch();
    }
}