package com.citybookshop.util;

import com.citybookshop.data.Database;
import com.citybookshop.model.User;

public class AuthManager {

    // Signup using DatabaseManager
    public static boolean signup(String username, String password, String role) {
        // Check if user already exists
        for (User u : Database.users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return false; // user already exists
            }
        }

        // Add new user and save to file
        Database.users.add(new User(username, password, role));
        DatabaseManager.saveData(); // persist changes
        return true;
    }

    // Login using Database
    public static boolean login(String username, String password) {
        for (User u : Database.users) {
            if (u.getUsername().equalsIgnoreCase(username)
                    && u.getPassword().equals(password)) {
                return true; // correct login
            }
        }
        return false; // wrong credentials
    }

    // Get current logged in user (for simplicity, we just return the first match)
    public static boolean getCurrentUser(String username, String password) {
        for (User u : Database.users) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password) && u.getRole().equalsIgnoreCase("admin")) {
                return true; // user exists
            }
        }
        return false; // wrong credentials
    }
}