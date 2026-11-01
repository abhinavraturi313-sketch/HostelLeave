<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.hostel.model.*" %>
<%
  User user = (User) session.getAttribute("user");
  if (user == null) { response.sendRedirect("index.jsp"); return; }
  List<LeaveRequest> leaves = (List<LeaveRequest>) request.getAttribute("leaves");
  List<Complaint> complaints = (List<Complaint>) request.getAttribute("complaints");
%>
<html>
<head>
  <title>Warden Dashboard</title>
  <link rel="stylesheet" href="style.css">
  <style>
    table { border-collapse: collapse; width: 100%; }
    th, td { border: 1px solid #999; padding: 6px 10px; text-align: left; vertical-align: top; }
    th { background: #eee; }
  </style>
</head>
<body>

<div style="padding:10px; border-bottom:2px solid #c8102e;">
  <img src="${pageContext.request.contextPath}/images/logo.png" height="60" alt="Chandigarh University">
</div>
<div class="box">
  <div class="top">
    <h2>Warden Panel - <%= user.getFullName() %></h2>
    <a href="logout">Logout</a>
  </div>

  <h3>All Leave Requests</h3>
  <table>
    <tr>
      <th width="5%">ID</th><th width="12%">Student</th><th width="8%">Room</th>
      <th width="10%">From</th><th width="10%">To</th><th width="15%">Reason</th>
      <th width="15%">Status</th><th width="25%">Action</th>
    </tr>
    <% for (LeaveRequest l : leaves) { %>
    <tr>
      <td><%= l.getId() %></td><td><%= l.getStudentName() %></td><td><%= l.getRoomNo() %></td>
      <td><%= l.getFromDate() %></td><td><%= l.getToDate() %></td><td><%= l.getReason() %></td>
      <td class="<%= l.getStatus() %>"><%= l.getStatus() %><br><small><%= l.getRemark() %></small></td>
      <td>
        <% if (l.getStatus().equals("Pending")) { %>
        <form action="update" method="post">
          <input type="hidden" name="id" value="<%= l.getId() %>">
          <input type="text" name="remark" placeholder="Remark">
          <button type="submit" name="action" value="Approved">Approve</button>
          <button type="submit" name="action" value="Rejected">Reject</button>
        </form>
        <% } %>
      </td>
    </tr>
    <% } %>
  </table>

  <hr>
  <h3>All Complaints</h3>
  <table>
    <tr>
      <th width="5%">ID</th><th width="12%">Student</th><th width="8%">Room</th>
      <th width="12%">Category</th><th width="25%">Description</th>
      <th width="15%">Status</th><th width="23%">Action</th>
    </tr>
    <% for (Complaint c : complaints) { %>
    <tr>
      <td><%= c.getId() %></td><td><%= c.getStudentName() %></td><td><%= c.getRoomNo() %></td>
      <td><%= c.getCategory() %></td><td><%= c.getDescription() %></td>
      <td class="<%= c.getStatus() %>"><%= c.getStatus() %><br><small><%= c.getRemark() %></small></td>
      <td>
        <% if (c.getStatus().equals("Pending")) { %>
        <form action="complaintUpdate" method="post">
          <input type="hidden" name="id" value="<%= c.getId() %>">
          <input type="text" name="remark" placeholder="Remark">
          <button type="submit" name="action" value="Resolved">Resolve</button>
          <button type="submit" name="action" value="Rejected">Reject</button>
        </form>
        <% } %>
      </td>
    </tr>
    <% } %>
  </table>
</div>
</body>
</html>