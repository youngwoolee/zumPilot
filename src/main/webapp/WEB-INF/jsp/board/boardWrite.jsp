<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

    <c:import url="../include/navigation.jsp"/>

    <div class="container">

        <div style="padding : 30px;">
            <spring:hasBindErrors name="board"/>
            <form id="board-write" method="POST" action="/board/" enctype="multipart/form-data" onsubmit="return validateForm()">
                <div class="form-group">
                    <label>제목</label>
                    <input type="text" name="title" class="form-control" id = "title">
                    <form:errors path="board.title" cssClass="error" />

                </div>
                <div class="form-group">
                    <label>작성자</label> ${auth}
                </div>
                <div class="form-group">
                    <label>내용</label>

                    <textarea name="content" class="form-control" rows="5" id ="content" ></textarea>
                    <form:errors path="board.content" cssClass="error" />
                    <div class=remaining>남은 글자수: <span class="count">10000</span></div>

                </div>

                <input type="file" name="upload" accept=".gif, .jpg, .png">


                <button type="submit" class="btn btn-success clearfix pull-right">작성</button>

            </form>

        </div>
    </div>

    <script src="/assets/js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="/assets/js/numberOfFontCheck.js"></script>

    <script>
        function validateForm() {

            var title = document.forms["board-write"]["title"].value;
            var content = document.forms["board-write"]["content"].value;
            if(title == "") {
                alert("제목을 입력해주세요");
                return false;
            }
            if(title.length < 2) {
                alert("제목은 두글자 이상입니다");
                return false;
            }
            if(content == "") {
                alert("내용을 입력해주세요");
                return false;
            }

        }

    </script>

</body>

</html>