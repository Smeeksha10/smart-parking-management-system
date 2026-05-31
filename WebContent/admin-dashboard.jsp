<%@ page import="java.sql.*" %>
<%@ page import="com.parking.DBConnection" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>

<header class="navbar">
  <div class="logo">SmartParking</div>
  <nav>
    <a href="index.html">Home</a>
    <a href="slots.jsp">Slots</a>
    <a href="booking.html">Book Now</a>
    <a href="admin-dashboard.jsp">Dashboard</a>
  </nav>
</header>

<section class="dashboard-section">
  <div class="dashboard-header">
    <h1>Admin Dashboard</h1>
    <p>Manage parking slots and bookings.</p>
  </div>

  <div class="table-container">
    <h2>Parking Slot Status</h2>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Slot Number</th>
          <th>Status</th>
        </tr>
      </thead>
      <tbody>
      <%
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM parking_slots");

            while(rs.next()) {
      %>
        <tr>
          <td><%= rs.getInt("id") %></td>
          <td><%= rs.getString("slot_number") %></td>
          <td><%= rs.getString("status") %></td>
        </tr>
      <%
            }
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
      %>
      </tbody>
    </table>
  </div>
</section>

<footer class="footer">
  <p>Smart Parking System © 2026 | Admin Dashboard</p>
</footer>

</body>
</html>