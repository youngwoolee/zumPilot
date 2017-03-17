<!DOCTYPE HTML>
<html>

<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resource/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <form class="form-signin" action="/user/new" method="post">

        <h2 class="form-signin-heading">회원가입</h2>
        <input type="text" id="userId" class="form-control" name="userId" placeholder="아이디" required="" autofocus="">
        <input type="password" id="password" class="form-control" name="password" placeholder="비밀번호" required="">
        <input type="email" id="email" name="email" class="form-control" placeholder="이메일" required="">
        <button class="btn btn-lg btn-primary btn-block" type="submit">회원가입</button>
    </form>
</div>

<script src="webjars/jquery/2.2.3/dist/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/dist/js/bootstrap.min.js"></script>
</body>
</html>