<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Shree Anna</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<header>
    <div class="logo">Shree Anna</div>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="marketplace">Marketplace</a>
        <a href="register.jsp">Register</a>
    </nav>
</header>

<div class="form-container">
    <h2>Login</h2>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <div class="alert error"><%= error %></div>
    <% } %>

    <form method="post" action="login">
        <label>Email</label>
        <input type="email" name="email" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <button type="submit" class="btn primary">Login</button>
    </form>
</div>

</body>
</html>
