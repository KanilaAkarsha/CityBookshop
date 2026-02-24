package com.citybookshop.ui;

import java.util.List;

import com.citybookshop.model.User;
import com.citybookshop.util.FileManager;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileUI {

    private User currentUser;

    public ProfileUI(User user) {
        this.currentUser = user;
    }

    public void show(Stage stage) {

        Label title = new Label("My Profile");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold; -fx-alignment: center; -fx-padding: 20px;");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField(currentUser.getUsername());

        Label passwordLabel = new Label("New Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter new password");

        Label roleLabel = new Label("Role:");
        Label roleValue = new Label(currentUser.getRole());
        roleValue.setStyle("-fx-font-size: 20px; -fx-text-fill: #1e3c72; -fx-font-weight: bold; -fx-text-alignment: center; ");

        Button updateButton = new Button("Update Details");
        Button logoutButton = new Button("Logout");


        updateButton.setOnAction(e -> {

            String newUsername = usernameField.getText().trim();
            String newPassword = passwordField.getText().trim();

            if (newUsername.isEmpty()) {
                showAlert("Username cannot be empty!");
                return;
            }

            List<User> users = FileManager.loadUsers();

            // Check if new username already exists (and not current user)
            boolean exists = users.stream()
                    .anyMatch(u -> u.getUsername().equals(newUsername)
                            && !u.getUsername().equals(currentUser.getUsername()));

            if (exists) {
                showAlert("Username already taken!");
                return;
            }

            // Update user details
            for (User u : users) {
                if (u.getUsername().equals(currentUser.getUsername())) {

                    u.setUsername(newUsername);

                    if (!newPassword.isEmpty()) {
                        u.setPassword(newPassword);
                    }

                    currentUser = u;
                    break;
                }
            }

            FileManager.saveUsers(users);

            showAlert("Profile updated successfully!");
            passwordField.clear();
        });

        logoutButton.setOnAction(e -> {
            new LoginUI().show(stage);
        });

        VBox layout = new VBox(15,
                title,
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                roleLabel,
                roleValue,
                updateButton,
                logoutButton
        );


        layout.setPrefSize(400, 500);
        layout.setStyle("-fx-padding: 20; -fx-background-color: #1e3c72; ");

        VBox card = new VBox(15, usernameLabel, usernameField, passwordLabel, passwordField, roleLabel, roleValue, updateButton, logoutButton);
        card.getStyleClass().add("card");
        card.setMaxWidth(300);

        Scene scene = new Scene(card, 300, 400);
        stage.setScene(scene);
        stage.setTitle("Profile - CityBookshop");
        stage.show();

        var css = ProfileUI.class.getResource("/styles/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}