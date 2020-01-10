<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
Hello ${greeting} , Welcome to the ALL USERS PAGE !

<!--Users: ${users}-->

<table border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>surname</td>
        <td>email</td>
        <td>login</td>
        <td>password</td>
        <td>delete</td>
    </tr>
    <c:forEach var="user" items="${users}">
    <tr>
        <td><c:out value = "${user.id}"/></td>
        <td><c:out value = "${user.name}"/></td>
        <td><c:out value = "${user.surname}"/></td>
        <td><c:out value = "${user.email}"/></td>
        <td><c:out value = "${user.login}"/></td>
        <td><c:out value = "${user.password}"/></td>
        <td><a href="/servlet/deleteUser?user_id=${user.id}" >DELETE</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>