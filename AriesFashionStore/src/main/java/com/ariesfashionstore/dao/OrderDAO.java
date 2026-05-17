package com.ariesfashionstore.dao;

import com.ariesfashionstore.model.CartItem;
import com.ariesfashionstore.model.Order;
import com.ariesfashionstore.model.OrderItem;

import java.util.List;

public interface OrderDAO {

    int createOrder(int userId, double totalAmount, String paymentMethod, String address);

    void addOrderItems(int orderId, List<CartItem> cartItems);

    void clearCart(int userId);

    Order getOrderById(int orderId);

    List<OrderItem> getOrderItemsByOrderId(int orderId);

    List<Order> getOrdersByUserId(int userId);
}