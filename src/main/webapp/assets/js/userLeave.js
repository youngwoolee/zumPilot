$(function() {

    var userId = $("#user-leave").attr("value");

    $("#user-leave").click(function () {

        $.post('/user/'+userId, function (result) {
            alert("탈퇴 성공!!");
            window.location.href = result.url;
        });

    });

});