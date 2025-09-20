<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Food</title>
<link rel="stylesheet" href="css/style.css">
</head>

    <body>
    <div class="container">
        <h2>Add Food</h2>

        <!-- Display success or failure message -->
        <%
    	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    	
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
            <p style="color: <%= message.contains("successfully") ? "green" : "red" %>;">
                <%= message %>
            </p>
        <%
            }
        %>

        <form action="add-food" method="get">
            <div class="form-group">
                <label for="name">Food Name:</label>
                <input type="text" class="form-control" id="name" name="fname" required>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" class="form-control" id="price" name="fprice" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Food</button><br><br>
        </form>
         <a href="admin-dashboard.jsp">Back to Dashboard</a>
         
          <script>
        // Replace the browser history state to avoid duplicate entries
        window.onload = function() {
            history.replaceState(null, "", "add-food.jsp");
        };
    </script>
    </div>
</body>

</html>
