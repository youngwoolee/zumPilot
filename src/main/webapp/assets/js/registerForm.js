$(function() {

    var isChecked = false;

    $('#confirmId').click(function() {
        var userName = $('#userName').val().trim();
        if (userName == '') {
            alert('아이디를 입력하세요!');
            $('#userName').focus();
            return;
        }
        if (userName.length < 2 || userName.length > 10) {
            alert('2자에서 10자 제한입니다!');
            $('#userName').focus();
            return;
        }

        $.ajax({

            url: "/user/isDuplicate",
            type : "post",
            data : "userName=" + userName,
            dataType : "json",


            success: function (data) {

                if(data == true) {
                    isChecked = false;
                    $('#id_signed').html('이미 등록된 아이디').css('color','red');
                    $('#userName').val('').focus();
                }
                else {
                    isChecked=true;
                    $('#id_signed').html('사용 가능한 아이디').css('color','black');
                }
            },
            error: function (jqXHR, status, err) {
            }

        });

    });

    $('#userName').keyup(function(){
        isChecked = false;
        $('#id_signed').html('');
    });

    $("[name='userInfo']").submit(function(){
        if(isChecked == false){
            alert('아이디 중복체크 필수');
            if($('#userName').val() == '') {
                $('#userName').focus();
            }else {
                $('#confirmId').focus();
            }
            return false;
        }

    });


});