<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="booklist_container">
		<div class="booklist_head">
			<div class="head_title">
				카테고리 별
			</div>
			<div class="head_cate_select">
				<select name="category" id="category">
					<option></option>
				</select>
			</div>
		</div>
		<div class="booklist_content">
			
			<div class="booklist_item">
				책 제목 : ${bookdetail.bookName}
				책 표지 : <img src="${bookdetail.bookImg}"/>
				책 페이지수 :${bookdetail.bookEndpage}
			</div>
			<div class="booklist_item">
				책list 
			</div>
		</div>
	</div>
</body>
</html>