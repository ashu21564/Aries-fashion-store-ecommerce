package com.ariesfashionstore.controller;

import com.ariesfashionstore.dao.OrderDAO;
import com.ariesfashionstore.dao.impl.OrderDAOImpl;
import com.ariesfashionstore.model.Order;
import com.ariesfashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-orders")
public class MyOrdersServlet extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedUser");

        // 🔒 Check login
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = user.getUserId();

        // 🔥 Fetch all orders
        List<Order> orders = orderDAO.getOrdersByUserId(userId);

        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/WEB-INF/views/my-orders.jsp")
                .forward(request, response);
    }
}