package com.citybookshop.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserUI extends Application {

    @Override
    public void start(Stage stage) {

        StackPane content = new StackPane();

        Button viewBooks = new Button("📖 View Books");

        VBox sidebar = new VBox(20, viewBooks);
        sidebar.setAlignment(Pos.CENTER);
        sidebar.setStyle("-fx-padding: 20; -fx-background-color: #1e3c72;");

        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(content);

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