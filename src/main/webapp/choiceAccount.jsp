<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="./css/stylesheet.css">
<title>Insert title here</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/bootstrap-theme.min.css" rel="stylesheet">
<style>
html {
  font-family: sans-serif;
  -webkit-text-size-adjust: 100%;
      -ms-text-size-adjust: 100%;
}
.navbar-brand {
 padding: 0px;
}
.navbar-brand>img {
  height: 100%;
 padding: 7px 15px;
  width: auto;
}
body{
	margin-top:200px;
   text-align: center;
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
			<!-- <nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<div class="navbar-brand">
						<a class="navbar-brand" href="#"><img
							class="img-responsive2" src="css/images/gold.png"
							alt="My Personnal Bank"></a>
							<h4 class="navbar-text">My Personnal Bank</h4>
					</div>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li class="divider"></li>
								<li class="dropdown-header">Nav header</li>
								<li><a href="#">Separated link</a></li>
								<li><a href="#">One more separated link</a></li>
							</ul></li>
						<li><a onClick='window.location.href="<c:url value="/authentification.jsp"/>"'>Log out</a></li>
					</ul>
				</div>
				</div>
			</nav> -->
	</header>
	<section>
	<div class="container">
		<div class="form-group">
			<select class="form-control form-control-lg" onChange="window.location.href=this.value">
				<option selected>Choisissez un compte...</option>
				<c:forEach var="account" items="${accounts}">
		     		<option value="${contextPath}/choiceAccount?account=${account.id}">${account}</option>
		    	</c:forEach>
			</select>
		</div>
	</div>
</section>
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="./js/lib/bootstrap.min.js"></script>
</body>
</html>