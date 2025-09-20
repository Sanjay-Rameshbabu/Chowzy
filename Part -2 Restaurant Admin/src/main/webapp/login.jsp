<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Login</h2>
         <!-- Display Success Message -->
        <%
    	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    	
            String successMessage = (String)request.getAttribute("registermsg");
            if (successMessage != null) {
                out.println("<p style='color: green;'>" + successMessage + "</p>");
                request.removeAttribute("registermsg"); // Remove the message after displaying
            }
            
            String delmsg=(String)request.getAttribute("message");
            if (delmsg!= null) {
                out.println("<p style='color: green;'>" + delmsg+ "</p>");
                session.removeAttribute("message"); // Remove the message after displaying
            }
        %>
        <form action="login" method="post">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
        <p>Don't have an account? <a href="register.jsp">Register here</a></p>

        <!-- Display Error Message -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <p style="color: red;"><%= errorMessage %></p>
        <%
            }
            
        %>
         
    </div>
</body>
</html>
