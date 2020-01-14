<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Show bucket empty list</title>
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
<center>
<b>ITEM IN YOUR BUCKET:</b>
<hr>
<table border="1">
    <tr>
        <td>Item Name</td>
        <td>Item Prise</td>
        <td>Item description</td>
        <td><span class="colortext">delete</span></td>
    </tr>
</table>
<form action="/servlet/showAllItems">
    <p><button class="new">Add item in your bucket</button></p>
</form>
<form action="/servlet/mainController">
    <p><button class="new">BACK TO HOME</button></p>
</form>
</center>
</body>
</html>