<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Hostel Leave Management - Chandigarh University</title>
  <style>
    * { box-sizing: border-box; }
    body {
      margin: 0;
      font-family: Arial, sans-serif;
      background: #f2f4f7;
      min-height: 100vh;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
    }
    .login-box {
      background: #fff;
      width: 380px;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 4px 15px rgba(0,0,0,0.15);
      text-align: center;
    }
    .login-box img {
      width: 260px;
      max-width: 100%;
      margin-bottom: 15px;
    }
    .login-box h2 {
      margin: 0 0 20px;
      color: #333;
    }
    .login-box label {
      display: block;
      text-align: left;
      margin: 10px 0 5px;
      font-weight: bold;
    }
    .login-box input[type="text"],
    .login-box input[type="password"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    .login-box input[type="submit"] {
      width: 100%;
      margin-top: 20px;
      padding: 10px;
      background: #c8102e;
      color: #fff;
      border: none;
      border-radius: 5px;
      font-size: 16px;
      cursor: pointer;
    }
    .login-box input[type="submit"]:hover {
      background: #a00d25;
    }
  </style>
</head>
<body>

  <div class="login-box">
    <img src="${pageContext.request.contextPath}/images/logo.png" alt="Chandigarh University">
    <h2>Hostel Leave Management</h2>

    <!-- KEEP your existing action and name values here -->
    <form action="login" method="post">
      <label>Username:</label>
      <input type="text" name="username" required>

      <label>Password:</label>
      <input type="password" name="password" required>

      <input type="submit" value="Login">
    </form>
  </div>
<div style="width:300px; margin:40px auto;">
</div>
</body>
</html>