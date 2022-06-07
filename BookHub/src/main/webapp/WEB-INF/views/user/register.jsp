<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>BookHub : 회원가입</title>
	<!-- Bootstrap WebJars -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/static/css/user/common_style.css">
	<link rel="stylesheet" href="/static/css/user/user_register_new.css">
</head>

<body>
	<div class="container">
		<h3>회원가입</h3>
		<hr>
		<br>
		<div class="container-page">
			<form id="change_url">
				<!-- 닉네임 수정 (2행)-->
				<div class="container-context container-nickname">
					<div class="lables-nickname">
						<h5 class="lables-text">닉네임 변경</h5>
					</div>
					<div class="buttons buttons-nickname">
						<input type=text name="userNick" id="userNick" value=${user.userNick}
							placeholder=${user.userNick} class="memberInfo">
						<input type="button" id="nick_btn" class="btn btn-outline-dark buttons" value="중복확인">
					</div>
				</div>
				<p id="nickappend"></p>
				<input type=hidden name="userId" id="userId" value=${user.userId}>
				<input type=hidden name="userRole" id="userRole" value=${user.userRole}>
				<!-- 변경하기 버튼 -->
				<div class="register-container update-comlete-container">
					<input type="button" id="register_btn" value="회원가입" class="btn btn-outline-dark item buttons">
				</div>
			</form>
		</div>
	</div>

	<script src="/static/js/user/user_register.js"></script>
</body>

</html>