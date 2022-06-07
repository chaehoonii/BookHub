<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>BookHub : 로그인</title>
	<link rel="stylesheet" href="/static/css/user/user_login.css" />
</head>

<body>
	<div id="wrap" class="highlight">
		<div class="container">
			<c:if test="${not empty register}">
				<span class="registrations" style="text-align: center; width: 120px;">
					회원가입 완료
				</span>
			</c:if>
			<div class="item">
				<a href="/oauth2/authorization/google" class="btn btn-secondary " style="color: white">가입 | 로그인</a>
			</div>
		</div>
	</div>
</body>

</html>