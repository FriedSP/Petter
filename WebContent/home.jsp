<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ペッター - ホーム</title>
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
<div class="menu_button">
<c:choose>
	<c:when test="${not empty address}">
	<a href="logout"><button class="button" type="submit">ログアウト</button></a>
	<div class="submission_button">
	<a href="submission"><button class="button">投稿</button></a>
	</div>
	</c:when>
	<c:when test="${empty address}">
	<a href="login"><button class="button">ログイン</button></a>
	</c:when>
</c:choose>

</div>
<form action="look" method="post">
<section class="section_homeMain">
	<input id="dog" type="radio" name="tab_item" <c:if test="${type == 2 or empty checked}">checked</c:if>>
	<label class="tab_item" for="dog">いぬ</label>
	<input id="cat" type="radio" name="tab_item" <c:if test="${type == 1}">checked</c:if>>
	<label class="tab_item" for="cat">ねこ</label>
	<div class="sub_content home_scroll" id="dog_content">
	<div class="content_description">
	<section class="content_button">
	<c:forEach var="dogImg" items="${dogImg}">
	<section class="content_img_align">
		<button type="submit" name="look_submission" value="${dogImg['submission_id']}"><img src="image/uploaded/${dogImg['image']}" id="img"></button>
	</section>
	</c:forEach>
	</section>
 	</div>
	</div>
	<div class="sub_content home_scroll" id="cat_content">
	<div class="content_description">
	<section class="content_button">
	<c:forEach var="catImg" items="${catImg}">
	<section class="content_img_align">
		<button type="submit" name="look_submission" value="${catImg['submission_id']}"><img src="image/uploaded/${catImg['image']}" id="img"></button>
	</section>
	</c:forEach>
	</section>
	</div>
	</div>
<!-- モーダルエリアここから -->
<!--
<section id="modalArea" class="modalArea">
  <div id="modalBg" class="modalBg"></div>
  <div class="modalWrapper">
    <div class="modalContents">
	  <img src="" id="modalImg">
	  <p>
	  </p>
    </div>
    <div id="closeModal" class="closeModal">
      ×
    </div>
  </div>
</section>
 -->
 <!-- モーダルエリアここまで -->
</section>
</form>
<%@ include file="footer.jsp" %>
</div>
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/modal_overlay.js"></script>
<script src="js/slide.js"></script>
</body>
</html>