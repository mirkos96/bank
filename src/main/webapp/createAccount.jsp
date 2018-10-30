<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="./css/stylesheet.css">
<title>Création de compte</title>
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
			<nav class="navbar navbar-inverse navbar-fixed-top">
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
			</nav>
	</header>
	<section>
	<div class="container">
		<div class="row">
			<c:choose>
				<c:when test="${empty currentAccount}">		
			<form action="<c:url value="/accountDispatcher"/>" method="POST">
				<div class="form-group">
				<h3>Création d'un compte !</h3>
				</div>
				<div class="form-group">
				<label>Numéro de compte</label>
				<input type='text' name='accountnumber' <c:out value=""></c:out>>
				</div>
				<div class="form-group">
				<label>Date de création</label>
				<input type='date' name='creationdate' <c:out value=""></c:out>>
				</div>
				<div class="form-group">
				<label>Solde initial</label>
				<input type='number' name='balance' <c:out value=""></c:out>>
				</div>
				<div class="form-group">
				<label>Découvert autorisé</label>
				<input type='number' name='overdraft' <c:out value=""></c:out>>
				</div>
				<div class="form-group">
				<label>Taux d'intérêt</label>
				<input type='number' step="0.1" name='interestrate' <c:out value=""></c:out>>
				</div>
				<div class="form-group">
				<label>Agence</label>
				<select class="form-control" name='agency'>
					<c:forEach var="agency" items="${agencies}">
		     			<option value="${agency.id}">${agency}</option>
		    		</c:forEach>
				</select>
				</div>
				<div class="form-group">
				<label>Type de compte</label>
				<select class="form-control" name='accounttype'>
					<c:forEach var="type" items="${accounttype}">
		     			<option value="${type.id}">${type}</option>
		    		</c:forEach>
				</select>
				</div>
				<div class="form-group">
				<label>Seuil d'alerte(optionnel)</label>
				<input type='number' name='alert' <c:out value=""></c:out>>
				</div>
				<div class="btn-group">
				<button class="btn btn-primary" type='submit'>Submit</button>
				</div>
			</form>
			</c:when>
			<c:otherwise>
			<h3>Edition du compte "${currentAccount}" !</h3>
			<form action="<c:url value="/EditAccount"/>" method="POST">
				<div class="form-group">
				<input type='hidden' name='id' value="${currentAccount.id}">
				</div>
				<div class="form-group">
				<label>Numéro de compte</label>
				<input type='text' name='accountnumber' value="${currentAccount.accountNumber}">
				</div>
				<div class="form-group">
				<label>Date de création</label>
				<input type='date' name='creationdate' value="${creationDate}">
				</div>
				<div class="form-group">
				<label>Solde initial</label>
				<input type='number' name='balance' value="${currentAccount.firstTotal}">
				</div>
				<div class="form-group">
				<label>Découvert autorisé</label>
				<input type='number' name='overdraft' value="${currentAccount.overdraft}">
				</div>
				<div class="form-group">
				<label>Taux d'intérêt</label>
				<input type='number' step="0.1" name='interestrate' value="${currentAccount.interestRate}">
				</div>
				<div class="form-group">
				<label>Agence</label>
				<select class="form-control" name='agency'>
					<c:forEach var="agency" items="${agencies}">
					<c:choose>
						<c:when test="${agency.id eq currentAccount.agency.id}">
								<option value="${agency.id}" selected="selected">${agency}</option>
						</c:when>
						<c:otherwise>
								<option value="${agency.id}">${agency}</option>
						</c:otherwise>
					</c:choose>
		    		</c:forEach>
				</select>
				</div>
				<div class="form-group">
				<label>Type de compte</label>
				<select class="form-control" name='accounttype'>
					<c:forEach var="type" items="${accounttype}">
					<c:choose>
						<c:when test="${type.id eq currentAccount.accountType.id}">
								<option value="${type.id}" selected="selected">${type}</option>
						</c:when>
						<c:otherwise>
								<option value="${type.id}">${type}</option>
						</c:otherwise>
					</c:choose>
		    		</c:forEach>
				</select>
				</div>
				<div class="form-group">
				<label>Seuil d'alerte(optionnel)</label>
				<input type='number' name='alert' value="${currentAccount.alertThresh}">
				</div>
				<div class="btn-group">
				<button class="btn bt-primary" type='submit'>Submit</button>
				</div>
			</form>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
</section>
</body>
</html>