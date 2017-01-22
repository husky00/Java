function up(){
	var folder = document.getElementById('folder');
	var file = document.getElementById('file');
	var filetext = document.getElementById('file_text');
	//var up_tri = document.getElementById('up_tri');
	//var h = filetext.scrollHeight;
	//folder.style.height = 0 + 'px';
	//file.style.height = h + 'px';
	folder.style.display = "none";
	file.style.display = "block";
	//up_tri.style.borderColor = "transparent transparent #000 transparent" ;
}

function down(){
	var folder = document.getElementById('folder');
	var file = document.getElementById('file');
	var foldertext = document.getElementById('folder_text');
	//var down_tri = document.getElementById('dowm_tri');
	//var h = foldertext.scrollHeight;
	//folder.style.height = h + 'px';
	//file.style.height = 0 + 'px';
	folder.style.display = "block";
	file.style.display = "none";
	//down_tri.style.borderColor = "transparent transparent transparent #000" ;
}