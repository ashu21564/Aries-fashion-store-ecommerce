package com.ariesfashionstore.util;

import java.sql.Connection;

public class TestDB {

    public static void main(String[] args) {

        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("✅ SUCCESS: Database Connected!");
        } else {
            System.out.println("❌ FAILED: Connection is null");
        }
    }
}