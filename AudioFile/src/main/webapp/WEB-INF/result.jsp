<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Song</title>
<jsp:include page="bootstrapHead.jsp" />
<div class="col-sm-8 text-left">
	<c:choose>
		<c:when test="${!  NotPopulated  }">
			<c:if test="${! empty Songs}">
				<h1>Song Information</h1>
				<ul>
					<c:forEach var="song" items="${Songs}">
						<li><a href="searchBySongName.do?songName=${song.name}">${song.name}</a></li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${! empty Albums}">
				<h1>Album Information</h1>
				<ul>
					<c:forEach var="album" items="${Albums}">
						<li><a href="album.do?albumId=${album.id }">${album.title }</a></li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${! empty Artists}">
				<h1>Artist Information</h1>
				<ul>
					<c:forEach var="artist" items="${Artists}">
						<li><a href="artistProfile?id=${artist.id}">${artist.name}</a></li>
						<img src="${artist.imageUrl}">
					</c:forEach>
				</ul>
			</c:if>
		</c:when>
		<c:otherwise>
			<p>No such Data found, please try again</p>
		</c:otherwise>
	</c:choose>
</div>
<jsp:include page="bootstrapFooter.jsp" />