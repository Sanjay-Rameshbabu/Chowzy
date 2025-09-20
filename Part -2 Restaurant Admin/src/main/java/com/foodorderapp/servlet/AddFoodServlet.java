package com.foodorderapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.foodorderapp.model.Food;
import com.foodorderapp.util.DBConnection;

@WebServlet("/add-food")
public class AddFoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("fname");
        double price = Double.parseDouble(request.getParameter("fprice"));
        HttpSession session = request.getSession();
        String restaurantID = (String) session.getAttribute("resID");

        Food food = new Food();
        food.setName(name);
        food.setPrice(price);
        food.setRestaurantId(restaurantID);

        Connection conn = null;
        PreparedStatement pstmt = null;

        String fmessage = ""; // To hold the success or failure message
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO foods (name, price, restaurant_id) VALUES (?, ?, ?)");
            pstmt.setString(1, food.getName());
            pstmt.setDouble(2, food.getPrice());
            pstmt.setString(3, food.getRestaurantId());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                fmessage = "Food added successfully!";
            } else {
                fmessage = "Food addition failed!";
            }

        } catch (SQLException e) {
            fmessage = "Food addition failed due to an error!";
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Forward the message back to add-food.jsp
        request.setAttribute("message", fmessage);
        request.getRequestDispatcher("add-food.jsp").forward(request, response);
    }
}
