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
            <form data-parsley-validate="" name="question" method="POST" action="/user/update/${user.userId}">
                <div class="form-group">
                    <label>사용자 아이디</label>
                    <br>
                    ${user.userName}
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password"
                           data-parsley-pattern="^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,16}$">
                </div>
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Email"
                           value="${user.email}" data-parsley-trigger="change" required="">
                </div>
                <a id="user-leave" value="${user.userId}" href="javascript:;">회원 탈퇴</a>
                <button type="submit" class="btn btn-success clearfix pull-right">개인정보 수정</button>

            </form>
        </div>
    </div>

</div>

<script src="/assets/js/jquery-3.2.0.min.js"></script>
<script src="/assets/js/parsley.js"></script>
<script src="/assets/js/userLeave.js"></script>


</body>
</html>