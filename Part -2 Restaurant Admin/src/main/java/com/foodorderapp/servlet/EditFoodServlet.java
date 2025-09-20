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

import com.foodorderapp.model.Food;
import com.foodorderapp.util.DBConnection;

@WebServlet("/edit-food")
public class EditFoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        HttpSession session = request.getSession();
        String restaurantId = (String) session.getAttribute("resID"); // Logged-in restaurant ID

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM foods WHERE restaurant_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, restaurantId);
            rs = pstmt.executeQuery();

            List<Food> foodList = new ArrayList<>();
            while (rs.next()) {
                Food food = new Food();
                food.setName(rs.getString("name"));
                food.setPrice(rs.getDouble("price"));
                food.setRestaurantId(rs.getString("restaurant_id"));
                foodList.add(food);
            }
            
            request.setAttribute("foodList", foodList);
            request.getRequestDispatcher("edit-food.jsp").forward(request, response);

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

