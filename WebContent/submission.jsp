<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ペッター - 投稿フォーム</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="wrapper">
<section class="section_top">
<form action="home" method="post">
<div class="img">
<a href="home"><img src="image/logo.png"></a>
</div>
</form>
<div class="title">
 <img src="image/main_img3.jpg">
</div>
</section>
<section class="section_main">
	<input id="dog" type="radio" name="tab_item" checked>
	<label class="tab_item" for="dog">いぬ</label>
	<input id="cat" type="radio" name="tab_item">
	<label class="tab_item" for="cat">ねこ</label>
	<div class="sub_content" id="dog_content">
		<div class="sub_content_description">
		<form action="insertsubmission" method="post" enctype="multipart/form-data">
		<div class="uploadButton">
		いぬの写真を選択
		<input type="file" name="file" id="dogImage" accept="jpg,jpeg,png,gif,image/jpeg,image/png,image/gif">
		<input type="text" id="target" class="uploadValue" disabled />
		</div>
		<section class="section_loadImage">
		<img id="dogpreview">
		</section>
		<br>
		<textarea name="text" rows="5" cols="50" placeholder="投稿したい内容を入力するワン！"></textarea><br>
		<button id="submit" class="button dogButton" type="submit" name="upload" value="dog">投稿するワン！</button>
		</form>
		<div class="back dog_back">
		<p><a href="home">戻るわん</a></p>
		</div>
		</div>
	</div>
	<div class="sub_content" id="cat_content">
		<div class="sub_content_description">
		<form action="insertsubmission" method="post" enctype="multipart/form-data">
		<div class="uploadButton">
		ねこの写真を選択
		<input type="file" name="file" id="catImage" accept="jpg,jpeg,png,gif,image/jpeg,image/png,image/gif">
		<input type="text" id="target" class="uploadValue" disabled />
		</div>
		<section class="section_loadImage">
		<img src="" id="catpreview">
		</section>
		<br>
		<textarea name="text" rows="5" cols="50" placeholder="投稿したい内容を入力するにゃ～"></textarea><br>
		<button id="submit" class="button catButton" type="submit" name="upload" value="cat">投稿するにゃ～</button>
		</form>
		</div>
		<div class="back cat_back">
		<a href="home"><p>戻るにゃん</p></a>
		</div>
		</div>
</section>
<%@ include file="footer.jsp" %>
</div>
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/dogImage.js"></script>
<script src="js/catImage.js"></script>
</body>
</html>