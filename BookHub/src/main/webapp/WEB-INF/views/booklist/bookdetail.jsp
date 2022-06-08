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
						${bookdetail.bookAuthor} &ensp; ${bookdetail.bookPublisher} &ensp;&ensp;&ensp; ${bookdetail.bookPubdate}
					</div>
					<br>
					<div class="bookdetail_reviewcount">
						리뷰 수 ${reviewCount}
					</div>
					<input type="hidden" id="bookisbn" value="${bookdetail.bookIsbn}">
					<div class="bookReview_reg">
						<c:choose>
							<%-- 로그인 안한 경우 --%>
							<c:when test="${userbookExist == -1}">
								<button id="userbook_none">내 책으로 등록하기</button>
							</c:when>
							<%-- 책 등록 안한 경우 --%>
							<c:when test="${userbookExist == 0}">
								<button id="userbook_reg">내 책으로 등록하기</button>
							</c:when>
							<%-- 읽고 있는 책인 경우 --%>
							<c:otherwise>
								<div class="userbook_item">
									<div class="userbook_item_title">
										<div>내 독서 진행도</div>
										<div>${progress}%</div>
									</div>
									<div class="userbook_item_progress">
										<div class="userbook_progressbar">
											<progress id="userbook_progressbar" value="${progress}" max="100"></progress>
										</div>
										<div class="review_btn">
											<c:if test="${progress == 100 && reviewExist == 0}">
												<button id="review_btn">리뷰 쓰기</button>
											</c:if>
										</div>
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
					<!-- 리뷰 등록 -->
					<div id="reviewItem_reg_display">
						<div class="reviewItem_reg">
							<div class="reviewItem_body">
								<input type="hidden" id="userId" value="${user.userId}">
								<div class="reviewItem_text">
									<textarea id="review_txt" maxlength="300"></textarea>
								</div>
								<div class="reviewItem_btn">
									<div id="review_txt_count">(0 / 300)</div>
									<button id="review_reg_btn">리뷰 등록</button>
								</div>
							</div>
						</div>
					</div>
					<!-- 반복할 reviewlist 시작 -->
					<c:forEach items="${ reviewlist }" var="list" varStatus="status">
						<div class="reviewlist_item">
							<div class="reviewlist_userthumbnail">
								<img src="${list.userThumbnail}"/>
							</div>
							<div class="reviewlist_body">
								<div class="reviewlist_reviewcontent">
									<c:if test="${list.userId == user.userId}">
										<input type="hidden" id="reviewNum" value="${list.reviewNum}">
										<%-- 내 리뷰 --%>
										<div id="myreview_content_display">
											<div class="reviewlist_content_txt">
												${list.reviewContent}
											</div>
											<div class="reviewlist_btn">
												<div><button id="review_edit">수정</button></div>
												<div><button id="review_delete">삭제</button></div>
											</div>
										</div>
										<%-- 리뷰 수정 폼 --%>
										<div id="myreview_content_edit">
											<div class="myreview_content_edit">
												<div class="reviewlist_content_txt">
													<textarea id="myreviewContent" maxlength="300">${list.reviewContent}</textarea>
												</div>
												<div class="reviewlist_btn">
													<div id="myreviewContent_count">(0 / 300)</div>
													<div><button id="myreview_edit_reg">수정완료</button></div>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${list.userId != user.userId}">
										<div class="reviewlist_content_txt">
											${list.reviewContent}
										</div>
										<div class="reviewlist_btn">
										</div>
									</c:if>				
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