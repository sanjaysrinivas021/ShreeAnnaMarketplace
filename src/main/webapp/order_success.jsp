<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Successful - Shree Anna</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<header>
    <div class="logo">Shree Anna</div>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="marketplace">Marketplace</a>
        <a href="cart.jsp">Cart</a>
    </nav>
</header>

<%
    String paymentReference = (String) request.getAttribute("paymentReference");
    if (paymentReference == null) {
        paymentReference = request.getParameter("paymentReference");
    }
%>

<div class="form-container">
    <h2>Thank You!</h2>
    <p>Your order has been placed successfully.</p>

    <% if (paymentReference != null) { %>
        <p><strong>Payment Reference (Demo):</strong> <%= paymentReference %></p>
    <% } %>

    <p>
        This is a demo UPI payment flow for project & resume purpose.  
        No real transaction is processed with UPI ID <strong>shreeanna@demo</strong>.
    </p>

    <a href="marketplace" class="btn primary">Continue Shopping</a>
</div>

</body>
</html>
