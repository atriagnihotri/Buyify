package com.example.buyify.Models;

public class CategoryModel {
    String category;
    int img;

    public CategoryModel(String category, int img) {
        this.category = category;
        this.img = img;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
