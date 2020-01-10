<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
    Hello  ${greeting} , Welcone to the ALL USERS PAGE !
</body>
</html>
