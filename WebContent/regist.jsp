<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ペッター - 新規登録</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="wrapper logsubWrapper">
<section class="section_rogin_top">
<div class="title_img">
<img src="image/regist.gif">
</div>
</section>
<div class="login_bg">
<form action="regist" method="post">
<div class="section_login">
<div class="error">
<c:if test="${ not empty error }">
<p>※${ error }</p>
</c:if>
</div>
<div class="table_center">
<table>
<tr>
<td>ユーザーネーム</td>
<td><input type="text" name="name" value="${ name }"></td>
<td>※20文字以内</td>
</tr>
<tr>
<td>メールアドレス</td>
<td><input type="text" name="address" value="${ address }"></td>
</tr>
<tr>
<td>パスワード</td>
<td><input type="password" name="pass" value="${ pass }"></td>
<td>※英数字10文字以内</td>
</tr>
<tr>
<td>パスワード確認</td>
<td><input type="password" name="conPass"></td>
</tr>
</table>
</div>
<div class="confirm_button">
<button class="button" type="submit">確認する</button>
</div>
</div>
</form>
</div>
<div class="regist">
<p><a href="login">← 戻る</a></p>
</div>
<%@ include file="footer.jsp" %>
</div>
</body>
</html>