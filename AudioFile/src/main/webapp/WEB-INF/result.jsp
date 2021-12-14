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
	<c:choose>
		<c:when test="${! empty Song}">
			<ul>
				<li>Name: ${Song.name}</li>
				<br>
				<li>Lyrics: ${Song.lyrics}</li>
				<br>
				<li>Duration Seconds: ${Song.durationInSeconds}</li>
				<br>
			</ul>
		</c:when>
		<c:when test="${! empty Songs}">
			<ul>
				<c:forEach var="x" items="${Songs}">

					<li>Name: ${x.name}</li>
					<br>
					<li>Lyrics: ${x.lyrics}</li>
					<br>
					<li>Duration Seconds: ${x.durationInSeconds}</li>
					<br>
				</c:forEach>
			</ul>
		</c:when> 
		<c:when test="${! empty Album}">
			<ul>

					<li>Name: ${Album.title}</li>
					<br>
					<li>Description: ${Album.description}</li>
					<br>
					<li>Release Date: ${Album.releaseDate}</li>
					<br>
			</ul>
		</c:when> 
		<c:when test="${! empty Albums}">
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
		</c:when> 
		<c:when test="${! empty Artist}">
			<ul>

					<li>Name: ${Artist.name}</li>
					<br>
					<li>Description: ${Artist.description}</li>
					<br>
			</ul>
		</c:when> 
		<c:when test="${! empty Artists}">
			<ul>
				<c:forEach var="x" items="${Artists}">

					<li>Name: ${x.name}</li>
					<br>
					<li>Description: ${x.description}</li>
					<br>
				</c:forEach>
			</ul>
		</c:when> 
		<c:otherwise>
			<p>No song found</p>
		</c:otherwise>
	</c:choose>
</body>
</html>