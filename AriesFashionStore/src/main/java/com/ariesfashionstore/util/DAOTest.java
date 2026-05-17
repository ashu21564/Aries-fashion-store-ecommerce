package com.ariesfashionstore.util;

import java.sql.*;

public class DAOTest {

    static final String URL = "jdbc:mysql://localhost:3306/aries_fashion_store";
    static final String USER = "root";
    static final String PASSWORD = "Root@123"; // change if needed

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            // ================= ALL PRODUCTS =================
            System.out.println("=== ALL PRODUCTS ===");

            String allProductsQuery = "SELECT * FROM products";
            PreparedStatement ps1 = con.prepareStatement(allProductsQuery);
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                System.out.println(
                        rs1.getInt("product_id") + " | " +
                        rs1.getString("product_name") + " | " +
                        rs1.getDouble("discount_percentage")
                );
            }

            // ================= SEARCH =================
            System.out.println("\n=== SEARCH: Black ===");

            String searchQuery = "SELECT * FROM products WHERE product_name LIKE ?";
            PreparedStatement ps2 = con.prepareStatement(searchQuery);
            ps2.setString(1, "%Black%");
            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                System.out.println(rs2.getString("product_name"));
            }

            // ================= CATEGORY FILTER =================
            System.out.println("\n=== PRODUCTS IN CATEGORY (MEN = 1) ===");

            String categoryQuery = "SELECT * FROM products WHERE category_id = ?";
            PreparedStatement ps3 = con.prepareStatement(categoryQuery);
            ps3.setInt(1, 1);
            ResultSet rs3 = ps3.executeQuery();

            while (rs3.next()) {
                System.out.println(rs3.getString("product_name"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}