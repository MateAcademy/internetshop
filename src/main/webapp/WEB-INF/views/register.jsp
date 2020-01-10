<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration</title>
</head>
<body>
    Let's create a new User!
    <form action="/servlet/registration" method="post">
        <div class="container">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>

            <label for="user_name"><b>Name</b></label>
            <input type="text" placeholder="Enter name" name="user_name" required>
            <br>
            <label for="user_surname"><b>SurName</b></label>
            <input type="text" placeholder="Enter curname" name="user_surname" required>
            <br>
            <label for="email"><b>Email</b></label>
            <input type="email" placeholder="Enter email" name="email" required>
            <br>
            <label for="login"><b>Login</b></label>
            <input type="text" placeholder="Enter ligin" name="login" required>
            <br>
            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter password" name="psw" required>
            <hr>

            <p> By creating an account you agree to our <a href="#">Terms & Privasy</a>.</p>
            <button type="submit" class="registerbtn">Register</button>
        </div>


        <div class="container sign">
            <p>Already have anaccount& <a href="#">Sign in</a>.</p>
        </div>


    </form>
</body>
</html>
