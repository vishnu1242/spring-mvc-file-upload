<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Code Challenge</title>
<style>
body {
font-family: Arial, Helvetica, sans-serif;
}

 .container {
  padding: 16px;
  width: 28%;
  border: 3px solid #f1f1f1;
  margin:auto;
}

input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}
</head>
<style>
.container {
	padding: 16px;
	width: 80%;
	border: 3px solid #f1f1f1;
	margin: auto;
	margin-top: 2%;
	background-color: lavender
}
</style>
<body>
 

<form action="uploadfile" method="post">
  <br/><br/>

  <div class="container">
   
    <label for="uname"><b>User name</b></label>
    <input type="text" placeholder="Enter Username" name="userName" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
      <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label>  
    <button type="submit">Login</button>
    
  </div> 
</form>

</body>
</html>