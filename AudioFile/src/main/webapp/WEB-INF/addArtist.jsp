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
		<form action="addArtist" method="post">
			<tr>
				<td>Name</td>
				<td><input type="text" id="name" name="name" required></td>
			</tr>
			<tr>
				<td>Uer_id</td>
				<td><input type="number" id="user" name="user" ></td>
			</tr>
			<tr>
				<td>Create Date</td>
				<td><input type="date" id="createDate"
					name="createDate"  ></td>
			</tr>
			<tr>
				<td>Image Url</td>
				<td><input type="text" id="imageURL" name="imageURL"></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><input type="text" id="description" name="description"></td>
			</tr>
			<tr>
				<td><input type="submit" type="Create Account" /></td>
			</tr>
		</form>
	</tbody>
</table>
<jsp:include page="bootstrapFooter.jsp" />