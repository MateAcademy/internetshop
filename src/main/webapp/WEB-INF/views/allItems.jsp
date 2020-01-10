<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>all Items</title>
</head>
<body>
Hello ${greeting} , Welcome to the ALL USERS PAGE !

<!--Users: ${users}-->

<table border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>prise</td>
        <td>description</td>
        <td>delete</td>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td><c:out value = "${item.id}"/></td>
            <td><c:out value = "${item.name}"/></td>
            <td><c:out value = "${item.price}"/></td>
            <td><c:out value = "${item.description}"/></td>
            <td><a href="/servlet/deleteItem?item_id=${item.id}" >DELETE</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>