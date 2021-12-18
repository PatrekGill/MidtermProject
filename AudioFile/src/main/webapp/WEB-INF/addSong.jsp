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
		<form action="addSong" method="post">
			<tr>
				<th>Name</th>
				<td><input type="text" id="name" name="name" required style ="color:black"></td>
			</tr>
			<tr>
				<th>Lyrics</th>
				<td><input type="text" id="lyrics" name="lyrics" style ="color:black"></td>
			</tr>
			<tr>
				<th>Duration Seconds</th>
				<td><input type="number" id="durationInSeconds" name="durationInSeconds" style ="color:black"></td>
			</tr>
			<tr>
				<td><input type="submit" type="Create Song" style ="color:black" /></td>
			</tr>
		</form>
	</tbody>
</table>
</div>
<jsp:include page="bootstrapFooter.jsp" />