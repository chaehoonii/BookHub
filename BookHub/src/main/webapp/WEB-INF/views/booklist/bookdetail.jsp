<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='/static/css/booklist/bookdetail.css'>
<script src="/webjars/jquery/3.6.0/jquery.min.js"></script>
<script src='/static/js/booklist/bookdetail.js'></script>
</head>
<body>
<div class="bookdetail_container">
	<div class="bookdetail_content">		
		<div class="bookdetail_content_body">
			<!-- 책 상세 내용 -->
			<div class="bookdetail_item">
				<div class="bookdetail_bookimg">
					<img src="${bookdetail.bookImg}"/>
				</div>
				<div class="bookdetail_bookinfo">					
					<div class="bookdetail_bookname">
						${bookdetail.bookName}								
					</div>
					<div class="bookdetail_booksubinfo">
						${bookdetail.bookAuthor} &ensp; ${bookdetail.bookPublisher} &ensp; ${bookdetail.bookPubdate}
					</div>
					<input type="hidden" value="${bookdetail.bookEndpage}">
				</div>	
			</div>
			<div class="bookReview_content">
			</div>
		</div>
	</div>
</div>
</body>
</html>