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
            <form name="userInfo" method="post" action="/user/">
                <div class="form-group">
                    <label for="userName">사용자 아이디</label>
                    <input class="form-control" id="userName" name="userName" placeholder="User ID">
                    <input type="button" id="confirmId" value="중복 체크">
                    <span id="id_signed"></span>
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

<script src="/assets/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript">

    $(function() {

        //아이디 중복 체크 실행 여부(0:중복체크X 1:중복체크O)
        var count = 0;

        $('#confirmId').click(function() {
            var userName = $('#userName').val();
            if (userName == '') {
                alert('아이디를 입력하세요!');
                $('#userName').focus();
                return;
            }

            $.ajax({

                url: "/user/isDuplicate",
                type : "post",
                data : "userName=" + userName,
                dataType : "json",


                success: function (data) {

                    if(data == true) {
                        count = 0;
                        $('#id_signed').html('이미 등록된 아이디').css('color','red');
                        $('#userName').val('').focus();
                    }
                    else {
                        count=1;
                        console.log("success: " + data);
                        $('#id_signed').html('사용 가능한 아이디').css('color','black');
                    }


                },
                error: function (jqXHR, status, err) {
                    console.log(jqXHR.responseText);
                }

            });

        });

        $('#userName').keyup(function(){
            count = 0;
            $('#id_signed').html('');
        });

        $("[name='userInfo']").submit(function(){
            if(count == 0){
                alert('아이디 중복체크 필수');
                if($('#userName').val() == '') {
                    $('#userName').focus();
                }else {
                    $('#confirmId').focus();
                }
                return false;
            }else {
                alert('전송');
            }
        });


    });



</script>

</body>
</html>