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
		<form action="addArtist" method="post">
			<tr>
				<th>Name</th>
				<td><input type="text" id="name" name="name" required style ="color:black"></td>
			</tr>
			<tr>
				<th>User Id</th>
				<td><input type ="number" id ="user.id" name ="user.id" value ="${user.id }" style ="color:black"></td>
			</tr>
			<tr>
				<th>Image Url</th>
				<td><input type="text" id="imageURL" name="imageURL" style ="color:black"></td>
			</tr>
			<tr>
				<th>Description</th>
				<td><input type="text" id="description" name="description" style ="color:black"></td>
			</tr>
			<tr>
				<td><input type="submit" type="Create Artist" style ="color:black" /></td>
			</tr>
		</form>
	</tbody>
</table>
<jsp:include page="bootstrapFooter.jsp" />