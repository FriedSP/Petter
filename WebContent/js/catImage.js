var imgselect = document.getElementById('catImage');
imgselect.addEventListener('change',function(e){
	var reader = new FileReader();
	reader.onload = function (f) {
		document.getElementById('catpreview').setAttribute('src', f.target.result);
	}
	reader.readAsDataURL(e.target.files[0]);
});