<!DOCTYPE html>
<html>
    <head>
        <#include '/layout/header.ftl'>
    </head>
    <body>
        <h1>Hello world.</h1>

        <p>request message : ${RequestParameters['msg']?if_exists}(${message?if_exists})</p>
        <a href="?msg=안녕하세요">안녕하세요</a>
        <a href="/intro">다른 페이지</a>
    </body>
</html>