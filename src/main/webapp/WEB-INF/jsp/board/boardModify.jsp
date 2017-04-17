<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form" %>
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

            <form data-parsley-validate="" id="board-modify" enctype="multipart/form-data">
                <input type="hidden" name="boardId" value="${board.boardId}">
                <div class="form-group">
                    <label>제목</label>
                    <input type="text" name="title" class="form-control" id = "title" value="${board.title}"
                           required="" data-parsley-minlength="2">

                </div>
                <div class="form-group">
                    <label>작성자</label>
                    <br>
                    ${board.userId.userName}
                </div>
                <div class="form-group">
                    <label>내용</label>
                    <textarea name="content" class="form-control" rows="5" id ="content"
                              required="" data-parsley-trigger="keyup"
                              data-parsley-minlength="1" data-parsley-maxlength="10000"
                              data-parsley-validation-threshold="10">${board.content}</textarea>

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


                <button id="modify-button" type="submit" class="btn btn-success clearfix pull-right">글 수정</button>

            </form>


            <script src="/assets/js/jquery-3.2.0.min.js"></script>
            <script type="text/javascript" src="/assets/js/numberOfFontCheck.js"></script>
            <script src="/assets/js/parsley.js"></script>
            <script src="/assets/js/boardModify.js"></script>

        </div>
    </div>

</body>

</html>