<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>BookHub | Settings</title>
	<link rel="stylesheet" href="/static/css/user/common_style.css">
	<link rel="stylesheet" href="/static/css/user/settings-withdraw.css" />
	<link rel="stylesheet" href="/static/css/user/settings-asidebar.css" />
</head>

<body>
	<section class="container">
		<%-- <jsp:include page="./settings-asidebar.jsp" flush="false" /> --%>
		<!-- 어사이드바 컨테이너 -->
		<aside class="aside-container">
			<a class="content" href="/settings">
				<div class="aside-tab-item">
					<p class="tab-text">회원정보 수정</p>
				</div>
			</a><br>
			<a class="content selected" href="/settings-withdraw">
				<div class="aside-tab-item">
					<p class="tab-text">회원 탈퇴</p>
				</div>
			</a>
		</aside>
		<article class="article-container">
			<div class="text">
				<h2>회원을 탈퇴하시겠어요? 😢</h2>
				<br>
				<br>
				<br>
				<br>
				<h3>탈퇴하시면 독서기록은 모두 삭제되니,<br> 신중하게 생각해 주세요.</h3>
			</div>
			<div class="buttons">
				<form id="withdraw-form" method="post">
					<br>
					<input type=hidden name="userId" id="userId" value=${user.userId}>
					<input type=hidden name="userRole" id="userRole" value=${user.userRole}>
					<button type="button" class="btn btn-danger withdraw-complete"
						id="withdraw-complete-guardian">BookHub
						탈퇴하기</button>
				</form>
			</div>
		</article>
	</section>

	<script src="/static/js/user/settings-withdraw.js"></script>
</body>

</html>