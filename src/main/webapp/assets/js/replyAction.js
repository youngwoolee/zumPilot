$(function() {

    var boardId = $("#boardId").val();
    var isDuplicateReplyForm = false;
    var selected = null;

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
    };

    var renderHtml = function( vo ) {

        if(vo.status == 0) {

            return "<div class='col-sm-10 reply' data-replyid='"+ vo.replyId + "' style = 'padding-left:" +
                20 * vo.depth + "px'><h4 class='info'>" + vo.writer.userName +
                "<small> " + formatTime(vo.regDate) + "</small></h4>" +
                "<p>삭제된 댓글입니다.</p></div>";
        }

        return "<div class='col-sm-10 reply' data-replyid='"+ vo.replyId + "' style = 'padding-left:" +
            20 * vo.depth + "px'><h4 class='info'>" + vo.writer.userName +
            "<small> " + formatTime(vo.regDate) +
            "</small><a class= 'replyWriteButton' href='javascript:;'><small> 답글</small></a>" +
            "<a class= 'replyModifyButton' href='javascript:;'><small> 수정</small></a>" +
            "<a class= 'replyDelete' href='javascript:;'><small> 삭제</small></a></h4>" +
            "<p>" +vo.content.replace( /\r\n/g, "<br>").replace( /\n/g, "<br>") +
            "</p></div>";

    };

    var renderForm = function (content) {

        if(content == null) {
            return "<div class='answerForm replyForm'><textarea name= 'content' " +
                "class='form-control replyContent' rows='2' required>" +
                "</textarea><button class='btn btn-success writeButton'>Submit</button></div>";
        }

        return "<div class='answerForm replyForm'><textarea name= 'content' " +
            "class='form-control replyContent' rows='2' required>" + content +
            "</textarea><button class='btn btn-success modifyButton'>Submit</button></div>";
    }


    //댓글 쓰기
    $(document).on("click", ".writeButton", function() {
        var t = $(this);
        var Idx = t.index(".replyWrite");
        var content = $(".replyContent").eq(Idx).val();
        var parentId = t.parents(".reply").data("replyid");

        if(Idx != 0) {

            //답글
            $.post(" /board/"+boardId+"/answer/create", {content: content, parentId: parentId}, function (data) {
                t.closest(".reply").after(renderHtml(data));
                $(".answerForm").remove();
                isDuplicateReplyForm = false;

            });
            return;
        }

        //댓글
        $.post("/board/" + boardId + "/reply/create", {content: content}, function (data) {
            $("#replyDiv").prepend(renderHtml(data));
            $(".replyContent").eq(Idx).val('');
        });

    });

    //답글 수정
    $(document).on("click", ".modifyButton", function () {

        var t = $(this);
        var Idx = t.index(".replyWrite");
        var content = $(".replyContent").eq(Idx).val();
        var parentId = t.parents(".reply").data("replyid");


        //수정
        $.post("/board/" + boardId + "/answerModify", {content: content, replyId: parentId}, function (data) {
            t.closest(".reply").replaceWith(renderHtml(data));
            $(".answerForm").remove();
            isDuplicateReplyForm = false;
        });

    });

    //댓글 삭제
    $(document).on("click", ".replyDelete", function () {

        var Idx = $(this).parents(".reply").prevAll().length;
        var replyId = $(".reply").eq(Idx).data("replyid");

        $.post("/board/"+boardId+"/answerDelete", {replyId: replyId}, function (data) {

            $(".reply").eq(Idx).replaceWith(renderHtml(data));

        });


    });

    //댓글 답글 폼
    $(document).on("click", ".replyWriteButton", function () {

        var t = $(this),
            Idx = t.parents(".reply").index(".reply"),
            currentReply = $(".reply").eq(Idx);

        if(isDuplicateReplyForm) {
            $(".answerForm").remove();
            if(selected == Idx){
                selected = null;
                return;
            }
        }
        currentReply.append(renderForm());
        isDuplicateReplyForm = true;
        selected = Idx;

    });

    //댓글 수정 폼
    $(document).on("click", ".replyModifyButton", function () {

        var t = $(this),
            replyForm = t.parent().siblings(".replyForm"),
            Idx = t.parents(".reply").index(".reply"),
            currentReply = $(".reply").eq(Idx),
            content = currentReply.children("p").text();


        if(isDuplicateReplyForm) {
            $(".answerForm").remove();
            if(selected == Idx){
                selected = null;
                return;
            }
        }
        currentReply.append(renderForm(content));
        isDuplicateReplyForm = true;
        selected = Idx;

    });

});