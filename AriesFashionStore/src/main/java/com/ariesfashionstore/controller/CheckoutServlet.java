package com.ariesfashionstore.controller;

import com.ariesfashionstore.dao.CartDAO;
import com.ariesfashionstore.dao.OrderDAO;
import com.ariesfashionstore.dao.impl.CartDAOImpl;
import com.ariesfashionstore.dao.impl.OrderDAOImpl;
import com.ariesfashionstore.model.CartItem;
import com.ariesfashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private CartDAO cartDAO;
    private OrderDAO orderDAO;

    @Override
    public void init() {
        cartDAO = new CartDAOImpl();
        orderDAO = new OrderDAOImpl();
    }

    // ================= SHOW CHECKOUT PAGE =================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = user.getUserId();

        List<CartItem> cartItems = cartDAO.getCartItemsByUserId(userId);

        request.setAttribute("cartItems", cartItems);

        request.getRequestDispatcher("/WEB-INF/views/checkout.jsp")
                .forward(request, response);
    }

    // ================= PLACE ORDER =================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int userId = user.getUserId();

        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");

        try {

            // 🔥 Get cart items
            List<CartItem> cartItems = cartDAO.getCartItemsByUserId(userId);

            // 🔥 Calculate total
            double totalAmount = 0;
            for (CartItem item : cartItems) {
                totalAmount += item.getQuantity() * item.getUnitPrice();
            }

            // 🔥 Create order
            int orderId = orderDAO.createOrder(userId, totalAmount, paymentMethod, address);

            // 🔥 Save order items
            orderDAO.addOrderItems(orderId, cartItems);

            // 🔥 Clear cart
            orderDAO.clearCart(userId);

            // 🔥 Redirect to confirmation
            response.sendRedirect("order-confirmation?id=" + orderId);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cart?action=view");
        }
    }
}