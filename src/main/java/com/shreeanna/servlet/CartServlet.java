package com.shreeanna.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.shreeanna.dao.ProductDAO;
import com.shreeanna.model.CartItem;
import com.shreeanna.model.Product;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();

    @SuppressWarnings("unchecked")
    private Map<Integer, CartItem> getCart(HttpSession session) {
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = getCart(session);

        if ("add".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int qty       = Integer.parseInt(request.getParameter("quantityKg"));

            Product p = productDAO.getProductById(productId);
            if (p != null) {
                CartItem item = cart.get(productId);
                if (item == null) {
                    item = new CartItem(p, qty);
                } else {
                    item.setQuantityKg(item.getQuantityKg() + qty);
                }
                cart.put(productId, item);
            }
            response.sendRedirect("cart.jsp");
        } else if ("clear".equals(action)) {
            cart.clear();
            response.sendRedirect("cart.jsp");
        } else if ("remove".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            cart.remove(productId);
            response.sendRedirect("cart.jsp");
        }
    }
}
