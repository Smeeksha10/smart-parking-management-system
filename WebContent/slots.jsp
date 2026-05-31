<%@ page import="java.sql.*" %>
<%@ page import="com.parking.DBConnection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Parking Slots</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<header class="navbar">
    <div class="logo">SmartParking</div>
    <nav>
      <a href="index.html">Home</a>
      <a href="slots.jsp">Slots</a>
      <a href="booking.html">Book Now</a>
      <a href="admin-login.html">Admin</a>
    </nav>
</header>

<section class="slots-section">
    <div class="slots-header">
      <h1>Available Parking Slots</h1>
      <p>Check which slots are free and which are occupied.</p>
    </div>

    <div class="slots-grid">
        <%
            try {
                Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM parking_slots");

                while(rs.next()) {
                    String slotNumber = rs.getString("slot_number");
                    String status = rs.getString("status");
                    String cssClass = status.equalsIgnoreCase("Available") ? "available-slot" : "occupied-slot";
        %>
                    <div class="slot-card <%= cssClass %>">
                        <h3><%= slotNumber %></h3>
                        <p><%= status %></p>
                    </div>
        <%
                }
                con.close();
            } catch(Exception e) {
                out.println("Error loading slots");
                e.printStackTrace();
            }
        %>
    </div>
</section>

<footer class="footer">
    <p>Smart Parking System © 2026 | Slot Status Page</p>
</footer>

</body>
</html>