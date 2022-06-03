<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>BookHub :: 활동 요약</title>
        <link rel="stylesheet" href="/static/css/profile/profile.css">
        <link rel="stylesheet" href="/static/css/profile/summary.css">
    </head>

    <body>
        <!-- Header -->

        <!-- Profile -->
        <div id="profile-wrapper">
            <!-- Profile Head -->
            <div id="profile-head">
                <h1 id="profile-username">
                    ${user.userNick}님의 독서 활동 캘린더
                </h1>
            </div>
            <!-- Profile Tab -->
            <div id="profile-tabmenu">
                <a class="tabmenu-btn activetab" href="/profile/summary">
                    📋 활동 요약
                </a>
                <a class="tabmenu-btn" href="/profile/library">
                    📚 내 서재
                </a>
            </div>

            <!-- Profile Summary -->
            <div id="profile-body">
                <div id="summary-calendar" class="profile-section">
                    <h2 class="profile-section-title">
                        나의 독서 활동
                    </h2>
                    <div id="calendar-graph">

                    </div>
                    <div id="calendar-stat">
                        <div class="stat-content">
                        
                        </div>
                        <div class="stat-content">
                        
                        </div>
                        <div class="stat-content">
                        
                        </div>
                    </div>
                </div>
                <div id="summary-recent">
                    <div id="summary-recent-book" class="profile-section">
                        <h2 class="profile-section-title">
                            최근 읽은 책
                        </h2>
                        <div class="recent-content">
                            <c:if test="${empty recentLog}">
                                <div class="recent-item">    
                                    <span class="no-recent-item">아직 독서 활동을 기록하지 않았습니다.</span>
                                </div>
                            </c:if>
                            <c:if test="${!empty recentLog}">
                                <c:forEach items="${recentLog}" var="log">
                                    <div class="recent-item">
                                        <img class="recent-thumbnail" src="${bookInfo[log.bookISBN].get('coverSmallUrl')}">
                                        <div class="recent-info">
                                            <span class="recent-title" title="${bookInfo[log.bookISBN].get('title')}"><a class="recent-title-link" href="/readinglog/edit?isbn=${log.bookISBN}">${bookInfo[log.bookISBN].get('title')}</a></span>
                                            <span class="recent-author">${bookInfo[log.bookISBN].get('author')}</span>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div id="summary-recent-review" class="profile-section">
                        <h2 class="profile-section-title">
                            최근 작성한 리뷰
                        </h2>
                        <div class="recent-content">
                            <c:if test="${empty recentReview}">
                                <div class="recent-item">    
                                    <span class="no-recent-item">작성한 리뷰가 없습니다.</span>
                                </div>
                            </c:if>
                            <c:if test="${!empty recentReview}">
                                <c:forEach items="${recentReview}" var="review">
                                    <div class="recent-item">
                                        
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </body>

    </html>