<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>${userId}</title>
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
                    ${userId}
                </h1>
            </div>
            <!-- Profile Tab -->
            <div id="profile-tabmenu">
                <a class="tabmenu-btn activetab" href="/profile/${userId}">
                    📋 활동 요약
                </a>
                <a class="tabmenu-btn" href="/profile/${userId}/library">
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
                            최근 독서 활동
                        </h2>
                        <div class="recent-content">
                            <c:if test="${empty recentLog}">
                                <div class="recent-item">    
                                    <span class="no-recent-item">아직 독서 활동을 시작하지 않았습니다.</span>
                                </div>
                            </c:if>
                            <c:if test="${!empty recentLog}">
                                <c:forEach items="${recentLog}" var="log">
                                    <div class="recent-item">
                                        <img class="recent-thumbnail" src="${log.value.get('coverSmallUrl')}">
                                        <a href="/readinglog/edit?isbn=${log.key.bookISBN}"><span class="recent-title" title="${log.value.get('title')}">${log.value.get('title')}</span></a>
                                        <span class="recent-author">${log.value.get('author')}</span>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div id="summary-recent-review" class="profile-section">
                        <h2 class="profile-section-title">
                            최근 작성 리뷰
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