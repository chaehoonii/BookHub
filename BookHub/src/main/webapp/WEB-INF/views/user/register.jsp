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
				<div class="item">닉네임 
					<input type=text name="userNick" id="userNick" value=${user.userNick} placeholder=${user.userNick} class="memberInfo"> 
					<input type="button" id="nick_btn" class="btn btn-primary buttons" value="중복확인">
					<p id="nickappend"></p>
				</div>
				<br> 
				<input type = hidden name = "userId" id = "userId" value = ${user.userId}>
 				<input type = hidden name = "userRole" id = "userRole" value = ${user.userRole}>
				<div class="register-container">
					<input type="button" id="register_btn" value="회원가입" class="btn btn-primary item buttons">
				</div>
			</form>
		</div>
	</div>

	<script src="/static/js/user/user_register.js"></script>
</body>

</html>