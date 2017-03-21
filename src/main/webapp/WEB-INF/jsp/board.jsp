<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>welcome</title>
</head>
<body>
<div class = "container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
            <th>조회수</th>

        </tr>
        </thead>
        <tbody>
        <tr>
            <td>3</td>
            <td>제목입니다.</td>
            <td>홍길동</td>
            <td>2017.03.17</td>
            <td>1</td>

        </tr>
        <tr>
            <td>2</td>
            <td>줌인터넷</td>
            <td>둘리</td>
            <td>2017.03.17</td>
            <td>2</td>

        </tr>
        <tr>
            <td>1</td>
            <td>이스트소프트</td>
            <td>또치</td>
            <td>2017.03.17</td>
            <td>1</td>

        </tr>
        </tbody>

    </table>

    <hr/>
    <a class="btn btn-default pull-right">글쓰기</a>

    <div class="text-center">
        <ul class="pagination">
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
        </ul>
    </div>
</div>

</body>

</html>