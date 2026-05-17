package com.ariesfashionstore.controller;

import com.ariesfashionstore.dao.ProductDAO;
import com.ariesfashionstore.dao.CategoryDAO;
import com.ariesfashionstore.dao.impl.ProductDAOImpl;
import com.ariesfashionstore.dao.impl.CategoryDAOImpl;
import com.ariesfashionstore.model.Product;
import com.ariesfashionstore.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() {
        productDAO = new ProductDAOImpl();
        categoryDAO = new CategoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products = new ArrayList<>();
        List<Category> categories = new ArrayList<>();

        try {

            // 🔥 DEBUG DB CONNECTION
            try (Connection con = com.ariesfashionstore.util.DBConnection.getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM products")) {

                if (rs.next()) {
                    System.out.println("🔥 TOTAL PRODUCTS IN DB: " + rs.getInt(1));
                }

            }

            // 🔥 FETCH PRODUCTS
            products = productDAO.getAllActiveProducts();

            // 🔥 FALLBACK
            if (products == null || products.isEmpty()) {
                System.out.println("⚠ No active products → loading ALL products");
                products = productDAO.getAllProducts();
            }

            categories = categoryDAO.getAllCategories();

        } catch (Exception e) {
            e.printStackTrace();
            products = new ArrayList<>();
            categories = new ArrayList<>();
        }

        // 🔥 FINAL DEBUG
        System.out.println("🔥 Products fetched (Home): " + products.size());
        System.out.println("🔥 Categories fetched: " + categories.size());

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/views/home.jsp")
                .forward(request, response);
    }
}