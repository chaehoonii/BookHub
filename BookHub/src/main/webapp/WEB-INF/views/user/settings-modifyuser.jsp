<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>BookHub | Settings</title>
	<!-- Bootstrap WebJars -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/bootstrap/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="/static/css/user/common_style.css">
	<link rel="stylesheet" href="/static/css/user/settings-modifyuser.css" />
	<link rel="stylesheet" href="/static/css/user/settings-asidebar.css" />
</head>

<body>
	<section class="container">
		<%-- <jsp:include page="./settings-asidebar.jsp" flush="false" /> --%>
		<!-- 어사이드바 컨테이너 -->
		<aside class="aside-container">
			<a class="content selected" href="/settings">
				<div class="aside-tab-item">
					<p class="tab-text">회원정보 수정</p>
				</div>
			</a><br>
			<a class="content" href="/settings-withdraw">
				<div class="aside-tab-item">
					<p class="tab-text">회원 탈퇴</p>
				</div>
			</a>
		</aside>
		<article class="article-container">
			<div class="title-text">
				<h2>회원정보 수정</h2>
			</div>
			<hr>
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
				<div id="nickappend"></div>
				<input type=hidden name="userId" id="userId" value=${user.userId}>
				<input type=hidden name="userRole" id="userRole" value=${user.userRole}>
				<!-- 변경하기 버튼 -->
				<div class="update-comlete-container">
					<br> <br> <input type="submit" class="btn btn-outline-dark" value="회원정보 수정하기"
						id="update-complete" />
				</div>
			</form>
		</article>
	</section>


	<script src="/static/js/user/settings-modifyuser.js"></script>
</body>

</html>