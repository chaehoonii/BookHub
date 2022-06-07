<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="booklist_container">
		<div class="booklist_head">
			<div class="head_title">
				카테고리 별
			</div>
			<div class="head_cate_select">				
				검색 대상
				<select name="SearchTarget" id="Search_target">
					<option value="Book" selected>국내도서</option>
					<option value="Foreign">외국도서</option>
					<option value="eBook">전자책</option>
				</select>
				분야보기
				<select name="CategoryId" id="category_td">
					<option value="0" selected>전체</option>
				</select>
			</div>
			<div class="book_search">				
				검색 조건
				<select name="QueryType" id="query_type">
					<option value="Keyword" selected>제목+저자</option>
					<option value="Title">제목</option>
					<option value="Author">저자</option>
					<option value="Publisher">출판사검색</option>
				</select>
				<input type="text" id="book_search_word">
				
				<button id="book_search_btn">분야 내 검색</button>
				
			</div>
		</div>
		<div class="booklist_content">
			<div class="booklist_content_title">
				베스트셀러
			</div>
			<!-- 반복할 booklist 시작 -->
			<c:forEach items="${ booklist }" var="list" >
				<div class="booklist_item">
					<div class="booklist_bookimg">
						<img src="${list.bookImg}"/>
					</div>
					<div class="booklist_bookbox">
						<div class="booklist_bookname">
							책 제목 : ${list.bookName}
						</div>
						<div class="booklist_bookauthor">
							책 저자 : ${list.bookAuthor}
						</div>
						<div class="booklist_bookpublisher">
							책 출판사 : ${list.bookPublisher}
						</div>
					</div>
				</div>
			</c:forEach>                    
			<!-- 반복할 게시물 종료 -->
			<div class="booklist_item">
				책list 
			</div>
		</div>
	</div>
</body>
</html>