package com.foodorderapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodorderapp.util.DBConnection;

@WebServlet("/update-order-status")
public class UpdateOrderStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String restaurantId = request.getParameter("restaurantId");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE orders SET order_status = 'Dispatched' WHERE order_id = ? AND restaurant_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderId);
            pstmt.setString(2, restaurantId);
            pstmt.executeUpdate();

            // Redirect to the view orders page to fetch the updated list
            response.sendRedirect("view-orders");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
