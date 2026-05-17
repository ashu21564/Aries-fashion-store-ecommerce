package com.ariesfashionstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/aries_fashion_store?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "Root@123";

    private DBConnection() {}

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 🔥 DEBUG (VERY IMPORTANT)
            System.out.println("✅ Connected to DB: aries_fashion_store"); 

            return conn;

        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL Driver not found!");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("❌ Database Connection Failed!");
            e.printStackTrace();
        }

        // 🔥 DON'T RETURN SILENTLY
        throw new RuntimeException("🔥 DB CONNECTION FAILED");
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("✅ Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}