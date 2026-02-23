package com.citybookshop.ui;

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
    try {
        String idValue = id.getText();
        String nameValue = name.getText();
        String categoryValue = category.getText();

        
        String priceText = price.getText().replaceAll("[^0-9.]", "");
        double priceValue = Double.parseDouble(priceText);

        String qtyText = qty.getText().replaceAll("[^0-9]", "");
        int qtyValue = Integer.parseInt(qtyText);

        
        com.citybookshop.data.Database.books.add(
            new com.citybookshop.model.Book(idValue, nameValue, categoryValue, priceValue, qtyValue)
        );

        com.citybookshop.util.DatabaseManager.saveData(); 
        System.out.println("Book added successfully!");

    } catch (NumberFormatException ex) {
        System.out.println("Invalid number input!");
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