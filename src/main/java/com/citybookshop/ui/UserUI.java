package com.citybookshop.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserUI extends Application {

    @Override
    public void start(Stage stage) {

        Button viewBooks = new Button("📖 View Books");

        VBox sidebar = new VBox(20, viewBooks);
        sidebar.setAlignment(Pos.CENTER);
        sidebar.setStyle("-fx-padding: 20; -fx-background-color: #1e3c72;");

        Label title = new Label("📚 CityBookshop");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        VBox titleBox = new VBox(20, title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle("-fx-padding: 20; -fx-background-color: #1e3c72;");

        Label label = new Label("Welcome to CityBookshop Cashier Dashboard");
        label.setStyle("-fx-font-size: 70px; -fx-text-fill: white; -fx-font-weight: bold; -fx-alignment: center; -fx-wrap-text: true;-fx-text-alignment: center; -fx-padding: 20px; -fx-width: 500px;");

        VBox welcome = new VBox(20, label);
        welcome.setAlignment(Pos.CENTER);


        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setTop(titleBox);
        root.setCenter(welcome);

        viewBooks.setOnAction(e -> ViewBooksUI.show(new Stage()));

        Scene scene = new Scene(root, 800, 500);

        scene.getStylesheets().add(
            UserUI.class.getResource("/styles/style.css").toExternalForm()
        );

        stage.setTitle("CityBookshop User Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}