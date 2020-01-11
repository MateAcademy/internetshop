<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <style type="text/css">
        button.new {
            background: -moz-linear-gradient(#00BBD6, #EBFFFF);
            background: -webkit-gradient(linear, 0 0, 0 100%, from(#00BBD6), to(#EBFFFF));
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00BBD6', endColorstr='#EBFFFF');
            padding: 3px 7px;
            color: #333;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            border-radius: 5px;
            border: 1px solid #666;
        }
    </style>
    <style>
        .colortext {
            color: red; /* Красный цвет выделения */
        }
    </style>
</head>
<body bgcolor="#5f9ea0">
<h4>
    <span class="colortext"> ${error}</span>
</h4>
    Let's create a new User!
    <form action="/servlet/registration" method="post">
        <div class="container">
            <h3>Register form:</h3>
            <hr>
            <label for="user_name"><b>Name</b></label>
            <input type="text" placeholder="Enter name" name="user_name" required>
            <br>
            <label for="user_surname"><b>Surname</b></label>
            <input type="text" placeholder="Enter curname" name="user_surname" required>
            <br>
            <label for="email"><b>Email</b></label>
            <input type="email" placeholder="Enter email" name="email" required>
            <br>
            <label for="phone"><b>Phone</b></label>
            <input type="phone" placeholder="Enter phone" name="phone" required>
            <br>
            <label for="login"><b>Login</b></label>
            <input type="text" placeholder="Enter login" name="login" required>
            <br>
            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter password" name="psw" required>
            <br>
            <label for="psw2"><b>Repeat Password</b></label>
            <input type="password" placeholder="Enter password" name="psw2" required>
            <hr>
            <button type="submit" class="new">Register</button>
        </div>

    </form>
    <form action="/servlet/mainController"><p><button class="new">MAIN MENU</button></p></form>
</body>

</html>
