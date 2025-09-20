package com.foodorderapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodorderapp.model.Order;
import com.foodorderapp.util.DBConnection;

@WebServlet("/view-orders")
public class ViewOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        HttpSession session = request.getSession();
        String restaurantId = (String) session.getAttribute("resID");

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM orders WHERE restaurant_id = ? ORDER BY order_date DESC";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, restaurantId);
            rs = pstmt.executeQuery();

            List<Order> pendingOrders = new ArrayList<>();
            List<Order> dispatchedOrders = new ArrayList<>();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("ordered_user_id"));
                order.setRestaurantId(rs.getString("restaurant_id"));
                order.setFoodName(rs.getString("food_name"));
                order.setQuantity(rs.getInt("quantity"));
                order.setTotal(rs.getDouble("total"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setOrderDate(rs.getTimestamp("order_date"));

                if ("Pending".equals(order.getOrderStatus())) {
                    pendingOrders.add(order);
                } else if ("Dispatched".equals(order.getOrderStatus())) {
                    dispatchedOrders.add(order);
                }
            }

            request.setAttribute("pendingOrders", pendingOrders);
            request.setAttribute("dispatchedOrders", dispatchedOrders);
            request.getRequestDispatcher("view-orders.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
