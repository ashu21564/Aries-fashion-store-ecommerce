package com.ariesfashionstore.dao.impl;

import com.ariesfashionstore.dao.CartDAO;
import com.ariesfashionstore.model.CartItem;
import com.ariesfashionstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {

    // 🔥 Get or Create Cart
    private int getOrCreateCartId(int userId, Connection conn) throws SQLException {

        String checkSql = "SELECT cart_id FROM cart WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(checkSql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("cart_id");
        }

        String insertSql = "INSERT INTO cart (user_id) VALUES (?)";
        ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.executeUpdate();

        rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }

    // ================= ADD =================

    @Override
    public void addToCart(int userId, int productId, String sizeLabel, int quantity) {

        try (Connection conn = DBConnection.getConnection()) {

            int cartId = getOrCreateCartId(userId, conn);

            // 🔍 Check if item already exists
            String checkSql = "SELECT * FROM cart_items WHERE cart_id=? AND product_id=? AND size_label=?";
            PreparedStatement ps = conn.prepareStatement(checkSql);
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.setString(3, sizeLabel);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // 🔁 UPDATE quantity
                int existingQty = rs.getInt("quantity");

                String updateSql = "UPDATE cart_items SET quantity=? WHERE cart_item_id=?";
                ps = conn.prepareStatement(updateSql);
                ps.setInt(1, existingQty + quantity);
                ps.setInt(2, rs.getInt("cart_item_id"));
                ps.executeUpdate();

                System.out.println("✅ Cart updated (quantity increased)");

            } else {

                // 🔥 FETCH REAL PRODUCT PRICE FROM DB
                double price = 0;

                String priceSql = "SELECT price FROM products WHERE product_id=?";
                PreparedStatement pricePs = conn.prepareStatement(priceSql);
                pricePs.setInt(1, productId);

                ResultSet priceRs = pricePs.executeQuery();
                if (priceRs.next()) {
                    price = priceRs.getDouble("price");
                }

                // 🛒 INSERT NEW ITEM
                String insertSql = "INSERT INTO cart_items (cart_id, product_id, size_label, quantity, unit_price) VALUES (?, ?, ?, ?, ?)";

                ps = conn.prepareStatement(insertSql);
                ps.setInt(1, cartId);
                ps.setInt(2, productId);
                ps.setString(3, sizeLabel);
                ps.setInt(4, quantity);
                ps.setDouble(5, price);  // ✅ FIXED PRICE

                ps.executeUpdate();

                System.out.println("✅ Added to cart with correct price: " + price);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= GET =================

    @Override
    public List<CartItem> getCartItemsByUserId(int userId) {

        List<CartItem> list = new ArrayList<>();

        String sql = "SELECT ci.*, p.product_name FROM cart_items ci " +
                     "JOIN cart c ON ci.cart_id = c.cart_id " +
                     "JOIN products p ON ci.product_id = p.product_id " +
                     "WHERE c.user_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CartItem item = new CartItem();

                item.setCartItemId(rs.getInt("cart_item_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setProductName(rs.getString("product_name"));
                item.setSizeLabel(rs.getString("size_label"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getDouble("unit_price"));

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================= UPDATE =================

    @Override
    public void updateCartItemQuantity(int cartItemId, int quantity) {

        String sql = "UPDATE cart_items SET quantity=? WHERE cart_item_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, cartItemId);

            ps.executeUpdate();

            System.out.println("✅ Cart item quantity updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= REMOVE =================

    @Override
    public void removeCartItem(int cartItemId) {

        String sql = "DELETE FROM cart_items WHERE cart_item_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartItemId);
            ps.executeUpdate();

            System.out.println("✅ Cart item removed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}