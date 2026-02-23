package com.citybookshop.ui;

import com.citybookshop.util.AuthManager;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginUI {

    public static void show(Stage stage) {

        Label title = new Label("📚 CityBookshop");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button loginBtn = new Button("Login");

        Button signupBtn = new Button("Sign Up");
        signupBtn.setOnAction(e -> {
            SignupUI.show(new Stage());
        });

       loginBtn.setOnAction(e -> {

    String user = username.getText();
    String pass = password.getText();

    if (user.isEmpty() || pass.isEmpty()) {
        showAlert("Please fill all fields!");
        return;
    }

    if (AuthManager.login(user, pass)) { 
        showAlert("Login Successful!");


        if (AuthManager.getCurrentUser(user, pass)) {
            try {
                new DashboardUI().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                new UserUI().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    } else {
        showAlert("Invalid username or password!");
    }
});

        VBox card = new VBox(15, username, password, loginBtn, signupBtn);
        card.getStyleClass().add("card");
        card.setMaxWidth(300);

        VBox root = new VBox(30, title, card);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 500, 400);

        var css = LoginUI.class.getResource("/styles/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        stage.setTitle("CityBookshop - Login");
        stage.setScene(scene);
        stage.show();
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}