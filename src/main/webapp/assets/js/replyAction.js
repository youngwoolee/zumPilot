$(function() {

    var boardId = $("#boardId").val();
    var isDuplicateReplyForm = false;
    var selected = null;

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
    $(document).on("click", ".replyWriteSubmit", function() {
        var $this = $(this),
            replyTextArea = $this.siblings(".replyContent"),
            content = replyTextArea.val();

        //댓글
        $.post("/board/" + boardId + "/reply/create", {content: content}, function (data) {

            $("#replyDiv").prepend(templateList(data));
            replyTextArea.val('');
        });

    });

    //답글 쓰기
    $(document).on("click", ".answerWriteSubmit", function() {
        var $this = $(this),
            content = $this.siblings(".replyContent").val(),
            parentId = $this.closest(".reply").data("replyid");

        //답글
        $.post(" /board/"+boardId+"/answer/create", {content: content, parentId: parentId}, function (data) {

            $this.closest(".reply").after(templateList(data));
            $(".answerForm").remove();
            isDuplicateReplyForm = false;

        });

    });

    //답글 수정
    $(document).on("click", ".modifyButton", function () {

        var $this = $(this),
            content = $this.siblings(".replyContent").val(),
            parent = $this.closest(".reply"),
            parentId = parent.data("replyid");

        //수정
        $.post("/board/" + boardId + "/answerModify", {content: content, replyId: parentId}, function (data) {
            parent.replaceWith(templateList(data));
            $(".answerForm").remove();
            isDuplicateReplyForm = false;
        });

    });

    //댓글 삭제
    $(document).on("click", ".replyDelete", function () {

        var $this = $(this),
            replyId = $this.closest(".reply").data("replyid"),
            currentReply = $("#reply-"+replyId);

        $.post("/board/"+boardId+"/answerDelete", {replyId: replyId}, function (data) {

            currentReply.replaceWith(templateList(data));

        });

    });

    //댓글 답글 폼
    $(document).on("click", ".replyWriteButton", function () {

        var $this = $(this),
            replyId = $this.closest(".reply").data("replyid"),
            data = {replyId : replyId};

        isDuplicateReplyFormFunc(isDuplicateReplyForm, replyId);

        $("#reply-"+replyId).append(templateForm(data));
        isDuplicateReplyForm = true;
        selected = replyId;
    });

    //댓글 수정 폼
    $(document).on("click", ".replyModifyButton", function () {

        var $this = $(this),
            replyId = $this.closest(".reply").data("replyid"),
            currentReply = $("#reply-"+replyId),
            content = currentReply.children("p").text(),
            data = {content : content, replyId : replyId};

        isDuplicateReplyFormFunc(isDuplicateReplyForm, replyId);

        currentReply.append(templateForm(data));
        isDuplicateReplyForm = true;
        selected = replyId;

    });

});
