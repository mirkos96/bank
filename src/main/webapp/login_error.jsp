<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<p>Wrong Password</p>
	<form method="POST" action="j_security_check">
		<label>Login</label> 
		<input type="text" name="j_username"> 
		<label>Mot de passe</label> 
		<input type="password" name="j_password">
		<button type="submit">login</button>
	</form>

</body>
</html>