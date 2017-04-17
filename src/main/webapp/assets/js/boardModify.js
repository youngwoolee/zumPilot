$(function() {

    var boardId = $("[name='boardId']").val();

    $(document).on("submit", "#board-modify", function (e) {
        e.preventDefault();
        var form = new FormData(document.getElementById('board-modify'));

        $.ajax({
            url: '/board/'+boardId,
            processData: false,
            contentType: false,
            dataType: 'json',
            data: form,
            type: 'post',
            success: function(result){
                alert("수정 성공!!");
                window.location.href = result.url;
            }
        });

    });

});