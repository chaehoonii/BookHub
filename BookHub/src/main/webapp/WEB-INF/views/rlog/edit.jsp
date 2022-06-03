<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>BookHub :: 독서 활동 기록하기</title>
        <link rel="stylesheet" href="/static/css/rlog/edit.css">
    </head>

    <body>
        <!-- Header -->

        <!-- ReadingLog -->
        <div id="readinglog-wrapper">
            <!-- ReadingLog Head -->
            <div id="readinglog-head">
                <img id="bookimage" src="${bookInfo.get('coverLargeUrl')}">
                <c:if test="${!empty bookInfo}">
                    <h1 id="bookTitle">${bookInfo.get('title')}</h1>
                    <span id="bookAuthor">${bookInfo.get('author')}</span>
                </c:if>
            </div>
            <!-- ReadingLog Edit -->
            <div id="readinglog-body">
                <form action="/rlog/edit" method="post">
                    읽은 날짜: <input name="readDate" type="date" pattern="\d{4}-\d{2}-\d{2}" required><br>
                    읽은 페이지: <input name="startPage" type="number" required> ~ <input name="endPage" type="number"
                        required><label><input name="readComplete" value="true" type="checkbox">완독</label><br>
                    읽는 내용 요약: <input name="summary" type="text"><input value="기록하기" type="submit">
                    <br>(선택)
                    <input type="hidden" name="isbn" value="${param.isbn}">
                </form>
            </div>
        </div>


    </body>

    </html>