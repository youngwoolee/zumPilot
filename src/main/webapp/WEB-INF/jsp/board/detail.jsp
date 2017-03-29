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
                <label> 제목 </label>
                <span>${board.title}</span>
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
                <span>${fn:replace(board.content, cn ,br)}</span>
            </div>

            <c:if test = "${board.userId.userName == auth}">
                <a type="button" href="/board/modifyForm/${board.boardId}">글 수정</a>
                <a type="button" href="#">글 삭제</a>
            </c:if>



        </div>

        <div id="reply">

        </div>

        <script src="http://code.jquery.com/jquery-3.2.0.min.js"></script>
        <script type="text/javascript" >
            var fetchList = function() {
                var data={}
                $.ajax({
                    url : "/board/${board.boardId}/reply",
                    type : "post",
                    contentType : "application/json",
                    data : JSON.stringify(data),
                    dataType : "json",

                    success: function (data, vo) {
                        console.log("success: " + data);
                        $.each(data, function (index, vo) {

                            $("#reply").append(renderHtml(vo));

                        })


                    },
                    error: function (e) {
                        console.log("error : " + e);
                    }

                });

            }

            var renderHtml = function( vo ) {
                var html =
                    "<li id='li-" + vo.replyId + "'><table><tr>" +
                    "<td>" + vo.writer + "</td>" +
                    "<td>" + vo.regDate  + "</td>" +
                    "<td><a href='#' class='a-del' data-no='"  +  vo.replyId + "'>삭제</a></td>" +
                    "</tr><tr>" +
                    "<td colspan='3'>" + vo.content.replace( /\r\n/g, "<br>").replace( /\n/g, "<br>") + "</td>" +
                    "</tr></table></li>";
                return html;
            }

            fetchList();

        </script>


        <div class="submit-write" method="post" action="#">
            <div class="form-group" style="padding:14px;">
                <textarea class="form-control" name="content"></textarea>
            </div>

            <input type="submit" class = "btn btn-success pull-right" value="답글쓰기"/>

            <div class="clearfix" />
        </div>


    </div>

</body>

</html>