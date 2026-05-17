package com.ariesfashionstore.controller;

import com.ariesfashionstore.dao.OrderDAO;
import com.ariesfashionstore.dao.impl.OrderDAOImpl;
import com.ariesfashionstore.model.Order;
import com.ariesfashionstore.model.OrderItem;
import com.ariesfashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/order-confirmation")
public class OrderServlet extends HttpServlet {

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

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        String orderIdStr = request.getParameter("id");

        if (orderIdStr == null || orderIdStr.isEmpty()) {
            response.sendRedirect("home");
            return;
        }

        int orderId = Integer.parseInt(orderIdStr);

        // 🔥 Fetch order + items
        Order order = orderDAO.getOrderById(orderId);
        List<OrderItem> orderItems = orderDAO.getOrderItemsByOrderId(orderId);

        request.setAttribute("order", order);
        request.setAttribute("orderItems", orderItems);

        request.getRequestDispatcher("/WEB-INF/views/order-confirmation.jsp")
                .forward(request, response);
    }
}