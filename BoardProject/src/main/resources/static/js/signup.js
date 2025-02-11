$(document).ready(function() {
    $('#checkIdButton').click(function() {
        var userId = $('input[name="user_id"]').val();
        if (userId) {
            $.ajax({
                url: '/check-id', // 중복 검사 요청을 보낼 URL
                type: 'GET',
                data: { userId: userId },
                success: function(response) {
                    if (response.exists) {
                        $('#idCheckResult').text('이미 사용 중인 아이디입니다.').css('color', 'red');
                    } else {
                        $('#idCheckResult').text('사용 가능한 아이디입니다.').css('color', 'green');
                    }
                },
                error: function() {
                    $('#idCheckResult').text('오류가 발생했습니다. 다시 시도해주세요.').css('color', 'red');
                }
            });
        } else {
            $('#idCheckResult').text('아이디를 입력해주세요.').css('color', 'red');
        }
    });
});
