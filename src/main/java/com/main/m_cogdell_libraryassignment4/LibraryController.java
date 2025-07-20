package com.main.m_cogdell_libraryassignment4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LibraryController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}