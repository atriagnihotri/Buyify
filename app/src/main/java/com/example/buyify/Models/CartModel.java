package com.example.buyify.Models;

public class CartModel {
    String Product,Quantity,Price,Total_Amount;

    public CartModel(String product, String quantity, String price, String total_Amount) {
        Product = product;
        Quantity = quantity;
        Price = price;
        Total_Amount = total_Amount;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTotal_Amount() {
        return Total_Amount;
    }

    public void setTotal_Amount(String total_Amount) {
        Total_Amount = total_Amount;
    }
}
