<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="bootstrapHead.jsp" />


<c:choose>
	<c:when test="${ empty user}">

		<div>
			<h1>Profile</h1>
			<form action="login" method="post">
				<p>
					Username: <input class="textbox" name="username" />
				</p>
				<p>
					Password: <input class="textbox" name="password" />
				</p>
				<input type="submit" value="Log In">
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
				<li>Username: ${user.username }</li>
			</ul>

		</div>
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