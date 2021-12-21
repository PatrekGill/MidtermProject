<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="bootstrapHead.jsp" />
<div class="container-fluid">
	<div class="table-responsive">
		<div class="table-wrapper table-body editing-table">
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
							<td>Username:</td>
							<td><input type="text" id="username" name="username"
								required style="color: black"></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="text" id="password" name="password"
								required style="color: black"></td>
						</tr>
						
						<tr>
							<td>First name:</td>
							<td><input type="text" id="firstName" name="firstName"
								style="color: black"></td>
						</tr>
						<tr>
							<td>Last name:</td>
							<td><input type="text" id="lastName" name="lastName"
								style="color: black"></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><input type="text" id="email" name="email"
								style="color: black"></td>
						</tr>
						<tr>
							<td><input type="submit" type="Create Account"
								style="color: black" /></td>
						</tr>
					</form>
				</tbody>
			</table>
		</div>
	</div>
</div>
<jsp:include page="bootstrapFooter.jsp" />