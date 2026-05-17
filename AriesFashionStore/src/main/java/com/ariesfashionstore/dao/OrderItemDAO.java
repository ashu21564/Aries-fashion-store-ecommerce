package com.ariesfashionstore.dao;

import com.ariesfashionstore.model.OrderItem;
import java.util.List;

public interface OrderItemDAO {

    boolean addOrderItem(OrderItem item);

    boolean addOrderItems(List<OrderItem> items);

    OrderItem getOrderItemById(int orderItemId);

    List<OrderItem> getOrderItemsByOrderId(int orderId);


    boolean deleteOrderItem(int orderItemId);

    boolean deleteItemsByOrderId(int orderId);
}