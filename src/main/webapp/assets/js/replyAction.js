$(function() {

    var boardId = $("[name='boardId']").val();
    var authName = $("[name='auth']").val();

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

        console.log(boardId);

        $.ajax({
            url : "/board/"+boardId+"/reply",
            type : "post",
            contentType : "application/json",
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


        if(authName != vo.writer.userName) {

            return html;
        }
        else {

            return authHtml;
        }

    }

    
    //댓글 쓰기
    $(document).on("submit", "#reply-insert", function (event) {


        event.preventDefault();
        var content =$("#content").val();

        this.reset();
        $.ajax({

            url: "/board/"+ boardId +"/reply/create",
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

            url: "/board/"+boardId+"/answer/create",
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

            url: "/board/"+boardId+"/answerModify",
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

            url: "/board/"+boardId+"/answerDelete",
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

});