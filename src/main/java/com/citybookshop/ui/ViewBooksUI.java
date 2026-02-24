package com.citybookshop.ui;

import com.citybookshop.data.Database;
import com.citybookshop.model.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewBooksUI {

    public static void show(Stage stage) {

       
        TextField nameField = new TextField();
        nameField.setPromptText("Search by Name");

        TextField categoryField = new TextField();
        categoryField.setPromptText("Search by Category");

        TextField priceField = new TextField();
        priceField.setPromptText("Search by Price");

        Button searchBtn = new Button("Search");
        Button resetBtn = new Button("Reset");

        HBox searchBox = new HBox(10, nameField, categoryField, priceField, searchBtn, resetBtn);
        searchBox.setAlignment(Pos.CENTER);

        
        TableView<Book> table = new TableView<>();
        table.setStyle("-fx-font-size: 14px; -fx-border-color: #ddd; -fx-border-width: 1px; -fx-border-radius: 10px; -fx-background-color: white;");

        TableColumn<Book, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> nameCol = new TableColumn<>("Title");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Book, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Book, Integer> stockCol = new TableColumn<>("Quantity");
        stockCol.setCellValueFactory(new PropertyValueFactory<>("qty"));

        table.getColumns().addAll(idCol, nameCol, categoryCol, priceCol, stockCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        
        ObservableList<Book> masterList =
                FXCollections.observableArrayList(Database.books);
        table.setItems(masterList);

        
        searchBtn.setOnAction(e -> {

            String nameText = nameField.getText().toLowerCase();
            String categoryText = categoryField.getText().toLowerCase();
            String priceText = priceField.getText().replaceAll("[^0-9.]", "");

            ObservableList<Book> filteredList =
                    FXCollections.observableArrayList();

            for (Book book : Database.books) {

                boolean matches = true;

                
                if (!nameText.isEmpty() &&
                        !book.getName().toLowerCase().contains(nameText)) {
                    matches = false;
                }

                
                if (!categoryText.isEmpty() &&
                        !book.getCategory().toLowerCase().contains(categoryText)) {
                    matches = false;
                }

               
                if (!priceText.isEmpty()) {
                    double searchPrice = Double.parseDouble(priceText);
                    if (book.getPrice() != searchPrice) {
                        matches = false;
                    }
                }

                if (matches) {
                    filteredList.add(book);
                }
            }

            table.setItems(filteredList);
        });

        
        resetBtn.setOnAction(e -> {
            table.setItems(masterList);
            nameField.clear();
            categoryField.clear();
            priceField.clear();
        });

        VBox root = new VBox(20, searchBox, table);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("All Books");
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add(
            ViewBooksUI.class.getResource("/styles/style.css").toExternalForm()
        );
    }
}