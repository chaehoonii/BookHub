<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>BookHub :: 활동 요약</title>
    <link rel="stylesheet" href="/static/css/rlog/rlog.css">
    <link rel="stylesheet" href="/static/css/rlog/summary.css">
</head>

<body>
    <!-- Header -->

    <!-- ReadingLog -->
    <div id="readinglog-wrapper">
        <!-- ReadingLog Head -->
        <div id="readinglog-head">
            <h1 id="readinglog-username">
                ${user.userNick}님의 독서기록
            </h1>
        </div>
        <!-- ReadingLog Tab -->
        <div id="readinglog-tabmenu">
            <a class="tabmenu-btn activetab" href="/rlog/summary">
                📋 활동 요약
            </a>
            <a class="tabmenu-btn" href="/rlog/library">
                📚 내 서재
            </a>
        </div>

        <!-- ReadingLog Summary -->
        <div id="readinglog-body">
            <div id="summary-calendar" class="readinglog-section">
                <h2 class="readinglog-section-title">
                    최근 독서활동
                </h2>
                <div id="calendar-graph">
                    <div id="graph-content-left" class="graph-content">
                        <h3 class="graph-title"></h3>
                        <div class="graph-dayofweek">
                            <span>일</span>
                            <span>월</span>
                            <span>화</span>
                            <span>수</span>
                            <span>목</span>
                            <span>금</span>
                            <span>토</span>
                        </div>
                        <div class="graph-main"></div>
                    </div>
                    <div id="graph-content-center" class="graph-content">
                        <h3 class="graph-title"></h3>
                        <div class="graph-dayofweek">
                            <span>일</span>
                            <span>월</span>
                            <span>화</span>
                            <span>수</span>
                            <span>목</span>
                            <span>금</span>
                            <span>토</span>
                        </div>
                        <div class="graph-main"></div>
                    </div>
                    <div id="graph-content-right" class="graph-content">
                        <h3 class="graph-title"></h3>
                        <div class="graph-dayofweek">
                            <span>일</span>
                            <span>월</span>
                            <span>화</span>
                            <span>수</span>
                            <span>목</span>
                            <span>금</span>
                            <span>토</span>
                        </div>
                        <div class="graph-main"></div>
                    </div>
                </div>
                <div id="calendar-stat">
                    <div class="stat-content">
                        <h3 class="stat-title">누적 독서 페이지</h3>
                        <span class="stat-value">
                            <c:choose>
                                <c:when test="${empty streak['max'].streakCount}">
                                    0
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatNumber type="number" value="${accReadPages}"
                                        maxFractionDigits="3" />
                                </c:otherwise>
                            </c:choose>
                            p.
                        </span>
                        <span class="stat-subvalue">차곡차곡 쌓아올린</span>
                    </div>
                    <div class="stat-content">
                        <h3 class="stat-title">최대 연속 독서일</h3>
                        <c:choose>
                            <c:when test="${empty streak['max'].streakCount}">
                                <span class="stat-value">0 일</span>
                                <span class="stat-subvalue">조금씩 꾸준히</span>
                            </c:when>
                            <c:otherwise>
                                <span class="stat-value">
                                    <fmt:formatNumber type="number" value="${streak['max'].streakCount}"
                                        maxFractionDigits="3" />
                                    일
                                </span>
                                <fmt:parseDate value="${streak['max'].streakStartDate}"
                                    pattern="yyyy-MM-dd'T'HH:mm" var="parsedStreakStartDate" />
                                <fmt:parseDate value="${streak['max'].streakEndDate}"
                                    pattern="yyyy-MM-dd'T'HH:mm" var="parsedStreakEndDate" />
                                <span class="stat-subvalue">
                                    <fmt:formatDate pattern="yyyy년 M월 d일"
                                        value="${parsedStreakStartDate}" /> -
                                    <fmt:formatDate pattern="yyyy년 M월 d일" value="${parsedStreakEndDate}" />
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="stat-content">
                        <h3 class="stat-title">현재 연속 독서일</h3>
                        <c:choose>
                            <c:when test="${empty streak['current'].streakCount}">
                                <span class="stat-value">0 일</span>
                                <span class="stat-subvalue">시작하기 좋은 날</span>
                            </c:when>
                            <c:otherwise>
                                <span class="stat-value">
                                    <fmt:formatNumber type="number" value="${streak['current'].streakCount}"
                                        maxFractionDigits="3" />
                                    일
                                </span>
                                <fmt:parseDate value="${streak['current'].streakStartDate}"
                                    pattern="yyyy-MM-dd'T'HH:mm" var="parsedStreakStartDate" />
                                <fmt:parseDate value="${streak['current'].streakEndDate}"
                                    pattern="yyyy-MM-dd'T'HH:mm" var="parsedStreakEndDate" />
                                <span class="stat-subvalue">
                                    <fmt:formatDate pattern="yyyy년 M월 d일"
                                        value="${parsedStreakStartDate}" /> -
                                    <fmt:formatDate pattern="yyyy년 M월 d일" value="${parsedStreakEndDate}" />
                                </span>
                            </c:otherwise>
                        </c:choose>
                        </span>
                    </div>
                </div>
            </div>
            <div id="summary-recent">
                <div id="summary-recent-book" class="readinglog-section">
                    <h2 class="readinglog-section-title">
                        최근 읽은 책
                    </h2>
                    <div class="recent-content">
                        <c:if test="${empty recentLog}">
                            <div class="recent-item">
                                <span class="no-recent-item">아직 독서활동을 기록하지 않았습니다.</span>
                            </div>
                        </c:if>
                        <c:if test="${!empty recentLog}">
                            <c:forEach items="${recentLog}" var="log">
                                <div class="recent-item">
                                    <img class="recent-thumbnail"
                                        src="${bookInfo[log.bookISBN].get('cover')}">
                                    <div class="recent-info">
                                        <div class="recent-title"
                                            title="${bookInfo[log.bookISBN].get('title')}"><a
                                                class="recent-title-link"
                                                href="/rlog/edit?isbn=${log.bookISBN}">${bookInfo[log.bookISBN].get('title')}</a>
                                        </div>
                                        <div class="recent-author">
                                            <span>${bookInfo[log.bookISBN].get('author')}</span></div>
                                    </div>
                                    <div class="recent-progress">
                                        <fmt:formatNumber
                                            value="${log.readPage/bookInfo[log.bookISBN].get('subInfo').get('itemPage')}"
                                            type="percent" var="percentReadPage" />
                                        <fmt:parseDate value="${log.readDate}" pattern="yyyy-MM-dd'T'HH:mm"
                                            var="parsedReadDate" />
                                        <div class="recent-readpercent">${percentReadPage}</div>
                                        <div class="recent-readpage">
                                            ${log.readPage}/${bookInfo[log.bookISBN].get('subInfo').get('itemPage')}
                                        </div>
                                        <progress class="recent-progressbar" value="${log.readPage}"
                                            max="${bookInfo[log.bookISBN].get('subInfo').get('itemPage')}"></progress>
                                        <span class="recent-readdate">마지막 독서일:
                                            <fmt:formatDate pattern="yyyy년 M월 d일"
                                                value="${parsedReadDate}" />
                                        </span>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
                <div id="summary-recent-review" class="readinglog-section">
                    <h2 class="readinglog-section-title">
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

    <script>
        const recentCalendar = "${recentCalendar}";
    </script>
    <script src="/static/js/rlog/summary.js"></script>
</body>

</html>