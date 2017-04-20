$(function() {
    $('.remaining').each(function() {
        var $count = $('.count', this);
        var $input = $(this).prev();
        var maximumCount = $count.text() * 1;
        var update = function() {
            var before = $count.text() * 1;
            var now = maximumCount - $input.val().length;
            if (now < 0) {
                var str = $input.val();
                alert('글자 입력수가 초과하였습니다.');
                $input.val(str.substr(0, maximumCount));
                now = 0;
            }
            if (before != now) {
                $count.text(now);
            }
        };
        $input.on('input keyup paste', function() {
            setTimeout(update, 0);
        });

        update();
    });
});