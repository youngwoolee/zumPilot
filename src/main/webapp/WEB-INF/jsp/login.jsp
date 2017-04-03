<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>

<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/assets/css/login.css" rel="stylesheet" type="text/css">
</head>

<body>
<c:import url="include/navigation.jsp"/>

<div class="wrapper">

    <form action="/login" class="form-signin" method="post">


        <c:if test="${param.error != null}">
            <p>
                Invalid username and password.
            </p>
        </c:if>
        <h2 class="form-signin-heading">Please login</h2>
        <input type="text" class="form-control" name="username" placeholder="UserID" required="" autofocus="" />
        <input type="password" class="form-control" name="password" placeholder="Password" required=""/>
        <button class="btn btn-success clearfix pull-right" type="submit">Login</button>
    </form>
</div>

</body>
</html>