$(function() {

    var userId = $("#user-leave").attr("value");

    $(document).on("click", "#user-leave", function (e) {
        e.preventDefault();

        $.ajax({
            url: '/user/'+userId,
            dataType: 'json',
            type: 'get',
            success: function(result){
                alert("탈퇴 성공!!");
                window.location.href = result.url;
            }
        });
    });

});