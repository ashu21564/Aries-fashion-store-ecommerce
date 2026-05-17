package com.ariesfashionstore.dao.impl;

import com.ariesfashionstore.dao.OrderItemDAO;
import com.ariesfashionstore.model.OrderItem;
import com.ariesfashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    // ================= SQL CONSTANTS =================

    private static final String ADD_ORDER_ITEM_SQL =
            "INSERT INTO order_items (order_id, product_id, product_name, quantity, unit_price, subtotal, size_label) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ITEM_BY_ID_SQL =
            "SELECT * FROM order_items WHERE order_item_id=?";

    private static final String GET_ITEMS_BY_ORDER_SQL =
            "SELECT * FROM order_items WHERE order_id=?";

    private static final String DELETE_ITEM_SQL =
            "DELETE FROM order_items WHERE order_item_id=?";

    private static final String DELETE_BY_ORDER_SQL =
            "DELETE FROM order_items WHERE order_id=?";

    // ================= ADD =================

    @Override
    public boolean addOrderItem(OrderItem item) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_ORDER_ITEM_SQL)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getProductId());
            ps.setString(3, item.getProductName());
            ps.setInt(4, item.getQuantity());
            ps.setDouble(5, item.getUnitPrice());
            ps.setDouble(6, item.getSubtotal());
            ps.setString(7, item.getSizeLabel());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= ADD MULTIPLE =================

    @Override
    public boolean addOrderItems(List<OrderItem> items) {

        boolean allInserted = true;

        for (OrderItem item : items) {
            if (!addOrderItem(item)) {
                allInserted = false;
            }
        }

        return allInserted;
    }

    // ================= GET BY ID =================

    @Override
    public OrderItem getOrderItemById(int orderItemId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ITEM_BY_ID_SQL)) {

            ps.setInt(1, orderItemId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToOrderItem(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GET BY ORDER =================

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {

        List<OrderItem> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ITEMS_BY_ORDER_SQL)) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToOrderItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= DELETE =================

    @Override
    public boolean deleteOrderItem(int orderItemId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ITEM_SQL)) {

            ps.setInt(1, orderItemId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteItemsByOrderId(int orderId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_BY_ORDER_SQL)) {

            ps.setInt(1, orderId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= HELPER =================

    private OrderItem mapResultSetToOrderItem(ResultSet rs) throws SQLException {

        OrderItem item = new OrderItem();

        item.setOrderItemId(rs.getInt("order_item_id"));
        item.setOrderId(rs.getInt("order_id"));
        item.setProductId(rs.getInt("product_id"));
        item.setProductName(rs.getString("product_name"));
        item.setQuantity(rs.getInt("quantity"));
        item.setUnitPrice(rs.getDouble("unit_price"));
        item.setSubtotal(rs.getDouble("subtotal"));
        item.setSizeLabel(rs.getString("size_label"));

        return item;
    }
}