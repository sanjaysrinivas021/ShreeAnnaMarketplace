<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.shreeanna.model.CartItem" %>
<%@ page import="com.shreeanna.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Cart - Shree Anna</title>
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
        <% } else { %>
            <span>Hi, <%= user.getName() %></span>
            <a href="logout">Logout</a>
        <% } %>
    </nav>
</header>

<section class="cart-section">
    <h2>Your Cart</h2>
    <%
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
    %>
    <p>Your cart is empty.</p>
    <% } else { 
           double grandTotal = 0;
    %>
    <table>
        <thead>
        <tr>
            <th>Product</th>
            <th>Price/Kg</th>
            <th>Quantity (Kg)</th>
            <th>Total</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (CartItem item : cart.values()) {
                grandTotal += item.getTotalPrice();
        %>
        <tr>
            <td><%= item.getProduct().getName() %></td>
            <td>₹<%= item.getProduct().getPricePerKg() %></td>
            <td><%= item.getQuantityKg() %></td>
            <td>₹<%= item.getTotalPrice() %></td>
            <td>
                <form method="post" action="cart">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="productId" value="<%= item.getProduct().getId() %>">
                    <button type="submit" class="btn danger">Remove</button>
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <div class="cart-summary">
        <p><strong>Grand Total:</strong> ₹<%= grandTotal %></p>

     <form method="post" action="cart" style="display:inline;">
    <input type="hidden" name="action" value="clear">
    <button type="submit" class="btn secondary">Clear Cart</button>
</form>

<div class="upi-info">
    <p><strong>Pay via UPI (Demo)</strong></p>
    <p>UPI ID: <code>shreeanna@demo</code></p>
    <div class="upi-qr-wrapper" style="text-align:center;margin-top:15px;">
    <img 
        src="https://api.qrserver.com/v1/create-qr-code/?size=230x230&data=upi://pay?pa=shreeanna@demo&pn=Shree%20Anna%20Demo&am=<%= grandTotal %>&cu=INR"
        alt="UPI QR Code"
        width="230"
        height="230"
        style="border: 2px solid #ccc; border-radius: 8px; padding: 6px;"
    />
</div>

    <p class="upi-note">
        Scan this QR using any UPI app (Google Pay / PhonePe / Paytm, etc.) and complete a
        <strong>demo payment</strong>.<br/>
        After paying (or for demo), click the button below to confirm.
    </p>
</div>

<form method="post" action="checkout" id="upiCheckoutForm" style="display:inline;">
    <input type="hidden" name="paymentReference" id="paymentReference">
    <button type="button" class="btn primary" id="upiPayBtn">
        I have completed UPI payment (Demo)
    </button>
</form>

    </div>
    <% } %>
    <script>
    (function() {
    var btn = document.getElementById("upiPayBtn");
    if (!btn) return;

    btn.onclick = function() {
        var ref = "UPI-DEMO-" + Date.now();   // fake payment reference
        document.getElementById("paymentReference").value = ref;
        document.getElementById("upiCheckoutForm").submit();
    };
})();
</script>
</section>

</body>
</html>
