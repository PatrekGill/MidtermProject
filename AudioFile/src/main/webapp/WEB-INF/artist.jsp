<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>


<jsp:include page="bootstrapHead.jsp" />
<div class="col-sm-8 text-left">
	<div class="artist-text">
		<div class="artist-spacer">
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
		</div>
		<br>
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
		<div class="">
			<strong>Albums by ${artist.name}</strong>
			<c:forEach items="${artist.albums }" var="album">
				<p>
					<a href="album">${album.title }</a>
				</p>
			</c:forEach>
		</div>
	</div>
</div>
<jsp:include page="bootstrapFooter.jsp" />