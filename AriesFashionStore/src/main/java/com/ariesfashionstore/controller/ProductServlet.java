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
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

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

        String keyword = request.getParameter("keyword");
        String categoryIdStr = request.getParameter("categoryId");
        String sort = request.getParameter("sort");

        List<Product> products;

        try {

            // 🔥 DEBUG DB CONNECTION (VERY IMPORTANT)
            try (Connection con = com.ariesfashionstore.util.DBConnection.getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM products")) {

                if (rs.next()) {
                    System.out.println("🔥 TOTAL PRODUCTS IN DB: " + rs.getInt(1));
                }

            }

            int categoryId = 0;

            if (categoryIdStr != null && !categoryIdStr.trim().isEmpty()) {
                categoryId = Integer.parseInt(categoryIdStr);
            }

            // ✅ CLEAN LOGIC
            if (keyword != null && !keyword.trim().isEmpty()) {
                products = productDAO.searchProducts(keyword);

            } else if (categoryId > 0) {
                products = productDAO.getProductsByCategory(categoryId);

            } else if ("asc".equalsIgnoreCase(sort)) {
                products = productDAO.getProductsSortedByPriceAsc();

            } else if ("desc".equalsIgnoreCase(sort)) {
                products = productDAO.getProductsSortedByPriceDesc();

            } else {
                products = productDAO.getAllActiveProducts();
            }

        } catch (Exception e) {
            e.printStackTrace();
            products = productDAO.getAllActiveProducts();
        }

        // 🔥 FINAL DEBUG
        System.out.println("🔥 Products fetched (Servlet): " + products.size());

        request.setAttribute("products", products);

        List<Category> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/views/products.jsp")
                .forward(request, response);
    }
}