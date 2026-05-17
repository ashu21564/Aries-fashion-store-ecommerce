package com.ariesfashionstore.dao;

import com.ariesfashionstore.model.CartItem;
import java.math.BigDecimal;
import java.util.List;

public interface CartItemDAO {


    boolean addCartItem(CartItem item);

    boolean updateCartItem(CartItem item);

    boolean updateCartItemQuantity(int cartItemId, int quantity);

    boolean updateCartItemQuantityByVariant(int cartId, int variantId, int quantity);

    boolean removeCartItem(int cartItemId);

    boolean removeCartItemByVariant(int cartId, int variantId);

    boolean clearCart(int cartId);

    CartItem getCartItemById(int cartItemId);

    CartItem getCartItemByCartAndVariant(int cartId, int variantId);

    List<CartItem> getCartItemsByCartId(int cartId);

    int getCartItemCount(int cartId);

    BigDecimal getCartTotal(int cartId);
}