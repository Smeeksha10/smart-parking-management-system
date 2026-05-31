package com.parking;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:sqlite:/Users/smeekshasangra/SmartParkingSystem/parking.db";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}