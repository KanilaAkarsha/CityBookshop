package com.citybookshop.util;

import java.util.List;

import com.citybookshop.data.Database;
import com.citybookshop.model.Book;
import com.citybookshop.model.User;

public class DatabaseManager {

    // Load data from files into Database
    public static void loadData() {
        Database.users = FileManager.loadUsers();
        Database.books = FileManager.loadBooks();
    }

    // Save data from Database into files
    public static void saveData() {
        FileManager.saveUsers(Database.users);
        FileManager.saveBooks(Database.books);
    }

    // User operations
    public static boolean addUser(User user) {
        for (User u : Database.users) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
                return false; // User already exists
            }
        }
        Database.users.add(user);
        saveData();
        return true;
    }

    public static List<User> getUsers() {
        return Database.users;
    }

    // Book operations
    public static List<Book> getBooks() {
        return Database.books;
    }

    public static boolean addBook(Book book) {
        Database.books.add(book);
        saveData();
        return true;
    }
}
