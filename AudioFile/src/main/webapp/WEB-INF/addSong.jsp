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
		<form action="addSong" method="post">
			<tr>
				<td>Name</td>
				<td><input type="text" id="name" name="name" required></td>
			</tr>
			<tr>
				<td>Lyrics</td>
				<td><input type="text" id="lyrics" name="lyrics" ></td>
			</tr>
			<tr>
				<td>Create Date</td>
				<td><input type="date" id="createDate"
					name="createDate" required></td>
			</tr>
			<tr>
				<td>Duration Seconds</td>
				<td><input type="number" id="durationInSeconds" name="durationInSeconds"></td>
			</tr>
		</form>
	</tbody>
</table>
<jsp:include page="bootstrapFooter.jsp" />