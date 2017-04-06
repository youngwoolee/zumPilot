<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>

<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">


</head>

<body>

<c:import url="include/navigation.jsp"/>

<div class="container">

    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default content-main">
            <spring:hasBindErrors name="user"/>
            <form name="question" method="post" action="/user/">
                <div class="form-group">
                    <label for="userName">사용자 아이디</label>
                    <input class="form-control" id="userName" name="userName" placeholder="User ID">
                    <form:errors path="user.userName" cssClass="error" />
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                    <form:errors path="user.password" cssClass="error" />

                </div>
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                    <form:errors path="user.email" cssClass="error" />
                </div>
                <button type="submit" class="btn btn-success clearfix pull-right">회원가입</button>
                <div class="clearfix" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>

</div>

</body>
</html>