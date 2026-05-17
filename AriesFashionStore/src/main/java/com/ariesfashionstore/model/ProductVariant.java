package com.ariesfashionstore.model;

public class ProductVariant {

    private int variantId;
    private int productId;
    private String sizeLabel;
    private int stockQuantity;
    private boolean isAvailable;

    // Default Constructor
    public ProductVariant() {
    }

    // Parameterized Constructor
    public ProductVariant(int variantId, int productId, String sizeLabel, int stockQuantity, boolean isAvailable) {
        this.variantId = variantId;
        this.productId = productId;
        this.sizeLabel = sizeLabel;
        this.stockQuantity = stockQuantity;
        this.isAvailable = isAvailable;
    }

    public ProductVariant(int productId, String sizeLabel, int stockQuantity, boolean isAvailable) {
        this.productId = productId;
        this.sizeLabel = sizeLabel;
        this.stockQuantity = stockQuantity;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSizeLabel() {
        return sizeLabel;
    }

    public void setSizeLabel(String sizeLabel) {
        this.sizeLabel = sizeLabel;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}