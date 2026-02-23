package com.citybookshop.util;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.citybookshop.model.Book;
import com.citybookshop.model.User;

public class FileManager {

    private static final String DATA_DIR = "data";

    // --- Books ---
    public static void saveBooks(List<Book> books) {
        new File(DATA_DIR).mkdirs();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "/books.dat"))) {
            out.writeObject(books);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Book> loadBooks() {
        File file = new File(DATA_DIR + "/books.dat");
        if (!file.exists() || file.length() == 0) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Book>) in.readObject();
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // --- Users ---
    public static void saveUsers(List<User> users) {
        new File(DATA_DIR).mkdirs();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "/users.dat"))) {
            out.writeObject(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<User> loadUsers() {
        File file = new File(DATA_DIR + "/users.dat");
        if (!file.exists() || file.length() == 0) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<User>) in.readObject();
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}