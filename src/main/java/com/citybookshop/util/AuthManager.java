package com.citybookshop.util;

import com.citybookshop.data.Database;
import com.citybookshop.model.User;

public class AuthManager {

   
    public static boolean signup(String username, String password, String role) {
        
        for (User u : Database.users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return false; 
            }
        }

        
        Database.users.add(new User(username, password, role));
        DatabaseManager.saveData(); 
        return true;
    }

    
    public static boolean login(String username, String password) {
        for (User u : Database.users) {
            if (u.getUsername().equalsIgnoreCase(username)
                    && u.getPassword().equals(password)) {
                return true; 
            }
        }
        return false; 
    }

    
    public static boolean getCurrentUser(String username, String password) {
        for (User u : Database.users) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password) && u.getRole().equalsIgnoreCase("admin")) {
                return true; 
            }
        }
        return false; 
    }

    public static User getCurrentUserObject(String username, String password) {
        for (User u : Database.users) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    
    }
}