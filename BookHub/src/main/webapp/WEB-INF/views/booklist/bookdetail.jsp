<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
					<br>
					<div class="bookdetail_reviewcount">
						리뷰 수 ${reviewCount}
					</div>
					<input type="hidden" value="${bookdetail.bookEndpage}">
					<div class="bookReview_reg">
						<c:choose>
							<%-- 로그인 안한 경우 --%>
							<c:when test="${userbookExist == -1}">
								<button class="userbook_none">내 책으로 등록하기</button>
							</c:when>
							<%-- 책 등록 안한 경우 --%>
							<c:when test="${userbookExist == 0}">
								<button class="userbook_reg">내 책으로 등록하기</button>
							</c:when>
							<%-- 읽고 있는 책인 경우 --%>
							<c:otherwise>
								<div class="userbook_item">
									<div class="userbook_item_title">
										내 독서 진행도
									</div>
									<div class="userbook_item_progress">
										
									</div>
								</div>

							</c:otherwise>
						</c:choose>
					</div>
				</div>	
			</div>
			<div class="bookReview_content">
				<div class="bookReview_title">
					<p>BOOK REVIEW</p>
				</div>
				<div class="bookReview_body">
					<!-- 반복할 reviewlist 시작 -->
					<c:forEach items="${ reviewlist }" var="list" varStatus="status">
						<div class="reviewlist_item">
							<div class="reviewlist_userthumbnail">
								<img src="${list.userThumbnail}"/>
							</div>
							<div class="reviewlist_body">
								<div class="reviewlist_reviewcontent">
									${list.reviewContent}							
								</div>
								<div class="reviewlist_userinfo">
									<div class="reviewlist_usernickname">
										${list.userNick}
									</div>
									<div class="reviewlist_writedate">										
										<fmt:formatDate value="${list.reviewWritedate}" var="writedate" pattern="yyyy-MM-dd"/>
										${writedate}
									</div>
								</div>
							</div>						
						</div>
					</c:forEach>                    
					<!-- 반복할 reviewlist 종료 -->
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>