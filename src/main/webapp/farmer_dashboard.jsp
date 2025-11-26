<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.shreeanna.model.Product" %>
<%@ page import="com.shreeanna.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Farmer Dashboard - Shree Anna</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"FARMER".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<header>
    <div class="logo">Shree Anna</div>
    <nav>
        <span>Welcome, <%= user.getName() %> (Farmer)</span>
        <a href="marketplace">Marketplace</a>
        <a href="logout">Logout</a>
    </nav>
</header>

<section class="dashboard">
    <div class="form-container">
        <h2>Add New Millet / Pulse Lot</h2>
        <form method="post" action="addProduct" enctype="multipart/form-data">
            <label>Product Name</label>
            <input type="text" name="name" required>

            <label>Description</label>
            <textarea name="description" required></textarea>

            <label>Category</label>
            <select name="category" required>
                <option value="Millet Grain">Millet Grain</option>
                <option value="Millet Flour">Millet Flour</option>
                <option value="Ready-to-Eat">Ready-to-Eat</option>
                <option value="Pulse">Pulse</option>
            </select>

            <label>Price per Kg (INR)</label>
            <input type="number" step="0.01" min="0" name="pricePerKg" required>

            <label>Available Quantity (Kg)</label>
            <input type="number" min="1" name="quantityKg" required>

            <label>Certification / Quality Tag</label>
            <input type="text" name="certification" placeholder="e.g. Organic, FPO Grade A">
            
            <label>Product Image</label>
            <input type="file" name="productImage" accept="image/*" required>            

            <button type="submit" class="btn primary">Add Product</button>
        </form>
    </div>

    <div class="table-container">
        <h2>Your Listed Products</h2>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products == null || products.isEmpty()) {
        %>
            <p>No products listed yet.</p>
        <% } else { %>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Category</th>
                <th>Price/Kg</th>
                <th>Quantity (Kg)</th>
                <th>Certification</th>
            </tr>
            </thead>
            <tbody>
            <% for (Product p : products) { %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getName() %></td>
                <td><%= p.getCategory() %></td>
                <td><%= p.getPricePerKg() %></td>
                <td><%= p.getQuantityKg() %></td>
                <td><%= p.getCertification() %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } %>
    </div>
</section>

</body>
</html>
