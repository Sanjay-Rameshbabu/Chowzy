package com.foodorderapp.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.foodorderapp.model.Restaurant;
import com.foodorderapp.util.DBConnection;


@WebServlet("/register-restaurant")
public class RegisterRestaurantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String id=request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        
        

        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setName(name);
        restaurant.setEmail(email);
        restaurant.setPassword(password);
        restaurant.setAddress(address);
        restaurant.setPhone(phone);

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO restaurants (id,name, email, password, address, phone) VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, restaurant.getId());
            pstmt.setString(2, restaurant.getName());
            pstmt.setString(3, restaurant.getEmail());
            pstmt.setString(4, restaurant.getPassword());
            pstmt.setString(5, restaurant.getAddress());
            pstmt.setString(6, restaurant.getPhone());
            int rowaffected= pstmt.executeUpdate();

            if(rowaffected > 0)
            {
            	request.setAttribute("registermsg", "Register Successfull!");
            }
            else {
            	request.setAttribute("registermsg", "Register UnSuccessfull!");
            }
            
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
