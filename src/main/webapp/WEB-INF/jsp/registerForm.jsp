<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body>
<div class="container">
    <form class="form-signin" action="/register" method="post">

        <h2 class="form-signin-heading">회원가입</h2>
        <input type="text" id="userName" class="form-control" name="userName" placeholder="아이디" required="" autofocus="">
        <input type="password" id="password" class="form-control" name="password" placeholder="비밀번호" required="">
        <input type="email" id="email" name="email" class="form-control" placeholder="이메일" required="">
        <button class="btn btn-lg btn-primary btn-block" type="submit">회원가입</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>

</body>
</html>