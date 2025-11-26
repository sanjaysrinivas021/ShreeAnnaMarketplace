package com.shreeanna.servlet;

import java.io.File;
import java.io.IOException;

import com.shreeanna.dao.ProductDAO;
import com.shreeanna.model.Product;
import com.shreeanna.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;



@WebServlet("/addProduct")
@MultipartConfig
public class AddProductServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User u = (session != null) ? (User) session.getAttribute("user") : null;
        if (u == null || !"FARMER".equalsIgnoreCase(u.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String name         = request.getParameter("name");
        String description  = request.getParameter("description");
        String category     = request.getParameter("category");
        String pricePerKg   = request.getParameter("pricePerKg");
        String quantityKg   = request.getParameter("quantityKg");
        String certification= request.getParameter("certification");
        
     // Handle image upload
        Part imagePart = request.getPart("productImage");
        String imageFileName = null;
        if (imagePart != null && imagePart.getSize() > 0) {
            String submittedFileName = imagePart.getSubmittedFileName();
            String ext = "";
            int dot = submittedFileName.lastIndexOf('.');
            if (dot >= 0) {
                ext = submittedFileName.substring(dot);
            }
            imageFileName = System.currentTimeMillis() + ext;

            String uploadDirPath = request.getServletContext().getRealPath("") 
                                   + File.separator + "uploads";
            File uploadDir = new File(uploadDirPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            imagePart.write(uploadDirPath + File.separator + imageFileName);
        }

        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setCategory(category);
        p.setPricePerKg(Double.parseDouble(pricePerKg));
        p.setQuantityKg(Integer.parseInt(quantityKg));
        p.setFarmerId(u.getId());
        p.setState(u.getState());
        p.setCertification(certification);
        p.setImage(imageFileName);     // NEW


        boolean ok = productDAO.addProduct(p);
        if (ok) {
            response.sendRedirect("farmerDashboard");
        } else {
            request.setAttribute("error", "Unable to add product.");
            request.getRequestDispatcher("farmer_dashboard.jsp").forward(request, response);
        }
    }
}
