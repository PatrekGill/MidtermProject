<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <!DOCTYPE html>
<html>
<body> -->
<jsp:include page="bootstrapHead.jsp" />
<h1>Song Information</h1>
<ul>
	<c:if test="${! empty Song}">
		<ul>
			<li>Name: "${Song.name}"</li>
			<br>
			<li>Lyrics: ${Song.lyrics}</li>
			<br>
			<li>Duration Seconds: ${Song.durationInSeconds}</li>
			<br>
			<c:forEach var="artist" items="${Song.artists}">
			<form action="artistProfile" method="get"></form>
				<li><a href="artistProfile?id=${artist.id}">${artist.name}</a></li>
			</c:forEach>
		</ul>
	</c:if>
</ul>
<!-- </body>
</html> -->
<jsp:include page="bootstrapFooter.jsp" />