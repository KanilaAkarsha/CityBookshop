package com.citybookshop;

import com.citybookshop.ui.LoginUI;
import com.citybookshop.util.DatabaseManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        DatabaseManager.loadData();

        
        LoginUI.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}