let change_urlForm = document.querySelector("#change_url");
let ajax_check = "";
let userNick = $("#userNick").val();
let userId = $("#userId").val();
let valiAjax = undefined;


valiAjax = () => {
    return new Promise(function (resolve, reject) {
        $.ajax({
            url: `/register/vali/${userId}?reqNick=${userNick}`,
            type: 'get',
            dataType: "json", // json으로 응답받기

            success: function (response) {
                /* 중복확인 결과  */
                if (response.result == "ok") {
                    ajax_check = "ok";
                    $("#nickappend").html('<p class="enable">사용가능한 닉네임입니다.</p>');
                    $("#change_url").attr("action", "/register/confirm");
                    $("#change_url").attr("method", "post");
                    $("#register_btn").attr("type", "submit")
                    resolve(true);
                } else if (response.result == "blank") {
                    ajax_check = "blank";
                    $("#nickappend").html('<p class="disable">변경할 닉네임을 입력해 주세요.</p>');
                    resolve(false);
                } else {
                    ajax_check = "duplicated";
                    $("#nickappend").html('<p class="disable">다른 사용자가 이미 사용중인 닉네임입니다.</p>');
                    resolve(false);
                }
            } // success end 	
        }); // ajax end
    })
}
valiAjax();

// ajax로 닉네임 중복확인
$("#nick_btn").on('click', function () {
    userNick = $("#userNick").val();
    userId = $("#userId").val();
    valiAjax();
});

$("#update-complete").on('click', function () {
    userNick = $("#userNick").val();
    userId = $("#userId").val();
    valiAjax();
});

document.querySelector('form').addEventListener('submit', function (e) {
    e.preventDefault();
    userNick = $("#userNick").val();
    userId = $("#userId").val();

    valiAjax().then((e) => {
        if (e == true) {
            if (ajax_check == "ok") {
                swal({
                    title: `닉네임을 변경하시겠습니까?`,
                    text: "변경사항은 다시 로그인하셔야 반영됩니다",
                    icon: "info",
                    buttons: true,
                    dangerMode: true,
                }).then((e) => {
                    if (e) {
                        change_urlForm.submit();
                    }
                });
            }
        } else {
            if (ajax_check == "") { // 회원가입 불가
                swal('', '닉네임 중복확인부터 진행해주세요.', 'error');
            } else if (ajax_check == "blank") {
                swal('', '변경할 닉네임을 입력해 주세요.', 'error');
            } else if (ajax_check == "duplicated") {
                swal('', '다른 사용자가 이미 사용중인 닉네임입니다.', 'error');
            }
        }
    })
});