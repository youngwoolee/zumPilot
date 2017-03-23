<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/">줌인터넷 게시판</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">

            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="navbar navbar-default" id ="subnav">
    <div class ="col-md-12">
        <div class="collapse navbar-collapse" id="navbar-collapse2">
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <li class="active"><a href="/board/list">게시판</a></li>
                    <li><a href="/login" role="button">로그인</a></li>
                    <li><a href="/registerForm" role="button">회원가입</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">

                    <li><a href="/board/list">게시판</a></li>
                    <li><a href="/updateForm" role="button">개인정보수정</a></li>
                    <li>${message}</li>
                    <li><form:form action="${pageContext.request.contextPath}/logout" method="POST">
                        <input type="submit" value="logout"/>
                    </form:form>
                    </li>

                </sec:authorize>
            </ul>

        </div>
    </div>
</div>
