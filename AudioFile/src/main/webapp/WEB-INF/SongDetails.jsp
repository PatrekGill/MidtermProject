<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="bootstrapHead.jsp" />
<h1>Song Information</h1>
<ul>
	<c:if test="${! empty Song}">
		

			<p>Name: ${Song.name}</p>
			
			<p align="justify">Lyrics: ${Song.lyrics}</p>
			<p>Duration Seconds: ${DurationSeconds}</p>
			<c:forEach var="artist" items="${Song.artists}">
			<form action="artistProfile" method="get"></form>
        <p align="center"><a href="artistProfile?id=${artist.id}">${artist.name}</a></p>

			</c:forEach>
			<c:forEach var="album" items="${Song.albums }">
			<p align="center">Create Date: ${album.creationDateTime}</p>
			<p><a href="album.do?albumId=${album.id }">${album.title }</a></p>
			</c:forEach>

		
	</c:if>
	<c:if test="${! empty RateDate }">
		<p align="center">Rating Points: ${Rating}</p>
		<p align="center">Rating By: ${User} at ${RateDate}</p>
		<p align="center">Rating Comments: ${Comments}</p>
	</c:if>
</ul>
<jsp:include page="bootstrapFooter.jsp" />