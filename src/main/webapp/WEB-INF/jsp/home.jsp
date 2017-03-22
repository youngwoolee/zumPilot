<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ page session="false" %>

<!DOCTYPE html>
<html>
<body>
<h2>줌 인터넷 게시판</h2>
<hr>


<sec:authorize access="isAnonymous()">

    <a href="<c:url value="/login" />">로그인</a><br>
    <a href="<c:url value="/registerForm" />">회원가입</a><br>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <div>${message}</div>
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
        <input type="submit" value="logout"/>
    </form:form>
    <c:import url="board.jsp" charEncoding="UTF-8"/>
</sec:authorize>









</body>


</html>