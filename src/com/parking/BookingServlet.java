package com.parking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookSlot")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullname");
        String vehicleNumber = request.getParameter("vehicle");
        String vehicleType = request.getParameter("vehicletype");
        String slot = request.getParameter("slot");
        String date = request.getParameter("date");
        String time = request.getParameter("time");

        try {
            Connection con = DBConnection.getConnection();

            String insertBooking = "INSERT INTO bookings (full_name, vehicle_number, vehicle_type, slot_number, booking_date, entry_time) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(insertBooking);
            pst.setString(1, fullName);
            pst.setString(2, vehicleNumber);
            pst.setString(3, vehicleType);
            pst.setString(4, slot);
            pst.setString(5, date);
            pst.setString(6, time);
            pst.executeUpdate();

            String updateSlot = "UPDATE parking_slots SET status='Occupied' WHERE slot_number=?";
            PreparedStatement pst2 = con.prepareStatement(updateSlot);
            pst2.setString(1, slot);
            pst2.executeUpdate();

            response.getWriter().println("<h3>Booking successful!</h3><a href='index.html'>Go Home</a>");
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}