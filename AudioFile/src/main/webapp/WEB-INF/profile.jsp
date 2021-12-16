<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="bootstrapHead.jsp" />
<div class="center-alignment">
<c:if test="${not empty albumsCreated}">
				<div class="albums-by-you">
					<table>
						<tr>
							<th>Your Albums:</th>
						</tr>
						<c:forEach items="${albumsCreated}" var="album">
							<tr>
								<td><a href="album">${album.title }</a></td>
							</tr>

						</c:forEach>
					</table>
				</div>
		</c:if>
				</div>
<div>
<c:choose>
	<c:when test="${ empty user}">

		<div class="center-alignment">
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
			<div class="center-alignment">
				<h2>Your Account Details</h2>
				<p>First name: ${user.firstName}</p>
				<p>Last name: ${user.lastName }</p>
				<p>Account created on: ${user.creationDateTime }</p>
				<p>Email: ${user.email }</p>
				<p>Username: ${user.username }</p>
				<div class="account-design">
					<form action="deleteAccount" method="post">
						<input type="submit" value="Delete Account">
					</form>
					<div class="spacer">
						<form action="updateAccount" method="get">
							<input type="submit" value="Update Account">
						</form>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty update }">
			<div>
				<h2>Change Account Details</h2>
				<form action="updateAccount" method="post">
					<ul>
						<li>First Name: <input value="${user.firstName}"
							name="firstName"></li>
						<li>Last Name: <input value="${user.lastName }"
							name="lastName"></li>
						<li>Cannot change account create date:
							${user.creationDateTime }</li>
						<li>Email: <input value="${user.email }" name="email"></li>
						<li>Username: ${user.username }</li>
					</ul>
					<input type="submit" value="Update Account">
				</form>
			</div>
		</c:if>
	</c:when>

</c:choose>
</div>



<jsp:include page="bootstrapFooter.jsp" />