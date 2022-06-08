<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="static/css/main.css" type="text/css">
<div class="slider" id="slider" >
	<input type="radio" name="slide-radios" class="slide-radio" checked id="slide-radio-1">
	<input type="radio" name="slide-radios" class="slide-radio" id="slide-radio-2">
	<input type="radio" name="slide-radios" class="slide-radio" id="slide-radio-3">
	
	<div class="pagination" id="pagination">
		<label for="slide-radio-1"></label>
		<label for="slide-radio-2"></label>
		<label for="slide-radio-3"></label>
	</div>

	<div class="slide" id="slide-1">
		<div class="slide-content-1">
			<h1>Book Hub</h1>
			<h2>"한 인간의 존재를 결정 짓는 것은 그가 읽은 책과 그가 쓴 글이다"<br/><br/>
			-도스토예프스키</h2>
			<a href="/introduction">소개 바로가기</a>
		</div>
		<img src="">
	</div>
	<div class="slide" id="slide-2">
		<div class="slide-content-2">
			<h1>책 목록</h1>
			<h2>어떤 책들이 있는 지 구경해볼래요?<br/>
			새로 읽을 책을 찾아보러 갈까요?<br/>
			다 읽은 책의 리뷰를 남기고, 공유해보아요</h2>
			<a href="${url}/booklist">책 둘러보기</a>
		</div>
	</div>
	<div class="slide" id="slide-3">
		<div class="slide-content-3">
			<h1>나의 캘린더</h1>
			<h2>어떤 책을 읽었나요? <br/>
			오늘 읽은 페이지를 기록하러 가볼까요?<br/>
			독서를 통해 마음 속 나무를 키워보아요</h2>
			<a href="${url}/rlog">책 심으러 가기</a>
		</div>
	</div>
</div>
<script src="/static/js/slide.js"></script>