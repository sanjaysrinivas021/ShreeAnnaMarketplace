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


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name     = request.getParameter("name");
        String email    = request.getParameter("email");
        String password = request.getParameter("password");
        String phone    = request.getParameter("phone");
        String role     = request.getParameter("role");
        String state    = request.getParameter("state");
        String district = request.getParameter("district");

        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password); // For demo only (no hashing)
        u.setPhone(phone);
        u.setRole(role);
        u.setState(state);
        u.setDistrict(district);

        boolean ok = userDAO.registerUser(u);
        if (ok) {
            request.setAttribute("msg", "Registration successful. Please login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Registration failed. Try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
