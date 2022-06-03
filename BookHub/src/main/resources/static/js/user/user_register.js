let change_urlForm = document.querySelector("#change_url");
let ajax_check = "";

// ajax로 닉네임 중복확인
$("#nick_btn").on('click', function () {
	let userNick = $("#userNick").val();
	let userId = $("#userId").val();
	$.ajax({
		url: `/register/vali/${userId}?reqNick=${userNick}`,
		type: 'get',
		dataType: "json", // json으로 응답받기

		success: function (response) {
			/* 중복확인 결과  */
			if (response.result == "ok") {
				ajax_check = "ok";
				$("#nickappend").html('<p style = "color: #e6ffe6;">사용가능한 닉네임입니다.</p>');
				$("#change_url").attr("action", "/register/confirm");
				$("#change_url").attr("method", "post");
				$("#register_btn").attr("type", "submit")
			} else if (response.result == "blank") {
				$("#nickappend").html('<p style = "color: #ffe6e6;">변경할 닉네임을 입력해 주세요.</p>');
			} else {
				$("#nickappend").html('<p style = "color: #ffe6e6;">다른 사용자가 이미 사용중인 닉네임입니다.</p>');
			}
		} // success end 	
	}); // ajax end
});

// 닉네임, 이메일 확인 안되었으면 회원가입 불가 
$("#register_btn").on("click", function () {
	if (ajax_check == "") { // 회원가입 불가
		alert("닉네임 중복확인부터 진행해주세요.");
	}
});

document.querySelector('form').addEventListener('submit', function(e){
	e.preventDefault();
	swal('가입을 축하합니다🎉', '다시 로그인해주세요', 'success');
	//등록 성공하면 내가등록한 게시글화면으로 이동
	setTimeout(function () {
		change_urlForm.submit();
	}, 2000);
});