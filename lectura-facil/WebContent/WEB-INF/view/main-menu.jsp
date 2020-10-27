<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main Form Menu</title>
</head>
<body>
	<form action="processForm" method="GET">
		
	<input type="text" name="textInput">
	<br><br>
	<input type="submit" value="Submit"/>
	
	</form>
</body>
</html>