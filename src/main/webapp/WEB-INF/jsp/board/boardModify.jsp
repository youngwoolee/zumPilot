<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>
        div[contenteditable] {
            padding:10px;
            border:1px solid #555555;
            background-color: #ffffff;
            min-height: 50px;
        }
    </style>

</head>
<body>

    <c:import url="../include/navigation.jsp"/>

    <div class="container">

        <div style="padding : 30px;">
            <form method="POST" action="/board/modify" enctype="multipart/form-data" >
                <input type="hidden" name="boardId" value="${board.boardId}">
                <div class="form-group">
                    <label>제목</label>
                    <input type="text" name="title" class="form-control" id = "title" value="${board.title}">
                </div>
                <div class="form-group">
                    <label>작성자</label>
                    <input type="text" name="writer" class="form-control" value="${board.userId.userName}" readonly >
                </div>
                <div class="form-group">
                    <label>내용</label>
                    <textarea name="content" class="form-control" rows="5" id ="content">${board.content}</textarea>

                </div>


                <c:if test = "${image != null}">
                    <div class="form-group">
                        <label>사진</label>
                        <img src="${image.fileName}"/>
                        <input type="text" value="${image.originName}">

                    </div>
                </c:if>
                <input type="file" name="upload">


                <button type="submit" class="btn btn-success clearfix pull-right">글 수정</button>

            </form>


            <script src="http://code.jquery.com/jquery-3.2.0.min.js"></script>

            <%--<script type="text/javascript">--%>

                <%--$(function(){ $("#btn").click(function(e){--%>
                    <%--e.preventDefault();--%>
                    <%--var formData = new FormData();--%>
                    <%--formData.append("test2", $("input[name=test2]").val());--%>
                    <%--formData.append("test3", $("textarea[name=test3]").text());--%>
                    <%--formData.append("test4", $("input[name=test4]")[0].files[0]);--%>

                    <%--$.ajax({--%>
                        <%--url: '/board/test',--%>
                        <%--data: formData,--%>
                        <%--processData: false,--%>
                        <%--contentType: false,--%>
                        <%--type: 'POST',--%>
                        <%--success: function(data){--%>
                            <%--alert($("input[name=test4]")[0].files[0]);--%>
<%--//                            $("input[name=test2]").val("aaa");--%>
<%--//                            var content = document.getElementById('content');--%>
<%--//                            content.value = content.value + "<img src = http://lego.zumst.com/resources/current/images/img_zum_logo_20161201.png />"--%>
                            <%--$("#content").html('<img id="newImg" src="" alt="your file"/>');--%>
                            <%--$("#newImg").attr("src","http://lego.zumst.com/resources/current/images/img_zum_logo_20161201.png");--%>



                        <%--}--%>
                    <%--});--%>
                <%--});--%>
                <%--});--%>

            <%--</script>--%>

        </div>
    </div>

</body>

</html>