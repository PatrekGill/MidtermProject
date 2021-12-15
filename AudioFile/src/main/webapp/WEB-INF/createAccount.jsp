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
		<form action="createAccount" method="post">
			<tr>
				<td>Username</td>
				<td><input type="text" id="username" name="username" required></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" id="password" name="password" required></td>
			</tr>
			<tr>
				<td>Repeat Password</td>
				<td><input type="text" id="password-repeat"
					name="password-repeat" required></td>
			</tr>
			<tr>
				<td>First name</td>
				<td><input type="text" id="firstName" name="firstName"></td>
			</tr>
			<tr>
				<td>Last name</td>
				<td><input type="text" id="lastName" name="lastName"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" id="email" name="email"></td>
			</tr>
			<tr>
				<td><input type="submit" type="Create Account" /></td>
			</tr>
		</form>
	</tbody>
</table>
<jsp:include page="bootstrapFooter.jsp" />