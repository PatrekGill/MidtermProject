<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Song</title>
</head>
<body>

	<c:if test="${! empty Songs}">
		<h1>Song Information</h1>
		<ul>
			<c:forEach var="x" items="${Songs}">

				<li>Name: ${x.name}</li>
				<br>
				<li>Lyrics: ${x.lyrics}</li>
				<br>
				<li>Duration Seconds: ${x.durationInSeconds}</li>
				<br>
			</c:forEach>
	</c:if>

	<c:if test="${! empty Albums}">
		<h1>Album Information</h1>
		<ul>
			<c:forEach var="x" items="${Albums}">

				<li>Name: ${x.title}</li>
				<br>
				<li>Description: ${x.description}</li>
				<br>
				<li>Release Date: ${x.releaseDate}</li>
				<br>
			</c:forEach>
		</ul>
	</c:if>

	<c:if test="${! empty Artists}">
		<h1>Artist Information</h1>
		<ul>
			<c:forEach var="x" items="${Artists}">

				<li>Name: ${x.name}</li>
				<br>
				<li>Description: ${x.description}</li>
				<br>
			</c:forEach>
		</ul>
	</c:if>

</body>
</html>