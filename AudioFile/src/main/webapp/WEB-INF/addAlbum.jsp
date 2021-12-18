<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="bootstrapHead.jsp" />
<div class="col-sm-8 text-left">
<table class="createAccount" id="create-account">
	<thead>
		<tr>
			<th>Field</th>
			<th>Value</th>
		</tr>
	</thead>
	<tbody>
		<form action="addAlbum" method="post">
			<tr>
				<th>Title</th>
				<td><input type="text" id="title" name="title" required
				style = "color:black"
				></td>
			</tr>
			<tr>
				<th>Description</th>
				<td><input type="text" id="description" name="description" style ="color:black"></td>
			</tr>
			<tr>
				<th>Release Date</th>
				<td><input type="date" id="releaseDate" name="releaseDate"
					required style ="color:black"></td>
			</tr>
			<tr>
				<th>Image Url</th>
				<td><input type="text" id="imageURL" name="imageURL" style ="color:black"></td>
			</tr>
			<div class ="spacer" >
			<tr>
			<td>
				<label for="names">Artist Name</label></td>
				<td>
				<select name="names" id="names">
					<option value="Jimmy Buffett">Jimmy Buffett</option>
					<option value="Mac DeMarco">Mac DeMarco</option>
					<option value="Adele">Adele</option>
					<option value="Miles Davis">Miles Davis</option>
				</select></td>
				
			</tr>
			</div>
		<tr>
			<td><input type="submit" type="Create Album" style ="color:black"/></td>
		</tr>
		</form>
	</tbody>
</table>
</div>
<jsp:include page="bootstrapFooter.jsp" />