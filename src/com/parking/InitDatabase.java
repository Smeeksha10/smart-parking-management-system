package com.parking;

import java.sql.Connection;
import java.sql.Statement;

public class InitDatabase {
    public static void main(String[] args) {
        try {
            Connection con = DBConnection.getConnection();
            if (con == null) {
                System.out.println("Database connection failed.");
                return;
            }
            Statement st = con.createStatement();

            String adminTable = "CREATE TABLE IF NOT EXISTS admin (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT NOT NULL, " +
                    "password TEXT NOT NULL" +
                    ");";

            String slotTable = "CREATE TABLE IF NOT EXISTS parking_slots (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "slot_number TEXT NOT NULL UNIQUE, " +
                    "status TEXT NOT NULL" +
                    ");";

            String bookingTable = "CREATE TABLE IF NOT EXISTS bookings (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "full_name TEXT NOT NULL, " +
                    "vehicle_number TEXT NOT NULL, " +
                    "vehicle_type TEXT NOT NULL, " +
                    "slot_number TEXT NOT NULL, " +
                    "booking_date TEXT NOT NULL, " +
                    "entry_time TEXT NOT NULL" +
                    ");";

            st.execute(adminTable);
            st.execute(slotTable);
            st.execute(bookingTable);

            st.executeUpdate("INSERT OR IGNORE INTO admin (id, username, password) VALUES (1, 'admin', 'admin123')");

            st.executeUpdate("INSERT OR IGNORE INTO parking_slots (slot_number, status) VALUES ('A1', 'Available')");
            st.executeUpdate("INSERT OR IGNORE INTO parking_slots (slot_number, status) VALUES ('A2', 'Occupied')");
            st.executeUpdate("INSERT OR IGNORE INTO parking_slots (slot_number, status) VALUES ('A3', 'Available')");
            st.executeUpdate("INSERT OR IGNORE INTO parking_slots (slot_number, status) VALUES ('A4', 'Occupied')");
            st.executeUpdate("INSERT OR IGNORE INTO parking_slots (slot_number, status) VALUES ('B1', 'Available')");
            st.executeUpdate("INSERT OR IGNORE INTO parking_slots (slot_number, status) VALUES ('B2', 'Available')");
            st.executeUpdate("INSERT OR IGNORE INTO parking_slots (slot_number, status) VALUES ('B3', 'Occupied')");
            st.executeUpdate("INSERT OR IGNORE INTO parking_slots (slot_number, status) VALUES ('B4', 'Available')");

            System.out.println("Database and tables created successfully.");
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}