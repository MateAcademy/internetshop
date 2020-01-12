<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>all Items</title>
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
Hello ${greeting} , Welcome to the ALL ITEMS PAGE !

<!--Users: ${users}-->
    <hr>

<table border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>prise</td>
        <td>description</td>
        <td><span class="colortext">DELETE ITEM</span></td>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td><c:out value = "${item.id}"/></td>
            <td><c:out value = "${item.name}"/></td>
            <td><c:out value = "${item.price}"/></td>
            <td><c:out value = "${item.description}"/></td>
            <td><a href="/servlet/deleteItem?item_id=${item.id}" ><span class="colortext">delete</span></a></td>
        </tr>
    </c:forEach>
</table>
<form action="/servlet/addItem">
    <p><button class="new">Add item</button></p>
</form>
<form action="/servlet/mainController">
    <p><button class="new">BACK TO HOME</button></p>
</form>
</center>
</body>
</html>