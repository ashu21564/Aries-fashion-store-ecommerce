package com.ariesfashionstore.dao;

import com.ariesfashionstore.model.CartItem;
import java.util.List;

public interface CartDAO {

    void addToCart(int userId, int productId, String sizeLabel, int quantity);

    List<CartItem> getCartItemsByUserId(int userId);

    void updateCartItemQuantity(int cartItemId, int quantity);

    void removeCartItem(int cartItemId);
}