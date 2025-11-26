<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.shreeanna.model.Product" %>
<%@ page import="com.shreeanna.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Marketplace - Shree Anna</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
%>
<header>
    <div class="logo">Shree Anna</div>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="marketplace">Marketplace</a>
        <% if (user == null) { %>
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
        <% } else { %>
            <span>Hi, <%= user.getName() %> (<%= user.getRole() %>)</span>
            <% if ("FARMER".equalsIgnoreCase(user.getRole())) { %>
                <a href="farmerDashboard">Farmer Dashboard</a>
            <% } %>
            <a href="cart.jsp">Cart</a>
            <a href="logout">Logout</a>
        <% } %>
    </nav>
</header>

<section class="marketplace">
    <h2>Available Millet & Pulse Lots</h2>

    <div class="card-grid">
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products == null || products.isEmpty()) {
        %>
        <p>No products available right now.</p>
        <% } else {
               for (Product p : products) {
        %>
        <div class="card product-card">
    <% if (p.getImage() != null) { %>
        <img src="uploads/<%= p.getImage() %>" 
             alt="<%= p.getName() %>" 
             class="product-image">
    <% } %>
    <h3><%= p.getName() %></h3>

            <p class="tag"><%= p.getCategory() %></p>
            <p><%= p.getDescription() %></p>
            <p><strong>Price:</strong> ₹<%= p.getPricePerKg() %>/Kg</p>
            <p><strong>Available:</strong> <%= p.getQuantityKg() %> Kg</p>
            <p><strong>State:</strong> <%= p.getState() %></p>
            <p><strong>Certification:</strong> <%= p.getCertification() %></p>

            <form method="post" action="cart">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="productId" value="<%= p.getId() %>">
                <label>Quantity (Kg)</label>
                <input type="number" name="quantityKg" min="1" max="<%= p.getQuantityKg() %>" required>
                <button type="submit" class="btn primary">Add to Cart</button>
            </form>
        </div>
        <%   }
           }
        %>
    </div>
</section>

</body>
</html>
