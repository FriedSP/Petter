var imgselect = document.getElementById('dogImage');
imgselect.addEventListener('change',function(e){
	var reader = new FileReader();
	reader.onload = function (f) {
		document.getElementById('dogpreview').setAttribute('src', f.target.result);
	}
	reader.readAsDataURL(e.target.files[0]);
});