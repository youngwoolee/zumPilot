<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>

<%
    pageContext.setAttribute("br", "<br/>");
    pageContext.setAttribute("cn", "\n");
%>

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
            <div class="form-group">
                <input id="boardId" type="hidden" name="boardId" value="${board.boardId}">
                <input type="hidden" name="auth" value="${auth}">
                <label> 제목 </label>
                <span><c:out value = "${board.title}" escapeXml="false"/></span>
            </div>
            <div class="form-group">
                <label> 작성자 </label>
                <span>${board.userId.userName}</span>
            </div>
            <div class="form-group">
                <label> 작성날짜 </label>
                <span>${board.regDate}</span>
            </div>
            <div class="form-group">
                <label> 조회수 </label>
                <span>${board.hit}</span>
            </div>
            <div class="form-group">
                <label> 내용 </label>
                <span><c:out value = "${fn:replace(board.content, cn ,br)}" escapeXml="false"/></span>
            </div>
            <c:if test = "${image != null}">
                <div class="form-group">
                    <label> 사진 </label>
                    <img src="${image.fileName}"/>
                </div>
            </c:if>



            <c:if test = "${board.userId.userName == auth}">
                <a type="button" href="/board/${board.boardId}/edit">글 수정</a>
                <a id="board-delete" type="button" href="javascript:;">글 삭제</a>
            </c:if>


        </div>

        <h4>Leave a Comment:</h4>
            <div class="replyForm">
                <textarea name= "content" class="form-control replyContent" rows="3" required></textarea>
                <div class=remaining>남은 글자수: <span class="count">140</span></div>
                <button class="btn btn-success writeButton">Submit</button>
            </div>

        <br><br>

        <div id ="replyDiv" class="row">
            <c:forEach items="${replyList}" var="reply" varStatus="status">
                <div class='col-sm-10 reply' data-replyid="${reply.replyId}" style = 'padding-left: ${reply.depth*20}px'>
                    <h4 class='info'> ${reply.writer.userName}
                        <small> ${reply.regDate}</small>
                        <c:if test="${reply.status == 1}">

                            <a class= 'replyWriteButton' href='javascript:;'><small> 답글</small></a>
                            <c:if test = "${reply.writer.userName == auth}">
                                <a class= 'replyModifyButton' href='javascript:;'><small> 수정</small></a>
                                <a class= 'replyDelete' href='javascript:;'><small> 삭제</small></a>
                            </c:if>
                        </c:if>

                    </h4>

                    <c:choose>
                        <c:when test="${reply.status == 0}">
                            <p>삭제된 댓글입니다.</p>
                        </c:when>
                        <c:otherwise>
                            <p>${fn:replace(reply.content, cn ,br)}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:forEach>
        </div>

        <script src="/assets/js/jquery-3.2.0.min.js"></script>
        <script type="text/javascript" src="/assets/js/numberOfFontCheck.js"></script>
        <script type="text/javascript" src="/assets/js/replyAction.js"></script>
        <script src="/assets/js/boardDelete.js"></script>

        <script type="text/javascript" src="/assets/js/handlebars-v4.0.5.js"></script>
        <script type="text/javascript" src="/assets/js/boardDetail.js"></script>



    </div>

</body>

</html>