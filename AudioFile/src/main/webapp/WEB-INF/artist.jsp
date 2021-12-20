<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>


<jsp:include page="bootstrapHead.jsp" />
<div class="row">
	<div class="container">

		<c:if test="${not empty artist.imageUrl}">
			<div class="col-xs-6 col-sm-7 col-md-6 col-lg-5 albumImage-div">

				<img class="albumImage-md" src="${artist.imageUrl}"
					alt="image of artist">
			</div>
		</c:if>

		<div class="col-xs-10 col-sm-6 col-md-4 col-lg-5 albumText">
			<h1 class="albumText-title">${artist.name }</h1>
			<br>
			<p>${artist.description }</p>
		</div>
	</div>
</div>

<div class="container-fluid">
	<%-- ------------------------------------------------
		Edit Button
	------------------------------------------------ --%>
	<div class="edit-entity-button">
		<c:if test="${not empty sessionScope.user && artist.user == sessionScope.user}">
			<form action="editArtist" method="GET">
				<input type="hidden" name="artistId" value="${artist.id}">
				<button type="submit" class="btn btn-warning table-btn">Edit Artist</button>
			</form>
		</c:if>
	</div>
</div>

<div class="table-responsive">
	<div class="table-wrapper table-body">
		<div class="table-title">
			<div class="row">
				<h2>Albums</h2>
			</div>
		</div>
		<table class="music-table table-hover">
		<c:forEach items="${artist.albums }" var="album">
		<tr>
		<td><a href="album.do?albumId=${album.id }">${album.title }</a></td>
		</tr>
		</c:forEach>
		</table>
	</div>
</div>

<!-- need to add another table for albums in average ranking order
also may need to shrink the table to not take up the whole page
 !!!!!!BE SURE TO CHANGE THE CLASS NAME IN CSS AND COPY THIS ONE AND ALTER IT
      BECAUSE IF YOU CHANGE THIS CLASS IN CSS IT WILL AFFECT THE ALBUM PAGE!!!!!!!
-->
<jsp:include page="bootstrapFooter.jsp" />
