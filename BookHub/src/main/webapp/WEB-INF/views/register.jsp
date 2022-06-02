<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>BookHub : 회원가입</title>
	<link rel="stylesheet" href="/static/css/user/user_register_new.css">
	<jsp:include page="libs/libsStyles.jsp" flush="false" />
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
	<br>
	<br>

	<jsp:include page="libs/libsScript.jsp" flush="false" />
	<script src="/static/js/user/user_register.js"></script>
</body>

</html>