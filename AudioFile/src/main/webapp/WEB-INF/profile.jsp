<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="bootstrapHead.jsp" />
<div class="col-sm-8 text-left">
	<div class="container-boy">
		<div>
			<c:choose>
				<c:when test="${ empty user}">

					<h1>Login or create Account</h1>
					<div class="account-design">
						<form action="login" method="post">
							<label for="username">Username: </label>&nbsp;<input
								class="login-textbox" name="username" style="color:black"/><label for="password">&nbsp;Password:
							</label>&nbsp;<input class="password-textbox" name="password" style="color:black" /><br>
							<br> <input type="submit" value="Log In" style="color:black">
						</form>
						<form action="createAccount" method="get">
							<div class="spacer">
								<input type="submit" value="Create Account" style="color:black">
							</div>
						</form>
					</div>
				</c:when>
				<c:when test="${not empty profile }">
					<div class="">
						<h2>${profile.username }'sProfile</h2>
						<p>First name: ${profile.firstName}</p>
						<p>Account created on: ${user.creationDateTime }</p>
						<c:if test="${not empty albumsCreatedByUser }">
							<div class="albums-created">
								<h2>Albums Created by ${profile.username }</h2>
								<c:forEach items="${albumsCreatedByUser }" var="album">
									<p>
										<a href="album.do?albumId=${album.id }">${album.title }</a>
									</p>
								</c:forEach>
							</div>
						</c:if>
					</div>

				</c:when>
				<c:when test="${user != null }">

					<c:if test="${empty update }">
						<div class="">
							<h2>Your Account Details</h2>
							<p>First name: ${user.firstName}</p>
							<p>Last name: ${user.lastName }</p>
							<p>Account created on: ${user.creationDateTime }</p>
							<p>Email: ${user.email }</p>
							<p>Username: ${user.username }</p>
							<div class="account-design">
								<form action="deleteAccount" method="post">
									<input type="submit" value="Delete Account" style="color:black">
								</form>
								<div class="spacer">
									<form action="updateAccount" method="get">
										<input type="submit" value="Update Account" style="color:black">
									</form>
								</div>
								<div class="spacer">
									<form action="friendList" method="get">
										<input type="submit" value="Friend List" style="color:black"	>
									</form>
								</div>
							</div>
						</div>
						<div class="">
							<c:if test="${not empty albumsCreated}">
								<div class="albums-created">
									<h2>Your Albums:</h2>
									<c:forEach items="${albumsCreated}" var="album">
										<p>
											<a href="album.do?albumId=${album.id }">${album.title }</a>
										</p>

									</c:forEach>
								</div>
							</c:if>
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
	</div>
</div>
</div>


<jsp:include page="bootstrapFooter.jsp" />