<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="UTF-8">
<title>Profile</title>
<jsp:include page="bootstrapHead.jsp" />
</head>



		<div>
			<center>
				<h1>Profile</h1>
			</center>
			<tbody>
				<form action="login" method="post">
					<tr>
						<td>Login</td>
						<td><input class="textbox" name="username" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input class="textbox" name="password" /></td>
					</tr>
					<tr>
					<td><input type="submit" value="Log In" ></td>
					</tr>
			</tbody>
			</form>
		</div>
	


