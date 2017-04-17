$(function() {

    var boardId = $("[name='boardId']").val();

    $(document).on("click", "#board-delete", function (e) {
        e.preventDefault();


        $.ajax({
            url: '/board/delete/'+boardId,
            dataType: 'json',
            type: 'get',
            success: function(result){
                alert("삭제 성공!!");
                window.location.href = result.url;
            }
        });


    });

});