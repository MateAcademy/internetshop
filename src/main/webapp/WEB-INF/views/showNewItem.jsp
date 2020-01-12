<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page  contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Show new Item</title>
</head>
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
<body  bgcolor="#5f9ea0">

<b>ADDED ITEM:</b>
<p>
Item name: ${name} <p>
Item price: ${price} <p>
Item description: ${description}

    <form action="/servlet/addItem"><p><button class="new">Add new items</button></p></form>
    <form action="/servlet/getAllItems"><p><button class="new">Show all items</button></p></form>
    <form action="/servlet/mainController"><p><button class="new">BACK TO HOME</button></p></form>
</body>
</html>
