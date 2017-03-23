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
            <form method="POST" action="/board/write">
                <div class="form-group">
                    <label>제목</label>
                    <input type="text" name="title" class="form-control">
                </div>
                <div class="form-group">
                    <label>작성자</label>
                    <input type="text" name="writer" class="form-control" value="${auth}" readonly >
                </div>
                <div class="form-group">
                    <label>내용</label>
                    <textarea name="content" class="form-control" rows="5"></textarea>
                </div>

                <a type="button" >이미지 올리기</a>
                <button type="submit" class="btn btn-success clearfix pull-right">작성</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>

</body>

</html>