<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>BookHub :: 내 서재</title>
    <link rel="stylesheet" href="/static/css/rlog/rlog.css">
    <link rel="stylesheet" href="/static/css/rlog/library.css">
</head>

<body>
    <!-- Header -->

    <!-- ReadingLog -->
    <div id="profile-wrapper">
        <!-- Library Head -->
        <div id="profile-head">
            <h1 id="profile-username">
                ${user.userNick}님의 독서기록
            </h1>
        </div>
        <!-- ReadingLog Tab -->
        <div id="profile-tabmenu">
            <a class="tabmenu-btn" href="/rlog/summary">
                📋 활동 요약
            </a>
            <a class="tabmenu-btn activetab" href="/rlog/library">
                📚 내 서재
            </a>
        </div>

        <!-- ReadingLog Library -->
        <div id="profile-body">
            <div id="library-searchmenu">
                <form action="/rlog/library" method="GET">
                    <input id="searchmenu-searchbar" class="searchmenu" type="search" name="q" placeholder="책 제목 검색">
                </form>
            </div>
            <div id="library-content">
                <c:if test="${!empty library}">
                    <c:forEach items="${library}" var="book">
                            <div class="library-item">
                                <img class="library-thumbnail" src="${bookInfo[book.bookISBN].get('cover')}">
                                <div class="library-info">
                                    <div class="library-title" title="${bookInfo[book.bookISBN].get('title')}"><a
                                            class="library-title-link"
                                            href="/rlog/edit?isbn=${book.bookISBN}">${bookInfo[book.bookISBN].get('title')}</a></div>
                                    <div class="library-author"><span>${bookInfo[book.bookISBN].get('author')}</span></div>
                                </div>
                                <div class="library-summary"><c:if test="${!empty book.summary}">NOTE: ${book.summary}</c:if></div>
                                <div class="library-progress">
                                    <fmt:formatNumber value="${book.readPage/bookInfo[book.bookISBN].get('subInfo').get('itemPage')}" type="percent" var="percentReadPage"/>
                                    <fmt:parseDate value="${book.readDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedReadDate" />
                                        <div class="library-readpercent">${percentReadPage}</div>
                                        <div class="library-readpage">${book.readPage}/${bookInfo[book.bookISBN].get('subInfo').get('itemPage')}</div>
                                        <progress class="library-progressbar" value="${book.readPage}" max="${bookInfo[book.bookISBN].get('subInfo').get('itemPage')}"></progress>
                                    <span class="library-readdate">마지막 독서일: <fmt:formatDate pattern="yyyy년 M월 d일" value="${parsedReadDate}"/></span>
                                </div>
                            </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty library}">
                    <div class="library-item">
                        <span class="no-library-item">아직 독서 활동을 기록하지 않았습니다.</span>
                    </div>
                </c:if>
            </div>
        </div>
    </div>


</body>

</html>