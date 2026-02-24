package com.citybookshop.ui;

import com.citybookshop.model.User;
import com.citybookshop.util.AuthManager;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardUI extends Application {

    private User currentUser;

    public DashboardUI(String user, String pass) {
        this.currentUser = AuthManager.getCurrentUserObject(user, pass);
    }


    @Override
    public void start(Stage stage) {

        StackPane content = new StackPane();

        Label welcomeLabel = new Label("Welcome, " + currentUser.getUsername());
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold; -fx-alignment: center; -fx-padding: 10px;-fx-max-width: 200px; -fx-letter-spacing: 3px;");

        Button addBook = new Button("➕ Add Book");
        Button viewBooks = new Button("📖 View Books");
        Button addMember = new Button("Add Member");
        Button profile = new Button("Profile");
        
        addMember.setOnAction(e -> {
            SignupUI.show(new Stage());
        });

        profile.setOnAction(e -> {
            new ProfileUI(currentUser).show(new Stage());
        });

        VBox sidebar = new VBox(20, welcomeLabel, addBook, viewBooks, addMember, profile);
        sidebar.setAlignment(Pos.CENTER);
        sidebar.setStyle("-fx-padding: 20; -fx-background-color: #1e3c72;");

        Label title = new Label("📚 CityBookshop");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox titleBox = new VBox(20, title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle("-fx-padding: 20; -fx-background-color: #1e3c72;");

        Label label = new Label("Welcome to CityBookshop Manager Dashboard");
        label.setStyle("-fx-font-size: 25px; -fx-text-fill: white; -fx-font-weight: bold; -fx-alignment: center; -fx-wrap-text: true;-fx-text-alignment: center; -fx-padding: 20px; -fx-letter-spacing: 3px;");

        VBox welcome = new VBox(20, label);
        welcome.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setTop(titleBox);
        root.setBottom(welcome);
        root.setCenter(content);

        addBook.setOnAction(e -> AddBookUI.show(content));
        viewBooks.setOnAction(e -> ViewBooksUI.show(new Stage()));

        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(
            DashboardUI.class.getResource("/styles/style.css").toExternalForm()
        );

        stage.setTitle("CityBookshop Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}