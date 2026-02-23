package com.citybookshop.ui;

import com.citybookshop.data.Database;
import com.citybookshop.model.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewBooksUI {

    public static void show(Stage stage) {
        // TableView
        TableView<Book> table = new TableView<>();

        // Columns
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

        // Load data from Database
        ObservableList<Book> books = FXCollections.observableArrayList(Database.books);
        table.setItems(books);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox root = new VBox(20, table);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 700, 400);
        stage.setTitle("All Books");
        stage.setScene(scene);
        stage.show();
    }
}