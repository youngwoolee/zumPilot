<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>

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
            <form name="question" method="POST" action="/user/update/${user.userId}">
                <div class="form-group">
                    <label for="userName">사용자 아이디</label>
                    <input class="form-control" id="userName" name="userName" placeholder="User ID" value="${user.userName}" readonly>
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${user.email}">
                </div>
                <a type="button" href="/user/${user.userId}">회원 탈퇴</a>
                <button type="submit" class="btn btn-success clearfix pull-right">개인정보 수정</button>

            </form>
        </div>
    </div>

</div>

</body>
</html>