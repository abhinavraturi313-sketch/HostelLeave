<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.hostel.model.*" %>
<%
  User user = (User) session.getAttribute("user");
  if (user == null) { response.sendRedirect("index.jsp"); return; }
  List<LeaveRequest> leaves = (List<LeaveRequest>) request.getAttribute("leaves");
  List<Complaint> complaints = (List<Complaint>) request.getAttribute("complaints");
%>
<html>
<head>
  <title>Student Dashboard</title>
  <link rel="stylesheet" href="style.css">
  <style>
    table { border-collapse: collapse; width: 100%; }
    th, td { border: 1px solid #999; padding: 6px 10px; text-align: left; vertical-align: top; }
    th { background: #eee; }
  </style>
</head>
<body>
<body>

<div style="padding:10px; border-bottom:2px solid #c8102e;">
  <img src="${pageContext.request.contextPath}/images/logo.png" height="60" alt="Chandigarh University">
</div>
<div class="box">
  <div class="top">
    <h2>Welcome, <%= user.getFullName() %> (Room <%= user.getRoomNo() %>)</h2>
    <a href="logout">Logout</a>
  </div>

  <h3>Apply for Leave</h3>
  <form action="apply" method="post">
    From: <input type="date" name="fromDate" required>
    To: <input type="date" name="toDate" required><br>
    Reason:<br><textarea name="reason" rows="3" cols="50" required></textarea><br>
    <button type="submit">Submit Leave</button>
  </form>

  <h3>My Leave Requests</h3>
  <table>
    <tr><th>ID</th><th>From</th><th>To</th><th>Reason</th><th>Status</th><th>Warden Remark</th><th>Action</th></tr>
    <% for (LeaveRequest l : leaves) { %>
    <tr>
      <td><%= l.getId() %></td><td><%= l.getFromDate() %></td><td><%= l.getToDate() %></td>
      <td><%= l.getReason() %></td>
      <td class="<%= l.getStatus() %>"><%= l.getStatus() %></td>
      <td><%= l.getRemark() %></td>
      <td>
        <% if (l.getStatus().equals("Pending")) { %>
        <form action="update" method="post">
          <input type="hidden" name="id" value="<%= l.getId() %>">
          <input type="hidden" name="action" value="cancel">
          <button type="submit">Cancel</button>
        </form>
        <% } %>
      </td>
    </tr>
    <% } %>
  </table>

  <hr>
  <h3>Raise a Complaint</h3>
  <form action="complaint" method="post">
    Category:
    <select name="category">
      <option>Electricity</option><option>Water</option><option>Mess</option>
      <option>Cleaning</option><option>Other</option>
    </select><br>
    Description:<br><textarea name="description" rows="3" cols="50" required></textarea><br>
    <button type="submit">Submit Complaint</button>
  </form>

  <h3>My Complaints</h3>
  <table>
    <tr><th>ID</th><th>Category</th><th>Description</th><th>Status</th><th>Warden Remark</th><th>Action</th></tr>
    <% for (Complaint c : complaints) { %>
    <tr>
      <td><%= c.getId() %></td><td><%= c.getCategory() %></td><td><%= c.getDescription() %></td>
      <td class="<%= c.getStatus() %>"><%= c.getStatus() %></td>
      <td><%= c.getRemark() %></td>
      <td>
        <% if (c.getStatus().equals("Pending")) { %>
        <form action="complaintUpdate" method="post">
          <input type="hidden" name="id" value="<%= c.getId() %>">
          <input type="hidden" name="action" value="delete">
          <button type="submit">Withdraw</button>
        </form>
        <% } %>
      </td>
    </tr>
    <% } %>
  </table>

  <%-- Implicit object: session --%>
  <p class="small">Session ID: <%= session.getId() %> |
     Created: <%= new Date(session.getCreationTime()) %> |
     Last accessed: <%= new Date(session.getLastAccessedTime()) %></p>
</div>
</body>
</html>
