package com.citybookshop.ui;

import com.citybookshop.util.DatabaseManager;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AddBookUI {

    public static void show(StackPane content) {

        TextField id = new TextField();
        id.setPromptText("Book ID");

        TextField name = new TextField();
        name.setPromptText("Book Name");

        TextField category = new TextField();
        category.setPromptText("Category");

        TextField price = new TextField();
        price.setPromptText("Price");

        TextField qty = new TextField();
        qty.setPromptText("Quantity");

        Button save = new Button("Save");

        save.setOnAction(e -> {
            String bookId = id.getText();
            String bookName = name.getText();
            String bookCategory = category.getText();
            double bookPrice = Double.parseDouble(price.getText());
            int bookQty = Integer.parseInt(qty.getText());

            if (DatabaseManager.addBook(new com.citybookshop.model.Book(bookId, bookName, bookCategory, bookPrice, bookQty))) {
                showAlert("Book added successfully!");
            } else {
                showAlert("Failed to add book!");
            }
        });

        VBox card = new VBox(15, id, name, category, price, qty, save);
        card.getStyleClass().add("card");

        content.getChildren().setAll(card);
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}