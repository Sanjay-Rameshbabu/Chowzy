<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Food Items</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Edit Food Items</h2>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Food Name</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${foodList}" var="food">
                    <tr>
                        <td>${food.name}</td>
                        <td>
                            <form action="update-food-price" method="post">
                                <input type="hidden" name="foodName" value="${food.name}">
                                <input type="text" name="price" value="${food.price}">
                                <button type="submit">Update Price</button>
                            </form>
                        </td>
                        <td>
                            <form action="delete-food" method="post">
                                <input type="hidden" name="foodName" value="${food.name}">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="admin-dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
