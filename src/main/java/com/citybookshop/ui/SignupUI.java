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

public class SignupUI {

    
    public static void show(Stage stage) {

        Label title = new Label("📚 CityBookshop");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        TextField username = new TextField();
        username.setPromptText("Username");

        TextField role = new TextField();
        role.setPromptText("Role (e.g. admin, user)");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button signupBtn = new Button("Sign Up");

        signupBtn.setOnAction(e -> {

    String user = username.getText();
    String pass = password.getText();
    String roleValue = role.getText();

    if (user.isEmpty() || pass.isEmpty() || roleValue.isEmpty()) {
        showAlert("Please fill all fields!");
        return;
    }

    if (AuthManager.signup(user, pass, roleValue)) { 
        showAlert("Signup successful! ");
        try {
            new DashboardUI().start(new Stage());
            stage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } else {
        showAlert("Username already exists!");
    }
});

        VBox card = new VBox(15, username, password, role, signupBtn);
        card.getStyleClass().add("card");
        card.setMaxWidth(300);

        VBox root = new VBox(30, title, card);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 500, 400);

        var css = LoginUI.class.getResource("/styles/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        stage.setTitle("CityBookshop - Signup");
        stage.setScene(scene);
        stage.show();
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
