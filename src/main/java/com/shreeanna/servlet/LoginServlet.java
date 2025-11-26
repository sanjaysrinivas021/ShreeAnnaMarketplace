package com.shreeanna.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.shreeanna.dao.ProductDAO;
import com.shreeanna.dao.UserDAO;
import com.shreeanna.model.Product;
import com.shreeanna.model.User;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email    = request.getParameter("email");
        String password = request.getParameter("password");

        User u = userDAO.validateLogin(email, password);
        if (u != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", u);
            if ("FARMER".equalsIgnoreCase(u.getRole())) {
                response.sendRedirect("farmerDashboard");
            } else {
                response.sendRedirect("marketplace");
            }
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
