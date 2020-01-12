<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Order>"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Show all Orders</title>
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
    <b>ALL ORDERS LIST:</b>
    <hr>
    <table border="1">
        <tr>
            <td>Order ID</td>
            <td>Items</td>
            <td><span class="colortext">DELETE ORDER</span></td>
        </tr>
        <c:forEach var="item" items="${items}">
            <tr>
                <td><c:out value="${item.id}"/></td>
                <td><c:out value="${item.items}"/></td>
                <td><a href="/servlet/deleteOrder?item_id=${item.id}"><span class="colortext">DELETE</span></a></td>
            </tr>
        </c:forEach>
    </table>

    <form action="/servlet/mainController">
        <p>
            <button class="new">BACK TO HOME</button>
        </p>
    </form>
</center>
</body>
</html>