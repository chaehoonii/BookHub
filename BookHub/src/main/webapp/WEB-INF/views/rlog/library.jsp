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
            <a class="tabmenu-btn" href="/rlog/summary">
                📋 활동 요약
            </a>
            <a class="tabmenu-btn activetab" href="/rlog/library">
                📚 내 서재
            </a>
        </div>

        <!-- Profile Library -->
        <div id="profile-body">
            <div id="library-searchmenu">
                <form action="/rlog/library" method="GET">
                    <input id="searchmenu-searchbar" class="searchmenu" type="search" name="q" placeholder="책 제목 검색">
                </form>
            </div>
            <div id="library-main">
                <c:if test="${!empty library}">
                            <c:forEach items="${library}" var="book">
                                <div class="library-content">
                                    <div class="library-item">
                                        <img class="library-thumbnail" src="${bookInfo[book.bookISBN].get('cover')}">
                                        <div class="library-info">
                                            <div class="library-title" title="${bookInfo[book.bookISBN].get('title')}"><a
                                                    class="library-title-link"
                                                    href="/rlog/edit?isbn=${book.bookISBN}">${bookInfo[book.bookISBN].get('title')}</a></div>
                                            <div class="library-author"><span>${bookInfo[book.bookISBN].get('author')}</span></div>
                                        </div>
                                        <div class="library-progress">
                                            <fmt:formatNumber value="${book.readPage/bookInfo[book.bookISBN].get('subInfo').get('itemPage')}" type="percent" var="percentReadPage"/>
                                            <fmt:parseDate value="${book.readDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedReadDate" />
                                                <div class="library-readpercent">${percentReadPage}</div>
                                                <div class="library-readpage">${book.readPage}/${bookInfo[book.bookISBN].get('subInfo').get('itemPage')}</div>
                                            <div class="progress">
                                                <div class="progress-bar" role="progressbar" style="width: ${percentReadPage}" aria-valuenow="${book.readPage}" aria-valuemin="0" aria-valuemax="${bookInfo[book.bookISBN].get('subInfo').get('itemPage')}"></div>
                                            </div>
                                            <span class="library-readdate">마지막 독서일: <fmt:formatDate pattern="yyyy년 M월 d일" value="${parsedReadDate}"/></span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
            </div>
        </div>
    </div>


</body>

</html>