<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ペッター - 登録確認</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="wrapper logsubWrapper">
<section class="section_rogin_top">
<div class="title_img">
<img src="image/regist.gif">
</div>
</section>
<div class="table_center">
<table>
<tr>
<td>ユーザーネーム</td>
<td>: ${ name }</td>
</tr>
<tr>
<td>メールアドレス</td>
<td>: ${ address }</td>
</tr>
<tr>
<td>パスワード</td>
<td>: ${ pass }</td>
</tr>
</table>
</div>
<form action="confirm" method="post">
<div class="confirm_button">
<button class="button" type="submit" name="confirm" value="fix">修正する</button>
<button class="button" type="submit" name="confirm" value="regist">登録</button>
</div>
</form>
<%@ include file="footer.jsp" %>
</div>
</body>
</html>