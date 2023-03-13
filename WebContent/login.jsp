<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ペッター - ログイン</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="wrapper logsubWrapper">
<section class="section_rogin_top">
<div class="title_img">
<img src="image/${ img }">
</div>
</section>
<div class="login_bg">
<form action="login" method="post">
<div class="section_login">
<div class="error">
<c:if test="${ not empty error }">
<p>※${ error }</p>
</c:if>
</div>
<div class="table_center">
<table class="table">
<tr>
<td>ユーザーネーム</td>
<td><input type="text" name="name" value="${ name }"></td>
</tr>
<tr>
<td>パスワード</td>
<td><input type="password" name="pass" value="${ pass }"></td>
</tr>
</table>
</div>
<div class="confirm_button">
<button class="button" type="submit">ログイン</button>
</div>
</div>
</form>
<div class="regist">
<p>アカウントをお持ちでない方は<a href="regist">こちら</a></p>
<p><a href="home">← 戻る</a></p>
</div>
</div>
<%@ include file="footer.jsp" %>
</div>
</body>
</html>