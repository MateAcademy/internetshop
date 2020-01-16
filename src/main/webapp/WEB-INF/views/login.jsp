<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Autorization</title>
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
    <style>
        .colortext {
            color: red; /* Красный цвет выделения */
        }
    </style>
</head>
<body>
Hello login page :)
<h4>
    <div> <span class="colortext"> ${errorMsg}</span> </div>
</h4>
<form action="/login" method="post">
    <div class ="container">
        <h1>Login form:</h1>
        <p>Please fill in this form to sign into account.</p>
        <hr>
        <label for="login"><b>Login</b></label>
        <input type="text" class="form-control" id="login" placeholder="Enter login" name="login" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" class="form-control" id="psw" placeholder="Enter password" name="psw" required>

        <p>By creatin an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <button type="submit" class="registerbtn" class="new">Login</button>
    </div>

    <div class="container signin">
        <p>Don't have an account? <a href="/registration">Sign up</a>.</p>
    </div>
</form>

<form action="/servlet/mainController"><p><button class="new">MAIN MENU</button></p></form>
</body>

</html>
