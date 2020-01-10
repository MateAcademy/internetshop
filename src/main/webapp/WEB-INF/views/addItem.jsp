<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>add Item</title>
</head>
<body>
Let's add a new Item!
<form action="/servlet/addItem" method="post">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to add Item.</p>
        <hr>

        <h5>
        <label for="item_name"><b>item_name :</b></label>
        <input type="text" placeholder="Enter item name" name="item_name" required>
        <br>
        <label for="item_price"><b>item_price :</b></label>
        <input type="text" placeholder="Enter price" name="item_price" required>
        <br>
        <label for="item_description"><b>item_description</b></label>
        <input type="text" placeholder="Enter description" name="item_description" required>
        </h5>
        <p> By creating an account you agree to our <a href="#">Terms & Privasy</a>.</p>
        <button type="submit" class="registerbtn">add Item</button>
    </div>


    <div class="container sign">
        <p>Already have anaccount& <a href="#">Sign in</a>.</p>
    </div>


</form>
</body>
</html>
