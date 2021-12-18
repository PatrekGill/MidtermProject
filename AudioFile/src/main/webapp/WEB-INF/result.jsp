<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Song</title> --%>
<jsp:include page="bootstrapHead.jsp" />

<div class="row">
	<div class="container text-left text-manipulator">
	    <p>
			Music Just
		</p>
		<p style="padding-top: 10px">
			Got Better
		</p>
	</div>
</div>



	<c:choose>
		<c:when test="${!  NotPopulated  }">

			<%-- ------------------------------------------------
                Songs Table
            ------------------------------------------------ --%>
			<c:if test="${! empty Songs}">

				<jsp:include page="search/includes/songResults.jsp" />

			</c:if>


			<%-- ------------------------------------------------
                Albums Table
            ------------------------------------------------ --%>
			<c:if test="${! empty Albums}">
				<h1>Album Information</h1>
				<ul>
					<c:forEach var="album" items="${Albums}">
						<li>${album.title}</li>
					</c:forEach>
				</ul>
			</c:if>


			<%-- ------------------------------------------------
                Artists Table
            ------------------------------------------------ --%>
			<c:if test="${! empty Artists}">
				<h1>Artist Information</h1>
				<ul>
					<c:forEach var="artist" items="${Artists}">
						<form action="artistProfile" method="get"></form>
						<li><a href="artistProfile?id=${artist.id}">${artist.name}</a></li>
						<img src="${artist.imageUrl}">
					</c:forEach>
				</ul>
			</c:if>


		</c:when>
		<c:otherwise>
			<p>No such Data found, please try again</p>
		</c:otherwise>
	</c:choose>



<jsp:include page="bootstrapFooter.jsp" />
