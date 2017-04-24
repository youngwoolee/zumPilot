$(function() {

    var boardId = $("#boardId").val();
    var isDuplicateReplyForm = false;
    var selected = null;

    var templateList = Handlebars.templates.replyList;
    var templateForm = Handlebars.templates.replyForm;

    Handlebars.registerHelper('multiple', function(context) {
        return context*20;
    });

    Handlebars.registerHelper('formatTime', function(context) {
        return formatTime(context);
    });

    var formatTime = function( timestamp ) {
        var date = new Date(timestamp);
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDay();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var seconds = date.getSeconds();
        var formattedTime = year+'-'+month+'-'+day+' '+hour + ':' + minute + ':' + seconds;
        return formattedTime;
    };

    var isDuplicateReplyFormFunc = function (isDuplicateReplyForm, Idx) {
        if(isDuplicateReplyForm) {
            $(".answerForm").remove();
            if(selected == Idx){
                selected = null;
                return;
            }
        }
    }

    //댓글 쓰기
    $(document).on("click", ".writeButton", function() {
        var t = $(this),
            Idx = t.parents(".replyForm").index(".replyForm"),
            content = $(".replyContent").eq(Idx).val(),
            parentId = t.parents(".reply").data("replyid");

        if(Idx != 0) {

            //답글
            $.post(" /board/"+boardId+"/answer/create", {content: content, parentId: parentId}, function (data) {

                t.closest(".reply").after(templateList(data));
                $(".answerForm").remove();
                isDuplicateReplyForm = false;

            });
            return;
        }

        //댓글
        $.post("/board/" + boardId + "/reply/create", {content: content}, function (data) {

            $("#replyDiv").prepend(templateList(data));
            $(".replyContent").eq(Idx).val('');
        });

    });

    //답글 수정
    $(document).on("click", ".modifyButton", function () {

        var t = $(this),
            Idx = t.index(".replyWrite"),
            content = $(".replyContent").eq(Idx).val(),
            parentId = t.parents(".reply").data("replyid");

        //수정
        $.post("/board/" + boardId + "/answerModify", {content: content, replyId: parentId}, function (data) {
            t.closest(".reply").replaceWith(templateList(data));
            $(".answerForm").remove();
            isDuplicateReplyForm = false;
        });

    });

    //댓글 삭제
    $(document).on("click", ".replyDelete", function () {

        var Idx = $(this).parents(".reply").index(".reply"),
            currentReply = $(".reply").eq(Idx),
            replyId = currentReply.data("replyid");

        $.post("/board/"+boardId+"/answerDelete", {replyId: replyId}, function (data) {

            currentReply.replaceWith(templateList(data));

        });


    });

    //댓글 답글 폼
    $(document).on("click", ".replyWriteButton", function () {

        var t = $(this),
            Idx = t.parents(".reply").index(".reply"),
            currentReply = $(".reply").eq(Idx);

        isDuplicateReplyFormFunc(isDuplicateReplyForm, Idx);


        currentReply.append(templateForm());
        isDuplicateReplyForm = true;
        selected = Idx;

    });

    //댓글 수정 폼
    $(document).on("click", ".replyModifyButton", function () {

        var t = $(this),
            replyForm = t.parent().siblings(".replyForm"),
            Idx = t.parents(".reply").index(".reply"),
            currentReply = $(".reply").eq(Idx),
            content = currentReply.children("p").text(),
            data = {content : content};

        isDuplicateReplyFormFunc(isDuplicateReplyForm, Idx);

        currentReply.append(templateForm(data));
        isDuplicateReplyForm = true;
        selected = Idx;

    });

});