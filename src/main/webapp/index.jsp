<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Index</title>
    <style>
        body{
            background: url("foto_girl.jpg") no-repeat;
            -moz-background-size: 100%;
            -webkit-background-size: 100%;
            -o-background-size: 100%;
            background-size: 100%;
            color:#cc0000
        }
    </style>
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
<body style="background-attachment: fixed" topmargin="0" >
<h2>MAIN MENU:</h2>

<form action="/servlet/registration"><p><button class="new">Register</button></p></form>
<form action="/servlet/addItem"><p><button class="new">Add item</button></p></form>
<form action="/servlet/registration"><p><button class="new">Add user</button></p></form>
<form action="/servlet/showAllItems"><p><button class="new">Show all items</button></p></form>
<form action="servlet/show-all-users"><p><button class="new">Show all users</button></p></form>
<form action="/servlet/showAllBuckets"><p><button class="new">Show buckets</button></p></form>
<form action="/servlet/showAllOrders"><p><button class="new">Show all orders</button></p></form>
</body>
</html>
