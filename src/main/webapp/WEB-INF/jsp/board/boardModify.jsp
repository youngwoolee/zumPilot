<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form" %>
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
            <form id="board-modify" action="" enctype="multipart/form-data" >
                <input type="hidden" name="boardId" value="${board.boardId}">
                <div class="form-group">
                    <label>제목</label>
                    <input type="text" name="title" class="form-control" id = "title" value="${board.title}">
                </div>
                <div class="form-group">
                    <label>작성자</label>
                    <br>
                    ${board.userId.userName}
                </div>
                <div class="form-group">
                    <label>내용</label>
                    <textarea name="content" class="form-control" rows="5" id ="content">${board.content}</textarea>
                    <div class=remaining>남은 글자수: <span class="count">10000</span></div>
                </div>


                <c:if test = "${image != null}">
                    <div class="form-group">
                        <label>사진</label>
                        <img src="${image.fileName}"/>
                        <input type="text" value="${image.originName}">

                    </div>
                </c:if>
                <input id= "upload" type="file" name="upload" accept=".gif, .jpg, .png">


                <button id="modify-button" type="reset" class="btn btn-success clearfix pull-right" OnClientClick="return false">글 수정</button>

            </form>


            <script src="/assets/js/jquery-3.2.0.min.js"></script>
            <script type="text/javascript" src="/assets/js/numberOfFontCheck.js"></script>

            <script type="text/javascript">

                $('#modify-button').on('click', function() {

                    var form = new FormData(document.getElementById('board-modify'));

                    $.ajax({
                        url: '/board/${board.boardId}',
                        processData: false,
                        contentType: false,
                        dataType: 'json',
                        data: form,
                        type: 'post',
                        success: function(result){
                            alert("수정 성공!!");
                            window.location.href = result.url;
                        }
                    });


                });


            </script>

        </div>
    </div>

</body>

</html>