<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>All Users</title>
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
    <style>
        .shrift
        {
            font-size:20px;
        }
    </style>
</head>
<body  bgcolor="#5f9ea0">
<center>
Hello ${greeting} , Welcome to the ALL USERS LIST :
<hr>
<!--Users: ${users}-->

<table border="1" class="shrift">

    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
        <td>Phone</td>
        <td>Login</td>
        <td><span class="colortext">DELETE USER</span></td>
    </tr>
    <c:forEach var="user" items="${users}">
    <tr>
        <td><c:out value = "${user.id}"/></td>
        <td><c:out value = "${user.name}"/></td>
        <td><c:out value = "${user.surname}"/></td>
        <td><c:out value = "${user.email}"/></td>
        <td><c:out value = "${user.phone}"/></td>
        <td><c:out value = "${user.login}"/></td>
        <td><a href="/servlet/deleteUser?user_id=${user.id}" ><span class="colortext">DELETE</span></a></td>
    </tr>
    </c:forEach>

</table>
</form>
<form action="/servlet/registration"><p><button class="new">Register</button></p></form>

</form>
<form action="/servlet/mainController"><p><button class="new">MAIN MENU</button></p></form>
</center>
</body>
</html>