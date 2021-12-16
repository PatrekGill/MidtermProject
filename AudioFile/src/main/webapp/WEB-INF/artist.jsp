<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>


<jsp:include page="bootstrapHead.jsp" />
<table>
	<tr>
		<td><img src="${artist.imageUrl }" width="100" height="200"></td>
	</tr>
	<tr>
		<td><h1>${artist.name }</h1></td>
	</tr>
	<tr>
		<td>${artist.description }</td>
	</tr>
</table>
	<table>
		<tr>
			<th>Top rated albums</th>
		</tr>
		<c:forEach items="${artistsHighestRatedAlbums}" var="album">
			<tr>
				<td><a href="album">${album.title }</a></td>
			</tr>
		</c:forEach>
	</table>
	<table>
		<tr>
			<th>Albums by ${artist.name}</th>
		</tr>
		<c:forEach items="${artist.albums }" var="album">
			<tr>
				<td><a href="album">${album.title }</a></td>
			</tr>
		</c:forEach>
	</table>
<jsp:include page="bootstrapFooter.jsp" />