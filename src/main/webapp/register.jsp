<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Shree Anna</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<header>
    <div class="logo">Shree Anna</div>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="marketplace">Marketplace</a>
        <a href="login.jsp">Login</a>
    </nav>
</header>

<div class="form-container">
    <h2>Create Account</h2>

    <%
        String error = (String) request.getAttribute("error");
        String msg   = (String) request.getAttribute("msg");
        if (error != null) {
    %>
    <div class="alert error"><%= error %></div>
    <% } else if (msg != null) { %>
    <div class="alert success"><%= msg %></div>
    <% } %>

    <form method="post" action="register">
        <label>Name</label>
        <input type="text" name="name" required>

        <label>Email</label>
        <input type="email" name="email" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <label>Phone</label>
        <input type="text" name="phone" required>

        <label>Role</label>
        <select name="role" required>
            <option value="FARMER">Farmer / FPO / SHG</option>
            <option value="BUYER">Buyer / Consumer</option>
            <option value="PROCESSOR">Processor / Startup</option>
        </select>

        <label>State</label>
        <input type="text" name="state" required>

        <label>District</label>
        <input type="text" name="district" required>

        <button type="submit" class="btn primary">Register</button>
    </form>
</div>

</body>
</html>
