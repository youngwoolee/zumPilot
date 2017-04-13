$(function() {

    //아이디 중복 체크 실행 여부(0:중복체크X 1:중복체크O)
    var count = 0;

    $('#confirmId').click(function() {
        var userName = $('#userName').val();
        if (userName == '') {
            alert('아이디를 입력하세요!');
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
                    count = 0;
                    $('#id_signed').html('이미 등록된 아이디').css('color','red');
                    $('#userName').val('').focus();
                }
                else {
                    count=1;
                    console.log("success: " + data);
                    $('#id_signed').html('사용 가능한 아이디').css('color','black');
                }


            },
            error: function (jqXHR, status, err) {
                console.log(jqXHR.responseText);
            }

        });

    });

    $('#userName').keyup(function(){
        count = 0;
        $('#id_signed').html('');
    });

    $("[name='userInfo']").submit(function(){
        if(count == 0){
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