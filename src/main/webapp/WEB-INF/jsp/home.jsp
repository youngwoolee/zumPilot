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
    <h2>줌 인터넷 게시판</h2>
    <hr>


    <div>message : ${message}</div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        welcome ${pageContext.request.userPrincipal.name} !<br>
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="logout"/>
        </form:form>
    </c:if>

    <hr>

    <c:if test="${pageContext.request.userPrincipal.name == null}" >
        <a href="<c:url value="/login" />">login</a><br>
    </c:if>

    <c:if test="${pageContext.request.userPrincipal.name != null}" >
<<<<<<< HEAD
        <p>게시판</p>
=======
        <jsp:include page="/inc/common.jsp" flush="true" />
>>>>>>> 1435acc497e4c0c97a996cfb9051debca566c958
    </c:if>

</body>

</html>