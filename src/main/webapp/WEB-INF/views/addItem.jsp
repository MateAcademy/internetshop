<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>add Item</title>
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
</head>
<body bgcolor="#5f9ea0">
Let's add a new Item!
<form action="/servlet/addItem" method="post">
    <div class="container">
        <h3>ITEM ADDING</h3>
        <p>Please fill in this form to add Item:</p>
        <hr>
        <h5>
        <label for="item_name"><b>item_name :</b></label>
        <input type="text" id="item_name" placeholder="Enter item name" name="item_name" required>
        <br>
        <label for="item_price"><b>item_price :</b></label>
        <input type="text" id="item_price" placeholder="Enter price" name="item_price" required>
        <br>
        <label for="item_description"><b>item_description</b></label>
        <input type="text" id="item_description" placeholder="Enter description" name="item_description" required>
        </h5>
        <p> By creating an account you agree to our <a href="#">Terms & Privasy</a>.</p>

        <button type="submit" class="new">Add item</button>
    </div>
</form>
<hr>
<form action="/servlet/getAllItems"><p><button class="new">All items</button></p></form>
<form action="/servlet/mainController"><p><button class="new">BACK TO HOME</button></p></form>

</body>
</html>
