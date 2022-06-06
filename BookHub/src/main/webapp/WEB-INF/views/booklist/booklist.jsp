<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOKS - 베스트셀러</title>
<link rel='stylesheet' href='/static/css/booklist/booklist.css'>
<script src="/webjars/jquery/3.6.0/jquery.min.js"></script>
<script src='/static/js/booklist/booklist.js'></script>
</head>
<body>
<div class="booklist_container">
	<div class="booklist_content">		
		<div class="booklist_content_body">
			<div class="booklist_content_title">
				베스트셀러
			</div>
			<div class="booklist_content_category">
				<p>${(param.mall == null) || param.mall == ""  ? "국내도서" : param.mall} > ${categoryName} </p>
			</div>			
			<div class="booklist_content_ranking">
				<a class="${(param.start == null) || (param.start == 1) ? 'view': 'none'}" href='/booklist?start=1&mall=${param.mall}&SearchTarget=${param.SearchTarget}&CategoryId=${param.CategoryId}'>1위 ▼</a>
				<a class="${(param.start == 2) ? 'view': 'none'}" href='/booklist?start=2&mall=${param.mall}&SearchTarget=${param.SearchTarget}&CategoryId=${param.CategoryId}'>11위 ▼</a>
				<a class="${(param.start == 3) ? 'view': 'none'}" href='/booklist?start=3&mall=${param.mall}&SearchTarget=${param.SearchTarget}&CategoryId=${param.CategoryId}'>21위 ▼</a>
				<a class="${(param.start == 4) ? 'view': 'none'}" href='/booklist?start=4&mall=${param.mall}&SearchTarget=${param.SearchTarget}&CategoryId=${param.CategoryId}'>31위 ▼</a>
				<a class="${(param.start == 5) ? 'view': 'none'}" href='/booklist?start=5&mall=${param.mall}&SearchTarget=${param.SearchTarget}&CategoryId=${param.CategoryId}'>41위 ▼</a>
			</div>
			<div class="booklist_content_item">
				<!-- 반복할 booklist 시작 -->
				<c:forEach items="${ booklist }" var="list" varStatus="status">
					<div class="booklist_item">
						<div class="booklist_item_body">
							<div class="booklist_ranking">
								<p>${param.start == null ? status.count : (param.start - 1) * 10 + status.count}</p>
							</div>
						</div>
						<div class="booklist_bookinfo">
							<div class="booklist_bookimg">
								<a href='/bookdetail?isbn=${list.bookIsbn}'><img src="${list.bookImg}"/></a>
							</div>
							<div class="booklist_bookname">
								<a href='/bookdetail?isbn=${list.bookIsbn}'>${list.bookName}</a>								
							</div>
							<div class="booklist_booksubinfo">
								${list.bookAuthor} / ${list.bookPublisher}
							</div>
						</div>						
					</div>
				</c:forEach>                    
				<!-- 반복할 booklist 종료 -->
			</div>
		</div>
		<div class="booklist_category">
			<div class="booklist_cate_select">
				<select name="SearchTarget" id="search_target">
					<option value="Book" ${param.SearchTarget == 'Book' ? 'selected="selected"' : ''}>국내도서</option>
					<option value="Foreign" ${param.SearchTarget == 'Foreign' ? 'selected="selected"' : ''}>외국도서</option>
					<option value="eBook" ${param.SearchTarget == 'eBook' ? 'selected="selected"' : ''}>전자책</option>
				</select>
				<select name="CategoryId" id="category_id">
					<option value="0"  ${param.CategoryId == '0' ? 'selected="selected"' : ''}>전체</option>
					<!-- 반복할 category 시작 -->
					<c:forEach items="${ catagory }" var="cate" >
						<option value="${cate.cid}" ${param.CategoryId == cate.cid ? 'selected="selected"' : ''}>${cate.categoryName}</option>
					</c:forEach>   
				</select>
			</div>
			<div class="booklist_search">
				<div class="booklist_search_title">
					<p>현재 분야에서 검색</p>
				</div>
				<div class="booklist_search_body">
					<select name="QueryType" id="query_type">
						<option value="Keyword" selected>제목+저자</option>
						<option value="Title">제목</option>
						<option value="Author">저자</option>
						<option value="Publisher">출판사검색</option>
					</select>
					<div class="booklist_search_txt">
						<input type="search" id="booklist_search_word" placeholder="책 상세 검색하기">
						<button id="booklist_search_btn">검색</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>