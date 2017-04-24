$(function() {

    var boardId = $("#boardId").val();

    $("#boardDelete").click(function () {

        $.post("/board/delete/"+boardId, function (result) {
            alert("삭제 성공!!");
            window.location.href = result.url;
        }).fail(function(response) {
            alert('Error: ' + response.responseText);
        });

    });

    $("#boardModifyForm").submit(function (e) {
        e.preventDefault();

        var formData = new FormData(e.currentTarget);

        $.ajax({
            url: '/board/'+boardId,
            processData: false,
            contentType: false,
            dataType: 'json',
            data: formData,
            type: 'post',
            success: function(result){
                alert("수정 성공!!");
                window.location.href = result.url;
            }
        });

    });

});