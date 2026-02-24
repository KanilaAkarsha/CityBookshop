package com.citybookshop.model;
import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String name;
    private String category;
    private double price;
    private int qty;

    public Book(String id, String name, String category, double price, int qty) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.qty = qty;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getQty() { return qty; }
}