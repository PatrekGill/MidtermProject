<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Song</title>
<jsp:include page="bootstrapHead.jsp"/>
<div class="col-sm-8 text-left">
	<c:if test="${! empty Songs}">
		<h1>Song Information</h1>
		<ul>
			<c:forEach var="x" items="${Songs}">
				<form action="searchBySongName.do" method="GET"></form>
				<li><a href="searchBySongName.do?songName=${x.name}">${x.name}</a></li>
			</c:forEach>
		</ul>
	</c:if>

	<c:if test="${! empty Albums}">
		<h1>Album Information</h1>
		<ul>
			<c:forEach var="x" items="${Albums}">

				<li>${x.title}</li>
				<%-- <br>
				<li>Description: ${x.description}</li>
				<br>
				<li>Release Date: ${x.releaseDate}</li>
				<br> --%>
			</c:forEach>
		</ul>
	</c:if>

	<c:if test="${! empty Artists}">
		<h1>Artist Information</h1>
		<ul>
			<c:forEach var="x" items="${Artists}">

				<li>${x.name}</li>
				<img src="${x.imageUrl}">
				<%-- <li>${x.imageUrl}</li> --%>
				<%-- <br>
				<li>Description: ${x.description}</li>
				<br> --%>
			</c:forEach>
		</ul>
	</c:if>
	</div>
<jsp:include page="bootstrapFooter.jsp"/>
	