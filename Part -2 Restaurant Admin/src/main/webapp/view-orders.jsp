<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Orders</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>View Orders</h2>

        <!-- Pending Orders Table -->
        <h3>Pending Orders</h3>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>Restaurant ID</th>
                    <th>Food Name</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Order Status</th>
                    <th>Order Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${pendingOrders}" var="order">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.userId}</td>
                        <td>${order.restaurantId}</td>
                        <td>${order.foodName}</td>
                        <td>${order.quantity}</td>
                        <td>${order.total}</td>
                        <td>${order.orderStatus}</td>
                        <td>${order.orderDate}</td>
                        <td>
                            <form action="update-order-status" method="post">
                                <input type="hidden" name="orderId" value="${order.orderId}">
                                <input type="hidden" name="restaurantId" value="${order.restaurantId}">
                                <button type="submit">Mark as Dispatched</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Dispatched Orders Table -->
        <h3>Dispatched Orders</h3>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>Restaurant ID</th>
                    <th>Food Name</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Order Status</th>
                    <th>Order Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${dispatchedOrders}" var="order">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.userId}</td>
                        <td>${order.restaurantId}</td>
                        <td>${order.foodName}</td>
                        <td>${order.quantity}</td>
                        <td>${order.total}</td>
                        <td>${order.orderStatus}</td>
                        <td>${order.orderDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="admin-dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
