package com.example.buyify.Models;

public class ProductModel {

    private String category;
    private String prName;
    private String image;
    private double price;
    private String description;
    private int stock;
    private int id;

    public ProductModel(int id,String category, String prName, String image, double price, String description, int stock) {

        this.category = category;
        this.prName = prName;
        this.image = image;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
