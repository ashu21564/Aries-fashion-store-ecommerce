package com.ariesfashionstore.dao.impl;

import com.ariesfashionstore.dao.OrderDAO;
import com.ariesfashionstore.model.CartItem;
import com.ariesfashionstore.model.Order;
import com.ariesfashionstore.model.OrderItem;
import com.ariesfashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    // ================= CREATE ORDER =================
    @Override
    public int createOrder(int userId, double totalAmount, String paymentMethod, String address) {

        int orderId = 0;

        String sql = "INSERT INTO orders (user_id, total_amount, payment_method, delivery_address, order_status) VALUES (?, ?, ?, ?, 'PLACED')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, userId);
            ps.setDouble(2, totalAmount);
            ps.setString(3, paymentMethod);
            ps.setString(4, address);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }

    // ================= ADD ORDER ITEMS (🔥 FIXED) =================
    @Override
    public void addOrderItems(int orderId, List<CartItem> cartItems) {

        String sql = "INSERT INTO order_items (order_id, product_id, product_name, size_label, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (CartItem item : cartItems) {

                ps.setInt(1, orderId);
                ps.setInt(2, item.getProductId());

                // 🔥 FIX: SAVE PRODUCT NAME
                ps.setString(3, item.getProductName());

                ps.setString(4, item.getSizeLabel());
                ps.setInt(5, item.getQuantity());
                ps.setDouble(6, item.getUnitPrice());
                ps.setDouble(7, item.getQuantity() * item.getUnitPrice());

                ps.addBatch();
            }

            ps.executeBatch();

            System.out.println("✅ Order items inserted with product name");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= CLEAR CART =================
    @Override
    public void clearCart(int userId) {

        String sql = "DELETE ci FROM cart_items ci " +
                     "JOIN cart c ON ci.cart_id = c.cart_id " +
                     "WHERE c.user_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= GET ORDER =================
    @Override
    public Order getOrderById(int orderId) {

        Order order = null;

        String sql = "SELECT * FROM orders WHERE order_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setDeliveryAddress(rs.getString("delivery_address"));
                order.setOrderDate(rs.getTimestamp("order_date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    // ================= GET ORDER ITEMS =================
    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {

        List<OrderItem> list = new ArrayList<>();

        String sql = "SELECT * FROM order_items WHERE order_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                OrderItem item = new OrderItem();

                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setProductId(rs.getInt("product_id"));

                // 🔥 NOW WILL WORK (DATA EXISTS)
                item.setProductName(rs.getString("product_name"));

                item.setSizeLabel(rs.getString("size_label"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getDouble("unit_price"));
                item.setSubtotal(rs.getDouble("subtotal"));

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= USER ORDERS =================
    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM orders WHERE user_id=? ORDER BY order_id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderDate(rs.getTimestamp("order_date"));

                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}