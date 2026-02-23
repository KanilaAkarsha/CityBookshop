package com.citybookshop;

import com.citybookshop.ui.LoginUI;
import com.citybookshop.util.DatabaseManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load database from files
        DatabaseManager.loadData();

        // Show login
        LoginUI.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}