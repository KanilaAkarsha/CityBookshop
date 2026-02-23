package com.citybookshop.util;

import java.util.List;

import com.citybookshop.data.Database;
import com.citybookshop.model.Book;
import com.citybookshop.model.User;

public class DatabaseManager {

    
    public static void loadData() {
        Database.users = FileManager.loadUsers();
        Database.books = FileManager.loadBooks();
    }

    
    public static void saveData() {
        FileManager.saveUsers(Database.users);
        FileManager.saveBooks(Database.books);
    }

    
    public static boolean addUser(User user) {
        for (User u : Database.users) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
                return false; 
            }
        }
        Database.users.add(user);
        saveData();
        return true;
    }

    public static List<User> getUsers() {
        return Database.users;
    }

    
    public static List<Book> getBooks() {
        return Database.books;
    }

    public static boolean addBook(Book book) {
        Database.books.add(book);
        saveData();
        return true;
    }
}
