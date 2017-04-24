<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">


</head>
<body>

<c:import url="include/navigation.jsp"/>

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
        <c:forEach items="${boardList.content}" var="board" varStatus="status">

            <tr>
                <td>${pageInfo.totalElement - pageInfo.pageSize * (pageInfo.pno-1) - status.index}</td>
                <td><a href="/board/${board.boardId}" >${board.title } </a></td>
                <td>${board.userId.userName }</td>
                <td><fmt:formatDate value="${board.regDate}" pattern="MM/ dd" />
                </td>
                <td>${board.hit }</td>
            </tr>

        </c:forEach>

        </tbody>
    </table>

    <hr/>
    <a class="btn btn-default pull-right" href="/board/new">글쓰기</a>

    <div class="text-center">
        <ul class="pagination">
            <c:if test="${pageInfo.begin < pageInfo.pno}">
            <li class="next">
                <a href="/board/?pno=${pageInfo.first}">&laquo;</a>
            </li>
            </c:if>
            <c:if test="${pageInfo.begin >  pageInfo.currentBlock}">
                <li class="previous">
                    <a href="/board/?pno=${pageInfo.end - pageInfo.maxPager}">&lsaquo;</a>
                </li>
            </c:if>
            <c:forEach begin="${pageInfo.begin}" end="${pageInfo.end}" var="i" step="1">
                <c:choose>
                    <c:when test="${pageInfo.pno == i}">
                        <li class="active"><a href="/board/?pno=${i}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/board/?pno=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${pageInfo.end <  pageInfo.totalPage}">
                <li class="next">
                    <a href="/board/?pno=${pageInfo.begin + pageInfo.maxPager}">&rsaquo;</a>
                </li>
            </c:if>
            <c:if test="${pageInfo.end > pageInfo.pno}">
            <li class="next">
                <a href="/board/?pno=${pageInfo.totalPage}">&raquo;</a>
            </li>
            </c:if>
        </ul>
    </div>
</div>

</body>

</html>