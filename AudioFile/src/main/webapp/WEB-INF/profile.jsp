<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="bootstrapHead.jsp" />


<c:choose>
	<c:when test="${ empty user}">

		<div>
			<h1>Login or create Account</h1>
			<form action="login" method="post">
				<p>
					Username: <input class="textbox" name="username" />
				</p>
				<p>
					Password: <input class="textbox" name="password" />
				</p>
				<input type="submit" value="Log In">
			</form>
			<form action="createAccount" method="get">
			<input type="submit" value="Create Account">
			</form>
		</div>
	</c:when>
	<c:when test="${user != null }">

		<c:if test="${empty update }">
		<div>
			<h2>Your Account Details</h2>
			<ul>
				<li>First name: ${user.firstName}</li>
				<li>Last name: ${user.lastName }</li>
				<li>Account created on: ${user.creationDateTime }</li>
				<li>Email: ${user.email }</li>
				<li>Username: ${user.username }</li>
			</ul>
			<form action="deleteAccount" method="post">
		<input type="submit" value="Delete Account">
		</form>
		<form action="updateAccount" method="get">
		<input type="submit" value="Update Account">
		</form>
		</div>
		</c:if>
		<c:if test="${not empty update }">
		<div>
			<h2>Change Account Details</h2>
			<form action="updateAccount" method="post">
			<ul>
				<li>First Name: <input value="${user.firstName}" name="firstName"></li>
				<li>Last Name: <input value="${user.lastName }" name="lastName"></li>
				<li>Cannot change account create date: ${user.creationDateTime }</li>
				<li>Email: <input value="${user.email }" name="email"></li>
				<li>Username: ${user.username }</li>
			</ul>
			<input type="submit" value="Update Account">
			</form>
		</div>
		</c:if>
		<c:if test="${not empty albumsCreated}">
		<table>
		<tr>
		<th>Albums added by you</th>
		</tr>
			<c:forEach items="${albumsCreated}" var="album">
				<tr>
					<td><a href="album">${album.title }</a></td>
				</tr>

			</c:forEach>
		</table>
		</c:if>
	</c:when>
	
</c:choose>




<jsp:include page="bootstrapFooter.jsp" />