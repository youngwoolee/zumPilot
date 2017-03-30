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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
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

        <h4>Leave a Comment:</h4>
        <form id="reply-insert" method="POST" role="form" onsubmit="return false;">
            <div class="form-group">
                <textarea id="content" name= "content" class="form-control" rows="3" required></textarea>
            </div>
            <button type="submit" class="btn btn-success">Submit</button>
        </form>
        <br><br>

        <p><span class="badge">99</span> Comments:</p><br>

        <div id ="reply" class="row">

                    <%--<div class="col-xs-10">--%>
                        <%--<h4>Nested Bro <small>Sep 25, 2015, 8:28 PM</small></h4>--%>
                        <%--<p>Me too! WOW!</p>--%>
                        <%--<br>--%>
                    <%--</div>--%>

        </div>



        <%--<script src="http://code.jquery.com/jquery-3.2.0.min.js"></script>--%>
        <script type="text/javascript" >

            var formatTime = function( timestamp ) {
                var date = new Date(timestamp);
                var year = date.getFullYear();
                var month = date.getMonth()+1;
                var day = date.getDay();
                var hour = date.getHours();
                var minute = date.getMinutes();
                var seconds = date.getSeconds();
                var formattedTime = year+'.'+month+'.'+day+' '+hour + ':' + minute + ':' + seconds;
                return formattedTime;
            }

            var fetchList = function() {
                $.ajax({
                    url : "/board/${board.boardId}/reply",
                    type : "post",
                    contentType : "application/json",
//                    data : JSON.stringify(data),
                    dataType : "json",

                    success: function (data, vo) {
                        console.log("success: " + data);

                        $.each(data, function (index, vo) {

                            $("#reply").append(renderHtml(vo));

                        })


                    },
                    error: function (jqXHR, status, err) {
                        console.log(jqXHR.responseText);
                    }

                });

            }

            var renderHtml = function( vo ) {
                var html =
                    "<div id='reply" + vo.replyId + "' class='col-sm-10' style = 'padding-left:" +
                    20 * vo.depth + "px' isopen = 'false' value = " + vo.replyId + "><h4>" + vo.writer.userName +
                    "<small> " + formatTime(vo.regDate) +
                    "</small><a id = '" + vo.replyId + "' class= 'reply-write-form' type ='button' href='#'><small> 답글</small></a>" +
                    "<a type='button' href='#'><small> 수정</small></a>" +
                    "<a type='button' href='#'><small> 삭제</small></a></h4>" +
                    "<p> " + vo.content.replace( /\r\n/g, "<br>").replace( /\n/g, "<br>") +
                    "</p></div>";


                return html;
            }

            $("#reply-insert").submit(function(event) {

                event.preventDefault();
                var content =$("#content").val();

                this.reset();
                $.ajax({

                    url: "/board/${board.boardId}/replyWrite",
                    type : "post",
                    data : "content=" + content,
                    dataType : "json",


                    success: function (data) {
                        console.log("success: " + data);
                        $("#reply").prepend(renderHtml(data));

                    },
                    error: function (jqXHR, status, err) {
                        console.log(jqXHR.responseText);
                    }

                });

            });



            $(document).on("click", ".reply-write-form", function (event) {

                event.preventDefault();


                var id = $(this).attr('id');


                var replyForm = "<form id = 'answer-insert' method='POST' role='form' onsubmit='return false;'>" +
                    "<div class='form-group'><textarea id='content-answer' name= 'content' class='form-control' rows='2' required>" +
                    "</textarea></div><button type='submit' class='btn btn-success'>Submit</button></form>";
                console.log(id);


                if($("#reply"+id).attr("isopen") == "false") {

                    $("#reply"+id).append(replyForm);
                    $("#reply"+id).attr("isopen",'true');
                }
                else {
                    $("#reply"+id).children("#answer-insert").remove();
                    $("#reply"+id).attr("isopen",'false');
                }

            });

            $(document).on("submit", "#answer-insert", function (event) {

                var content = $("#content-answer").val();
                var parentId = $(this).parent().attr("value");

                this.reset();

                console.log(content);
                console.log(parentId);

                $.ajax({

                    url: "/board/${board.boardId}/answerWrite",
                    type : "post",
                    data : "content=" + content +
                            "&parentId=" + parentId,
                    dataType : "json",



                    success: function (data) {
                        console.log("success: " + data);


                    },
                    error: function (jqXHR, status, err) {
                        console.log(jqXHR.responseText);
                    }

                });

            });

            fetchList();

        </script>



    </div>

</body>

</html>