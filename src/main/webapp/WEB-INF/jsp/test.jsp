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

            <form data-parsley-validate="" id="uesr-info" name="userInfo" action="#" onsubmit="return validateForm()">
                <div class="form-group">
                    <label for="userName">사용자 아이디</label>
                    <input class="form-control" id="userName" name="userName" placeholder="아이디를 입력하세요" required="">
                    <input type="button" id="confirmId" value="중복 체크">
                    <span id="id_signed"></span>

                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="비밀번호는 4~15자 제한입니다" required=""
                           data-parsley-minlength="4" data-parsley-maxlength="15">


                </div>
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력하세요">

                </div>
                <button type="submit" class="btn btn-success clearfix pull-right">회원가입</button>
                <div class="clearfix" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>

</div>

<script src="/assets/js/jquery-3.2.0.min.js"></script>
<script src="/assets/js/parsley.js"></script>
<script>

    var instance = $('#userName').parsley();
    console.log(instance.isValid()); // maxlength is 42, so field is valid

</script>


</body>
</html>