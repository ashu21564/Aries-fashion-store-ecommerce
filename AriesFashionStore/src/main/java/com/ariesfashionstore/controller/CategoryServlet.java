package com.ariesfashionstore.controller;

import com.ariesfashionstore.dao.ProductDAO;
import com.ariesfashionstore.dao.impl.ProductDAOImpl;
import com.ariesfashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryIdStr = request.getParameter("id");

        List<Product> products;

        if (categoryIdStr != null) {
            int categoryId = Integer.parseInt(categoryIdStr);
            products = productDAO.getProductsByCategory(categoryId);
        } else {
            products = productDAO.getAllActiveProducts();
        }

        request.setAttribute("products", products);

        request.getRequestDispatcher("/WEB-INF/views/category.jsp")
                .forward(request, response);
    }
}