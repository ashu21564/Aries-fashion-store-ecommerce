package com.ariesfashionstore.model;

public class CartItem {

    private int cartItemId;
    private int cartId;          // 🔥 ADD THIS
    private int productId;
    private String productName;
    private String sizeLabel;
    private int quantity;
    private double unitPrice;

    // ================= GETTERS =================

    public int getCartItemId() {
        return cartItemId;
    }

    public int getCartId() {        // 🔥 FIX
        return cartId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getSizeLabel() {
        return sizeLabel;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    // ================= SETTERS =================

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setCartId(int cartId) {   // 🔥 FIX
        this.cartId = cartId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSizeLabel(String sizeLabel) {
        this.sizeLabel = sizeLabel;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}