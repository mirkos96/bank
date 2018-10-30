<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/stylesheet.css">
<title>Insert title here</title>
</head>
<body>
		<label>Numéro de compte</label>
		<label>"${account.accountNumber}"</label>
		<label>Date de création</label>
		<label>
		<jsp:useBean id="dateOperation" class="java.util.Date"/>
		<fmt:formatDate value="${account.creationDate}" pattern="dd MMMM YYYY" type="date"/></label>
		<label>Solde initial</label>
		<label>"${account.firstTotal}"</label>
		<label>Découvert autorisé</label>
		<label>"${account.overdraft}"</label>
		<label>Taux d'intérêt</label>
		<label>"${account.interestRate}"</label>
		<label>Agence</label>
		<label>"${account.agency.agencyName}"</label>
		<label>Type de compte</label>
		<label>"${account.accountType}"</label>
		<label>Seuil d'alerte(optionnel)</label>
		<label>"${account.alertThresh}"</label>
</body>
</html>