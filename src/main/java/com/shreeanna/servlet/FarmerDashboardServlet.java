package com.shreeanna.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.shreeanna.dao.ProductDAO;
import com.shreeanna.model.Product;
import com.shreeanna.model.User;


@WebServlet("/farmerDashboard")
public class FarmerDashboardServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User u = (session != null) ? (User) session.getAttribute("user") : null;
        if (u == null || !"FARMER".equalsIgnoreCase(u.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Product> products = productDAO.getProductsByFarmer(u.getId());
        request.setAttribute("products", products);
        request.getRequestDispatcher("farmer_dashboard.jsp").forward(request, response);
    }
}
