<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="bootstrapHead.jsp" />
<table id="create-account">
	<thead>
		<tr>
			<th>Field</th>
			<th>Value</th>
		</tr>
	</thead>
	<tbody>
		<form action="addAlbum" method="post">
			<tr>
				<td>Title</td>
				<td><input type="text" id="title" name="title" required></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><input type="text" id="description" name="description" ></td>
			</tr>
			<tr>
				<td>Release Date</td>
				<td><input type="date" id="releaseDate"
					name="releaseDate" required></td>
			</tr>
			<tr>
				<td>Image Url</td>
				<td><input type="text" id="imageURL" name="imageURL"></td>
			</tr>
			<tr>
				<td>User Id</td>
				<td>${user.id }</td>
			</tr>
			<%-- <tr>
				<td>Artist Id</td>
				<td>${user.artist.id }</td>
			</tr> --%>
			<tr>
				<td><input type="submit" type="Create Album" /></td>
			</tr>
		</form>
	</tbody>
</table>

<jsp:include page="bootstrapFooter.jsp" />