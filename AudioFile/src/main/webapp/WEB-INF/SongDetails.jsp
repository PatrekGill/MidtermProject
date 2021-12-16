<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="bootstrapHead.jsp" />
<h1>Song Information</h1>
<ul>
	<c:if test="${! empty Song}">
		<ul>
			<p>Name: ${Song.name}</p>

			<p align="justify">Lyrics: ${Song.lyrics}</p>
			<p>Duration Seconds: ${DurationSeconds}</p>
			<c:forEach var="y" items="${Song.artists}">
				<p align ="center">Artist Name: ${y.name}</p>
			</c:forEach>
			<p align ="center">Create Date: ${CreateDate}</p>
			<p align ="center">Album: ${Album.title}</p>

		</ul>
	</c:if>
	<c:if test="${! empty RateDate }">
		<p align ="center">Rating Points: ${Rating}</p>
		<p align ="center">Rating By: ${User} at ${RateDate}</p>
		<p align ="center">Rating Comments: ${Comments}</p>
	</c:if>
</ul>
<jsp:include page="bootstrapFooter.jsp" />