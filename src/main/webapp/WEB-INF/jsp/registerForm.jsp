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
            <form data-parsley-validate="" id="uesr-info" name="userInfo" method="post" action="/user/">
                <div class="form-group">
                    <label for="userName">사용자 아이디</label>
                    <input class="form-control" id="userName" name="userName"
                           placeholder="아이디는 2~10자 제한입니다" required=""
                           data-parsley-minlength="2" data-parsley-maxlength="10">
                    <input type="button" id="confirmId" value="중복 체크">
                    <span id="id_signed"></span>
                    <form:errors path="user.userName" cssClass="error" />
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="비밀번호는 8자 ~ 16자 사이 영숫자 혼합입니다" required=""
                           data-parsley-pattern="^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,16}$">
                    <form:errors path="user.password" cssClass="error" />

                </div>
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" name="email"
                           placeholder="이메일을 입력하세요" data-parsley-trigger="change" required="">
                    <form:errors path="user.email" cssClass="error" />
                </div>
                <button type="submit" class="btn btn-success clearfix pull-right">회원가입</button>
                <div class="clearfix" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>

</div>

<script src="/assets/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/assets/js/registerForm.js"></script>
<script src="/assets/js/parsley.js"></script>
</body>
</html>