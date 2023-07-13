package model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private double price;
    private int capacity;
    private int stock;
    private boolean status = true;
    private Category category;

    public Product() {
    }

    public Product(int id, String name, double price, int capacity, int stock, boolean status, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.stock = stock;
        this.status = status;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID : "+ id + " | Category: " + category.getName() + " | Name : "+ name + " | Capacity: " + capacity +"GB" +
                " | Price : " + price + " $ " +" | Stock: " + stock + (status ? "" : "Don't Sell");
    }
}
