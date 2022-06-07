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
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
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
			<a class="content" href="/settings-deleteuser">
				<div class="aside-tab-item">
					<p class="tab-text">회원 탈퇴</p>
				</div>
			</a>
		</aside>
		
	</section>

</body>

</html>