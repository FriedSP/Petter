

$(function () {
  $('.openModal').each(function(){
	  $(this).on('click', function(){
			var modalImg = document.getElementById('modalImg');

			//モーダルウィンドウで表示する画像の処理
			var img = document.getElementById('img');
			var src = img.getAttribute('src');
			modalImg.setAttribute('src', src);

			//複数モーダル用の処理
			var target = $(this).data('target');
			var modal = document.getElementById(target);

			//モーダルフェードイン
			$(modal).fadeIn();
			return false;
	  });
  });
  $('#closeModal , #modalBg').click(function(){
    $('#modalArea').fadeOut();
	return false;
  });
});