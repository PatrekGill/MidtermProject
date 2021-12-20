<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../bootstrapHead.jsp" />



<div class="row">
	<div class="container">
		<div class="col-xs-10 col-sm-6 col-md-4 col-lg-5 albumText">
			<h1 class="albumText-title">${Song.name }</h1>
			<p>By</p>
			<c:forEach items="${artists }" var="artist">
				<a class="albumText-artist" href="artistProfile?id=${artist.id}">${artist.name}</a>
			</c:forEach>
			 <a class="albumText-artist-sm" href="songRatings.do?songId=${Song.id}">Average Rating: ${averageRating} / 10</a>
			<p>${Song.lyrics }</p>
		</div>
	</div>
</div>

<div class="container-fluid">
	<%-- ------------------------------------------------
		Edit Button
	------------------------------------------------ --%>
	<div class="edit-entity-button">
		<c:if test="${not empty sessionScope.user && Song.user == sessionScope.user}">
			<form action="editSong" method="GET">
				<input type="hidden" name="songId" value="${Song.id}">
				<button type="submit" class="btn btn-warning table-btn">Edit Song</button>
			</form>
		</c:if>
	</div>
</div>

<div class="table-responsive">
	<div class="table-wrapper table-body">
		<div class="table-title">
			<div class="row">
				<h2>Albums</h2>
			</div>
		</div>
		<table class="music-table table-hover">
			<c:forEach var="album" items="${albums }">
				<tr>
					<td><a href="album.do?albumId=${album.id }">${album.title }</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

 <%-- ------------------------------------------------
                10 latest Ratings
            ------------------------------------------------ --%>
            <div class="table-responsive">
                <div class="table-wrapper table-body">
                    <div class="table-title">
    					<div class="row">
    						<h2>Latest Ratings</h2>
    					</div>
    				</div>
                    <table class="music-table table-hover">

                        <thead>
                            <tr>
                                <th class="ratingTable-userImage"></th>
                                <th class="ratingTable-score"></th>
                                <th class="ratingTable-description"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty songRatings}">
                                    <c:forEach items="${songRatings}" var="rating" begin="0" end="9">

                                        <tr>
                                            <td>
                                                <a href="profile?id=${rating.user.id}">
                                                    <img class="user-image-md" src="${rating.user.imageURL}" alt="Profile Image">
                                                </a>
                                            </td>
                                            <td class="ratingTable-score-text">
                                                ${rating.rating} / 10
                                            </td>
                                            <td class="commentTable-commentText">
                                                <p class="commentTable-dateText">
                                                    Posted by: ${rating.user.username}
                                                    <br>
                                                    On:
                                                    ${rating.ratingDate.year}
                                                    ${rating.ratingDate.month}
                                                    ${rating.ratingDate.dayOfMonth}
                                                    <c:if test="${rating.updateDateTime != null && rating.updateDateTime != rating.ratingDate}">
                                                        (Edited On:
                                                        ${rating.updateDateTime.year}
                                                        ${rating.updateDateTime.month}
                                                        ${rating.updateDateTime.dayOfMonth}))
                                                    </c:if>
                                                </p>
                                                <p>${rating.description}</p>
                                            </td>
                                        </tr>

                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        No Ratings...
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>

                    </table>
                    <c:if test="${not empty songRatings}">

                        <form class="" action="songRatings.do" method="GET">
                            <input type="hidden" name="albumId" value="${song.id}">
                            <button type="submit" class="btn btn-warning table-btn">Show All Ratings</button>
                        </form>

                    </c:if>
                </div>
            </div>


<%-- <h1>Song Information</h1>
<ul>
	<c:if test="${! empty Song}">


			<p>Name: ${Song.name}</p>

			<p align="justify">Lyrics: ${Song.lyrics}</p>
			<p>Duration Seconds: ${DurationSeconds}</p>
			<c:forEach var="artist" items="${Song.artists}">
			<form action="artistProfile" method="get"></form>
        <p align="center"><a href="artistProfile?id=${artist.id}">${artist.name}</a></p>

			</c:forEach>
			<c:forEach var="album" items="${Song.albums }">
			<p align="center">Create Date: ${album.creationDateTime}</p>
			<p><a href="album.do?albumId=${album.id }">${album.title }</a></p>
			</c:forEach>


	</c:if>
	<c:if test="${! empty RateDate }">
		<p align="center">Rating Points: ${Rating}</p>
		<p align="center">Rating By: ${User} at ${RateDate}</p>
		<p align="center">Rating Comments: ${Comments}</p>
	</c:if>
</ul> --%>

<jsp:include page="../scripts/tooltipScript.jsp" />
<jsp:include page="../bootstrapFooter.jsp" />
