package com.shreeanna.servlet;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.shreeanna.model.CartItem;
import com.shreeanna.model.User;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User u = (session != null) ? (User) session.getAttribute("user") : null;
        if (u == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Map<Integer, CartItem> cart = (session != null) ? (Map<Integer, CartItem>) session.getAttribute("cart") : null;
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("marketplace");
            return;
        }

        String paymentReference = request.getParameter("paymentReference");
        if (paymentReference == null || paymentReference.trim().isEmpty()) {
            paymentReference = "UPI-DEMO-" + System.currentTimeMillis();
        }

        // Here you would normally save order & payment info to DB.

        cart.clear();  // empty cart after "payment"
        request.setAttribute("paymentReference", paymentReference);
        request.getRequestDispatcher("order_success.jsp").forward(request, response);
    }
}
