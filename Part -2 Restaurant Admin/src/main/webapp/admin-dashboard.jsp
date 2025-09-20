<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" href="css/style.css">
<style>
    /* Modal Styles */
    #confirmModal {
        display: none;
        position: fixed;
        z-index: 1000;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.4); /* Black background with opacity */
    }
    #confirmModalContent {
        background-color: #fff;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 30%;
        text-align: center;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
    #confirmModalContent button {
        margin: 10px;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    #confirmModalContent button#confirmYes {
        background-color: #28a745;
        color: white;
    }
    #confirmModalContent button#confirmNo {
        background-color: #dc3545;
        color: white;
    }
</style>
<script>
    function showConfirmModal() {
        document.getElementById("confirmModal").style.display = "block";
    }
    function hideConfirmModal() {
        document.getElementById("confirmModal").style.display = "none";
    }
    function confirmRemoval() {
        window.location.href = "remove-restaurant?action=confirm";
    }
</script>
</head>
<body>
    <div class="container">
        <h2>Admin Dashboard</h2>
		
		<%
		
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			if(session.getAttribute("resID")==null)
		{
			response.sendRedirect("login.jsp");
		}
			
		%>
        <!-- Display Success Message -->
        <%
            session = request.getSession(false);
            String successMessage = (String) session.getAttribute("successMessage");
            if (successMessage != null) {
                out.println("<p style='color: green;'>" + successMessage + "</p>");
                session.removeAttribute("successMessage"); // Remove the message after displaying
            }
           
        %>

        <ul>
            <li><a href="add-food.jsp">Add Food</a></li>
            <li><a href="view-orders">View Orders</a></li>
            <li><a href="edit-food">Edit Food Items</a></li>
            <li><a href="#" onclick="showConfirmModal()">Remove Your Restaurant</a></li>
            <li><a href="logout.jsp">Logout</a></li>
        </ul>
    </div>

    <!-- Confirmation Modal -->
    <div id="confirmModal">
        <div id="confirmModalContent">
            <p>Would you like to remove your restaurant completely?</p>
            <button id="confirmYes" onclick="confirmRemoval()">Yes</button>
            <button id="confirmNo" onclick="hideConfirmModal()">Cancel</button>
        </div>
    </div>
</body>
</html>
