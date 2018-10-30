<%@page pageEncoding="UTF-8" contentType="text/html" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Authentification</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/bootstrap-theme.min.css" rel="stylesheet">
<style>
html {
  font-family: sans-serif;
  -webkit-text-size-adjust: 100%;
      -ms-text-size-adjust: 100%;
}
body {
	margin:65px;
   text-align: center;
}
section {
margin-top: 40px;
}
div#container{
   margin-left: auto;
   margin-right: auto;
   width: 50em;
}
</style>
</head>
<body>
	<header>
	<h2>Welcome to your Personnal Bank!</h2>
	<img src="css/images/hommeargent.gif">
	</header>
	<section>
		<div class="container">
			<div class="row">
			<form class="form-horizontal" action="j_security_check" method="POST">
				<div class="form-group">
					<label for="inputLogin" class="col-sm-3 control-label">Login</label>
					<div class="col-sm-6">
					<input type="text" class="form-control" placeholder="login" name="j_username">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="col-sm-3 control-label">Password</label>
					<div class="col-sm-6">
					<input type="password" class="form-control" name="j_password" placeholder="Password">
					</div>
				</div>
				<div class="btn" role="group">
					<div class="col-sm-10">
					<button type="button" class="btn btn-default">New User</button>
					<button type="submit" class="btn btn-primary">Connexion</button>
					</div>
				</div>
			</form>
			</div>
		</div>
	</section>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="./js/lib/bootstrap.min.js"></script>
</body>
</html>