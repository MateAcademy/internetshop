<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <style type="text/css"><%@include file="/WEB-INF/styles/bootstrap.min.css" %></style>
    <style>
        .colortext {
            color: red; /* Красный цвет выделения */
        }
    </style>
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
        }</style>
</head>
<body bgcolor="#5f9ea0">
<h4>
    <span class="colortext"> ${error}</span>
</h4>
    Let's create a new User!
    <form action="/servlet/registration" method="post">
        <div class ="container">
            <h1>Register form:</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>
            <label for="user_name"><b>Name</b></label>
            <input type="text" class="form-control" id="user_name"  placeholder="Enter name" name="user_name" required>

            <label for="user_surname"><b>Surname</b></label>
            <input type="text" class="form-control" id="user_surname" placeholder="Enter curname" name="user_surname" required>

            <label for="email"><b>Email</b></label>
            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required>

            <label for="phone"><b>Phone</b></label>
            <input type="phone" class="form-control" id="phone" placeholder="Enter phone" name="phone" required>

            <label for="login"><b>Login</b></label>
            <input type="text" class="form-control" id="login" placeholder="Enter login" name="login" required>

            <label for="psw"><b>Password</b></label>
            <input type="password" class="form-control" id="psw" placeholder="Enter password" name="psw" required>

            <label for="psw2"><b>Repeat Password</b></label>
            <input type="password" class="form-control" id="psw2"  placeholder="Enter password" name="psw2" required>
            <hr>

            <p>By creatin an account you agree to our <a href="#">Terms & Privacy</a>.</p>
            <button type="submit" class="registerbtn" class="new">Register</button>
        </div>

        <div class="container signin">
            <p>Already have an account? <a href="#">Sign in</a>.</p>
        </div>
    </form>

    <form action="/servlet/mainController"><p><button class="new">MAIN MENU</button></p></form>
</body>

</html>
