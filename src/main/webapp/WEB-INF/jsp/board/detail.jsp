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
                <input type="hidden" name="boardId" value="${board.boardId}">
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
                <a id="board-delete" type="button" href="/board/delete/${board.boardId}">글 삭제</a>
            </c:if>


        </div>

        <h4>Leave a Comment:</h4>
        <form id="reply-insert" method="POST" role="form" onsubmit="return false;">
            <div class="form-group">
                <textarea id="content" name= "content" class="form-control" rows="3" required></textarea>
                <div class=remaining>남은 글자수: <span class="count">140</span></div>
            </div>
            <button type="submit" class="btn btn-success">Submit</button>
        </form>
        <br><br>

        <p>Comments:</p><br>


        <div id ="reply" class="row">
            <c:forEach items="${replyList}" var="reply" varStatus="status">
                <div id='reply${reply.replyId}' class='col-sm-10' style = 'padding-left: ${reply.depth*20}px'
                     isopen = 'false' ismodify = 'false' value = ${reply.replyId}>
                    <h4 class='info' value = ${reply.writer.userName}> ${reply.writer.userName}
                <small> ${reply.regDate}</small>
                        <a id = '${reply.replyId}' class= 'reply-write-form' type ='button' href='#'><small> 답글</small></a>

                        <c:if test = "${reply.writer.userName == auth}">
                            <a id = 'modify${reply.replyId}' class= 'reply-modify-form' type='button' href='#' value = ${reply.replyId}><small> 수정</small></a>
                            <a id = 'delete${reply.replyId}' class= 'reply-delete' type='button' href='#' value = ${reply.replyId}><small> 삭제</small></a>
                        </c:if>
                    </h4>
                <p id = 'reply-content-${reply.replyId}'>${reply.content}</p></div>
            </c:forEach>

        </div>

        <script src="/assets/js/jquery-3.2.0.min.js"></script>
        <script type="text/javascript" src="/assets/js/numberOfFontCheck.js"></script>
        <script type="text/javascript" src="/assets/js/replyAction.js"></script>
        <script type="text/javascript">

            $(document).on("click", "#board-delete", function (e) {
                e.preventDefault();


                $.ajax({
                    url: '/board/delete/${board.boardId}',
                    dataType: 'json',
                    type: 'get',
                    success: function(result){
                        alert("삭제 성공!!");
                        window.location.href = result.url;
                    }
                });


            });


        </script>



    </div>

</body>

</html>