<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="UTF-8">
<title>Profile</title>
<jsp:include page="bootstrapHead.jsp" />
</head>

<c:choose>
	<c:when test="${ empty user}">

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
						<td><input type="submit" value="Log In"></td>
					</tr>
			</tbody>
			</form>
		</div>
		</c:when>
		<c:when test="${user != null }">

		<div>
		<h2>Your Account Details</h2>
      <ul>
        <li>First name: ${user.firstName}</li>
       <li>Last name: ${user.lastName }</li>
       <li>Account created on: ${user.creationDateTime }</li>
       <li>Email: ${user.email }</li>
       <li>Username: ${user.username } </li>
      </ul>
		</div>
		</c:when>
		
		
		</c:choose>