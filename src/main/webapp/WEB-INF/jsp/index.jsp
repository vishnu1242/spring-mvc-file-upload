<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<title>Code Challenge</title>

<style>
.container {
	padding: 16px;
	width: 80%;
	border: 3px solid #f1f1f1;
	margin: auto;
	margin-top: 2%;
	background-color: lavender
}

body {
	font-family: Arial, Helvetica, sans-serif;
}

.button {
	border: none;
	color: white;
	padding: 6px 21px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	cursor: pointer;
	float: right;
	margin-right: 76px;
	background-color: #795548;
	border-radius: 10px;
}

.headtext1 {
	color: #FF69B4;
	font-weight: bolder;
	font-size: 21px;
}

.headtext2 {
	color: #FF00FF;
	font-weight: bolder;
	font-size: 17px;
}

.headtext3 {
	color: #00FF00;
	font-weight: bolder;
	font-size: 12px;
}
</style>
</head>
<body>
	<script type="text/javascript">
$(document).ready(function() {
	addtextcss();
});

function addtextcss(){
	
	let key = "${key}";
	
	if(key=="test2"){
		$("#headtext").addClass("headtext1");
	} 
	if(key=="test"){
		$("#headtext").addClass("headtext2");
	} 
	if(key=="test3"){
		$("#headtext").addClass("headtext3");
	} 
}
/* 
	
	function getPath() { 
	     var inputName = document.getElementById('files');
	     var imgPath;

	     imgPath = inputName.value; 
	     alert(imgPath);
	     confirmFileSubmit();
	} */
	
	function confirmFileSubmit(){ 
	    var input  = document.getElementById('files');
	    
	    var file   = input.files[0]; 
	    var reader = new FileReader();

	    reader.onload = function(e) {
	        var text = reader.result; 

	        var firstLine = text.split('\n').shift(); 
          
	        console.log(firstLine); 
	        alert(firstLine);
	        document.getElementById('textLine').value=firstLine;
	    }

	    reader.readAsText(file, 'UTF-8');  
	}
	</script>
	<div class="container">
		<a class="button " href="logout">Sign Out</a>
		
		<br /> ${message} <br />
		
		<c:if test="${files.size()>0}">
			<h1>Files Uploaded SucessFully</h1>
			
			<c:forEach var="filename" items="${files}" varStatus="count">
			${count.count}. ${filename.fileName}<br/>
		     ${filename.firstLine}<br/>
		</c:forEach>
		</c:if>
		<c:if test="${files.size() == 0}">
			<span id="headtext">Choose Files</span>
		</c:if>
		<br />
		<form action="savefile" method="post" enctype="multipart/form-data">
			Select File: <input type="file" name="uploadFiles" id="files"
				onchange="confirmFileSubmit()" accept=".txt" multiple="multiple" />
			<input type="submit" value="Upload File" /> <input type="hidden"
				value="" id="textLine" name="firstLine" />
		</form>

		<c:if test="${file.size()>0}">
			<span>${fileCount} Files Were Selected</span>
			<br />
			<br />
			<c:forEach var="filename" items="${file}" varStatus="count">
		 ${filename}<br />
			</c:forEach>
		</c:if>
	</div>
</body>
</html>