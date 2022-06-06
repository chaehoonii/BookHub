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

    <!-- Profile -->
    <div id="profile-wrapper">
        <!-- Profile Head -->
        <div id="profile-head">
            <h1 id="profile-username">
                ${user.userNick}님의 독서 기록
            </h1>
        </div>
        <!-- Profile Tab -->
        <div id="profile-tabmenu">
            <a class="tabmenu-btn activetab" href="/rlog/summary">
                📋 활동 요약
            </a>
            <a class="tabmenu-btn" href="/rlog/library">
                📚 내 서재
            </a>
        </div>

        <!-- Profile Summary -->
        <div id="profile-body">
            <div id="summary-calendar" class="profile-section">
                <h2 class="profile-section-title">
                    최근 독서 활동
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
                        <span class="stat-value"></span>
                    </div>
                    <div class="stat-content">
                        <h3 class="stat-title">최대 연속 독서일</h3>
                        <span class="stat-value"></span>
                    </div>
                    <div class="stat-content">
                        <h3 class="stat-title">현재 연속 독서일</h3>
                        <span class="stat-value"></span>
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
                                    <img class="recent-thumbnail" src="${bookInfo[log.bookISBN].get('cover')}">
                                    <div class="recent-info">
                                        <span class="recent-title" title="${bookInfo[log.bookISBN].get('title')}"><a
                                                class="recent-title-link"
                                                href="/rlog/edit?isbn=${log.bookISBN}">${bookInfo[log.bookISBN].get('title')}</a></span>
                                        <span class="recent-author">${bookInfo[log.bookISBN].get('author')}</span>
                                    </div>
                                    <div class="progress">
                                        <div class="progress-bar" role="progressbar" style="width: ${log.endPage/bookInfo[log.bookISBN].get('subInfo').get('itemPage')*100}%" aria-valuenow="${log.endPage}" aria-valuemin="0" aria-valuemax="${bookInfo[log.bookISBN].get('subInfo').get('itemPage')}"></div>
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

    <script>
        const recentCalendar = "${recentCalendar}";
    </script>
    <script src="/static/js/rlog/summary.js"></script>
</body>

</html>