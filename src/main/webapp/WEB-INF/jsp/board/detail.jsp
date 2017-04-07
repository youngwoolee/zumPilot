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
                <span><c:out escapeXml="true" value="${board.title}" /></span>
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
                <span><c:out escapeXml="true" value="${fn:replace(board.content, cn ,br)}" /></span>
            </div>
            <c:if test = "${image != null}">
                <div class="form-group">
                    <label> 사진 </label>
                    <img src="${image.fileName}"/>
                </div>
            </c:if>



            <c:if test = "${board.userId.userName == auth}">
                <a type="button" href="/board/${board.boardId}/edit">글 수정</a>
                <a type="button" href="/board/delete/${board.boardId}">글 삭제</a>
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

        </div>

        <script src="/assets/js/jquery-3.2.0.min.js"></script>
        <script type="text/javascript" src="/assets/js/numberOfFontCheck.js"></script>
        <script type="text/javascript">
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
                    20 * vo.depth + "px' isopen = 'false' ismodify = 'false' value = " + vo.replyId + "><h4 class='info' value = '" + vo.writer.userName +"'>" + vo.writer.userName +
                    "<small> " + formatTime(vo.regDate) +
                    "</small><a id = '" + vo.replyId + "' class= 'reply-write-form' type ='button' href='#'><small> 답글</small></a></h4>" +
                    "<p id = 'reply-content-"+ vo.replyId + "'>" +vo.content.replace( /\r\n/g, "<br>").replace( /\n/g, "<br>") +
                    "</p></div>";

                var authHtml =
                    "<div id='reply" + vo.replyId + "' class='col-sm-10' style = 'padding-left:" +
                    20 * vo.depth + "px' isopen = 'false' ismodify = 'false' value = " + vo.replyId + "><h4 class='info' value = '" + vo.writer.userName +"'>" + vo.writer.userName +
                    "<small> " + formatTime(vo.regDate) +
                    "</small><a id = '" + vo.replyId + "' class= 'reply-write-form' type ='button' href='#'><small> 답글</small></a>" +
                    "<a id = 'modify" + vo.replyId + "' class= 'reply-modify-form' type='button' href='#' value = '"+ vo.replyId +"'><small> 수정</small></a>" +
                    "<a id = 'delete" + vo.replyId + "' class= 'reply-delete' type='button' href='#' value = '"+ vo.replyId +"'><small> 삭제</small></a></h4>" +
                    "<p id = 'reply-content-"+ vo.replyId + "'>" +vo.content.replace( /\r\n/g, "<br>").replace( /\n/g, "<br>") +
                    "</p></div>";

                var deleteHtml =
                    "<div id='reply" + vo.replyId + "' class='col-sm-10' style = 'padding-left:" +
                    20 * vo.depth + "px' isopen = 'false' ismodify = 'false' value = " + vo.replyId + "><h4 class='info' value = '" + vo.writer.userName +"'>" + vo.writer.userName +
                    "<small> " + formatTime(vo.regDate) +
                    "<p id = 'reply-content-"+ vo.replyId + "'>삭제된 댓글입니다.</p></div>";

                if(vo.status == 0) {
                    return deleteHtml;
                }


                if('${auth}' != vo.writer.userName) {
                    return html;
                }
                else {
                    return authHtml;
                }

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


            //댓글 답글 폼
            $(document).on("click", ".reply-write-form", function (event) {

                event.preventDefault();


                var id = $(this).attr('id');


                var replyForm = "<form id = 'answer-insert' method='POST' role='form' onsubmit='return false;'>" +
                    "<div class='form-group'><textarea id='content-answer"+id+"' name= 'content' class='form-control' rows='2' required>" +
                    "</textarea></div><button type='submit' class='btn btn-success'>Submit</button></form>";
                console.log(id);


                if($("#reply"+id).attr("isopen") == "false") {

                    $("#reply"+id).append(replyForm);
                    $("#reply"+id).attr("isopen",'true');

                    if($("#reply"+id).attr("ismodify") == "true") {
                        $("#reply"+id).children("#answer-modify").remove();
                        $("#reply"+id).attr("ismodify",'false');
                    }

                }
                else {
                    $("#reply"+id).children("#answer-insert").remove();
                    $("#reply"+id).attr("isopen",'false');
                }

            });

            //댓글 수정 폼
            $(document).on("click", ".reply-modify-form", function (event) {

                event.preventDefault();


                var id = $(this).attr('value');
                var content = $("#reply-content-"+id).text();

                var modifyForm = "<form id = 'answer-modify' method='POST' role='form' onsubmit='return false;'>" +
                    "<div class='form-group'><textarea id='content-answer-modify"+id+"' name= 'content' class='form-control' rows='2' required>" +
                    content + "</textarea></div><button type='submit' class='btn btn-success'>수정</button></form>";

                console.log(content);

                if($("#reply"+id).attr("ismodify") == "false") {

                    $("#reply"+id).append(modifyForm);
                    $("#reply"+id).attr("ismodify",'true');

                    if($("#reply"+id).attr("isopen") == "true") {
                        $("#reply"+id).children("#answer-insert").remove();
                        $("#reply"+id).attr("isopen",'false');
                    }
                }
                else {
                    $("#reply"+id).children("#answer-modify").remove();
                    $("#reply"+id).attr("ismodify",'false');
                }

            });


            //답글 작성
            $(document).on("submit", "#answer-insert", function (event) {

                var parentId = $(this).parent().attr("value");
                var content = $("#content-answer"+parentId).val();

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
                        $("#reply"+parentId).children("#answer-insert").remove();
                        $("#reply"+parentId).attr("isopen",'false');
                        $("#reply"+parentId).after(renderHtml(data));

                    },
                    error: function (jqXHR, status, err) {
                        console.log(jqXHR.responseText);
                    }

                });

            });

            //답글 수정
            $(document).on("submit", "#answer-modify", function (event) {

                console.log("수정");
                var replyId = $(this).parent().attr("value");
                var content = $("#content-answer-modify"+replyId).val();
                this.reset();

                console.log(content);
                console.log(replyId);

                $.ajax({

                    url: "/board/${board.boardId}/answerModify",
                    type : "post",
                    data : "content=" + content +
                    "&replyId=" + replyId,
                    dataType : "json",

                    success: function (data) {

                        $("#reply"+replyId).children("#answer-modify").remove();
                        $("#reply"+replyId).attr("ismodify",'false');
                        $("#reply"+replyId).replaceWith(renderHtml(data));

                    },

                    error: function (jqXHR, status, err) {
                        console.log(jqXHR.responseText);
                    }

                });

            });


            //댓글 삭제
            $(document).on("click", ".reply-delete", function (event) {

                event.preventDefault();
                var replyId = $(this).attr("value");

                console.log("삭제");
                console.log(replyId);

                $.ajax({

                    url: "/board/${board.boardId}/answerDelete",
                    type : "post",
                    data : "replyId=" + replyId,
                    dataType : "json",

                    success: function (data) {

                        console.log(data);
                        $("#reply"+replyId).replaceWith(renderHtml(data));

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