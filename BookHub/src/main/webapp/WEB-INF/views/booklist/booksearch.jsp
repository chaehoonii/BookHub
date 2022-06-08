<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOKS - 도서 검색</title>
<link rel='stylesheet' href='/static/css/booklist/booklist.css'>
<link rel='stylesheet' href='/static/css/booklist/booksearch.css'>
<script src="/webjars/jquery/3.6.0/jquery.min.js"></script>
<script src='/static/js/booklist/booksearch.js'></script>
</head>
<body>
<div class="booklist_container">
	<div class="booklist_content">		
		<div class="booklist_content_body">
			<div class="booklist_content_title">
				도서 검색
			</div>
			<div class="booklist_content_category">
				<p>${(param.mall == null) || param.mall == ""  ? "국내도서" : param.mall} > ${categoryName} </p>
			</div>
			<c:choose>
				<%-- booklist 없는 경우 --%>
				<c:when test="${fn:length(booklist) == 0}">
					<div class="booklist_result">
						<span>"${param.searchWord}"</span>에 대한 검색 결과가 없습니다.
					</div>
					<div class="booklist_content_item"></div>
				</c:when>
				<c:otherwise>
					<div class="booklist_result">
						<span>"${param.searchWord}"</span>에 대한 ${totalResults}개의 상품이 검색되었습니다.
					</div>
					<div class="booklist_content_item">
					<%-- 반복할 booklist --%>					
					<c:forEach items="${ booklist }" var="list" varStatus="status">
						<div class="booklist_item">
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
							<input type="hidden" value="${list.bookIsbn}">
						</div>
					</c:forEach>
					</div>                    
				</c:otherwise>
			</c:choose>
			<!-- 페이지 번호 -->
			<div class="booklist_page">
				<div class="paging">
					<c:choose>
						<%-- booklist 없는 경우 --%>
						<c:when test="${pagedto.totalPageCount == 0}">
							<div class="direction_none">&lt;&lt;</div>
							<div class="direction_none"> &lt;</div>
							<div class="direction_none">0</div>
							<div class="direction_none">&gt;</div>
							<div class="direction_none">&gt;&gt;</div>
						</c:when>
						<c:otherwise>						
							<div class="direction_first"> &lt;&lt; </div> 

							<c:if test="${pagedto.hasPreviousPage == true}">
								<div class="direction_prev" id="${pagedto.currentPage-1}"> &lt;</div>
							</c:if>
							<c:if test="${pagedto.hasPreviousPage == false}">
								<div class="direction_none"> &lt;</div>
							</c:if>
							
							<c:forEach begin="${pagedto.firstPage}" end="${pagedto.lastPage}" var="page">
								<div class="direction_num" id="${page}"><p class="${pagedto.currentPage == page? 'direction_current' : ''}">${page}</p></div>
							</c:forEach>
							
							<c:if test="${pagedto.hasNextPage == true}">
								<div class="direction_next" id="${pagedto.currentPage+1}"> &gt;</div>
							</c:if>
							<c:if test="${pagedto.hasNextPage == false}">
								<div class="direction_none">&gt;</div>
							</c:if>

							<div class="direction_last" id="${pagedto.totalPageCount}"> &gt;&gt; </div>
												
						</c:otherwise>
					</c:choose>
				</div>

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
						<option value="Keyword" ${param.queryType == 'Keyword' ? 'selected="selected"' : ''}>제목+저자</option>
						<option value="Title" ${param.queryType == 'Title' ? 'selected="selected"' : ''}>제목</option>
						<option value="Author" ${param.queryType == 'Author' ? 'selected="selected"' : ''}>저자</option>
						<option value="Publisher" ${param.queryType == 'Publisher' ? 'selected="selected"' : ''}>출판사검색</option>
					</select>
					<div class="booklist_search_txt">
						<input type="search" id="booklist_search_word" placeholder="책 상세 검색하기" value="${param.searchWord}">
						<button id="booklist_search_btn">검색</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>