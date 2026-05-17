package com.ariesfashionstore.model;

public class Product {

    private int productId;
    private int categoryId;
    private String productName;
    private String description;
    private String color;

    // 🔥 NEW FIELD ADDED
    private double price;

    private double discountPercentage;
    private String imageUrl;
    private boolean isActive;

    // Default Constructor
    public Product() {
    }

    // Parameterized Constructor (FULL)
    public Product(int productId, int categoryId, String productName, String description,
                   String color, double price, double discountPercentage,
                   String imageUrl, boolean isActive) {

        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.description = description;
        this.color = color;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
    }

    // Parameterized Constructor (WITHOUT ID)
    public Product(int categoryId, String productName, String description,
                   String color, double price, double discountPercentage,
                   String imageUrl, boolean isActive) {

        this.categoryId = categoryId;
        this.productName = productName;
        this.description = description;
        this.color = color;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
    }

    // ================= GETTERS & SETTERS =================

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // 🔥 NEW GETTER
    public double getPrice() {
        return price;
    }

    // 🔥 NEW SETTER
    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}