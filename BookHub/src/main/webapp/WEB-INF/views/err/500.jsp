<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>500 | BookHub</title>

  <!-- CSS -->
  <link rel="stylesheet" href="/static/css/user/common_style.css" />
  <link rel="stylesheet" href="/static/css/error.css" />
</head>

<body>
  <div id="main">
    <div class="fof">
      <h1>${timestamp}</h1><br>
      <h1>${status} - ${error}</h1><br>
      <c:if test="${message == null}">
      	<h1>대단히 죄송합니다🙏</h1><br>
      </c:if>
      <h1>${message}</h1><br>
      <button type="button">
        <a href="${path}">홈으로 돌아가기</a>
      </button><br>
      <img src="/static/img/500.jpg" alt="">
    </div>
  </div>

</body>

</html>