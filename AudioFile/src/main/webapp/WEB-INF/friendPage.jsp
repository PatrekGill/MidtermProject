<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="bootstrapHead.jsp" />
<div class="col-sm-8 text-left">
	<div class="container-boy">
		<div>
			<c:choose>
				<c:when test="${ empty user1}">

					<h1>You do not have any friends listed</h1>

				</c:when>
				<c:when test="${user1 != null }">

					<div class="">
						<h2>Your Friend Account Details</h2>
						<p>First name: ${user1.firstName}</p>
						<p>Last name: ${user1.lastName }</p>
						<p>Account created on: ${user1.creationDateTime }</p>
						<p>Email: ${user1.email }</p>
						<p><a href="profile?id=${user1.id}">${user1.username}</a></p>
					</div>
			<div class="">
				<c:if test="${not empty albumsCreated}">
					<div class="albums-by-you">
						<h2>Friends Albums:</h2>
						<c:forEach items="${albumsCreated}" var="album">
							<p>
								<a href="album">${album.title }</a>
							</p>

						</c:forEach>
					</div>
				</c:if>
			</div>
				</c:when>
			</c:choose>
		</div>
	</div>
</div>
<jsp:include page="bootstrapFooter.jsp" />