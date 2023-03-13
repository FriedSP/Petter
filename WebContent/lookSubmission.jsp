<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ペッター - ${ name }さんの投稿</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
<div class="wrapper">
<section class="section_top">
<div class="img">
<a href="home"><img src="image/logo.png"></a>
</div>
<div class="title">
 <img src="image/main_img3.jpg">
</div>
</section>
<div class="submission_main_content">
<img src="image/uploaded/${ img }">
<div class="text_content">
<div class="user_name">
<div class="name">
<p>ご主人様：
<c:if test="${type == 1 }">
${ name } にゃん
</c:if>
<c:if test="${type == 2 }">
${ name } わん
</c:if>
</p>
</div>
</div>
<div class="text">
<p>${ text }</p>
</div>
<c:if test="${not empty delete}">
<div class="delete_button">
<form action="delete" method="post">
<button class="button" type="submit" name="submission_id" value="${ submission_id }">投稿を取り消す</button>
</form>
</div>
</c:if>
</div>
</div>
<c:choose>
	<c:when test="${ type == 1 }">
	<div class="back cat_back look">
	<p><a href="home">戻るにゃん</a></p>
	</div>
	</c:when>
	<c:when test="${ type == 2 }">
	<div class="back dog_back look">
	<p><a href="home">戻るわん</a></p>
	</div>
	</c:when>
</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>