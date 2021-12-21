<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../bootstrapHead.jsp" />



<div class="row">
	<div class="container">

		<c:forEach items="${Song.artists }" var="artist">

			<div class="col-xs-6 col-sm-7 col-md-6 col-lg-5 albumImage-div">

				<img class="albumImage-md" src="${artist.imageUrl}"
					alt="image of artist">
			</div>
		</c:forEach>

		<div class="col-xs-10 col-sm-6 col-md-4 col-lg-5 albumText">
			<h1 class="albumText-title">${Song.name }</h1>
			<p>By</p>
			<c:forEach items="${artists }" var="artist">
				<a class="albumText-artist" href="artistProfile?id=${artist.id}">${artist.name}</a>
			</c:forEach>
			<br> <a class="albumText-artist-sm"
				href="songRatings.do?songId=${Song.id}">Average Rating:
				${averageRating} / 10</a>
			<p>Lyrics:</p>
			<p>${Song.lyrics }</p>
		</div>
	</div>
</div>

<div class="container-fluid">
	<%-- ------------------------------------------------
		Edit Button
	------------------------------------------------ --%>
	<div class="edit-entity-button">
		<c:if
			test="${not empty sessionScope.user && Song.user == sessionScope.user}">
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
			<tbody>
				<c:forEach var="album" items="${albums }">
					<tr>
						<td><a href="album.do?albumId=${album.id}"><img
								class="albumImage-sm" src="${album.imageURL }"
								alt="image of album"></a></td>
						<td><a href="album.do?albumId=${album.id}">${album.title }</a></td>
					</tr>
				</c:forEach>
			</tbody>
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
								<td><a href="profile?id=${rating.user.id}"> <img
										class="user-image-md" src="${rating.user.imageURL}"
										alt="Profile Image">
								</a></td>
								<td class="ratingTable-score-text">${rating.rating}/10</td>
								<td class="commentTable-commentText">
									<p class="commentTable-dateText">
										Posted by: ${rating.user.username} <br> On:
										${rating.ratingDate.year} ${rating.ratingDate.month}
										${rating.ratingDate.dayOfMonth}
										<c:if
											test="${rating.updateDateTime != null && rating.updateDateTime != rating.ratingDate}">
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
							<h3>No Ratings...</h3>
							<c:if test="${ empty sessionScope.user }">
								<br>
								<h4>
									<a href="login">To leave a rating please login or create an
										account</a>
								</h4>
							</c:if>
						</tr>
						<c:if test="${sessionScope.user != null }">
							<form id="ratingPostForm" action="songRatings.do" method="POST">
								<input type="hidden" name="songId" value="${Song.id }">
								<table class="music-table table-hover album-comment-box">
									<tbody>
										<tr>
											<div class="table-title">
												<div class="row">
													<h2>Leave A Rating</h2>
												</div>
											</div>
										</tr>
										<tr>
											<td>
												<div class="form-group">

													<label for="ratingNumber">Rating</label> <select
														class="form-control" name="ratingNumber">

														<c:forTokens items="1,2,3,4,5,6,7,8,9,10" delims=","
															var="i">

															<c:choose>
																<c:when
																	test="${(userHasRating && i == usersRating.rating) || (!userHasRating && i == 10)}">
																	<option value="${i}" selected>${i}</option>
																</c:when>
																<c:otherwise>
																	<option value="${i}">${i}</option>
																</c:otherwise>
															</c:choose>

														</c:forTokens>
													</select>

												</div>
											</td>
										</tr>
										<tr>
											<td><c:choose>
													<c:when test="${userHasRating}">
														<textarea class="form-control" rows="5" name="ratingText"
															placeholder="Add Comment To Rating...">${usersRating.description}</textarea>
													</c:when>
													<c:otherwise>
														<textarea class="form-control" rows="5" name="ratingText"
															placeholder="Add Comment To Rating..."></textarea>
													</c:otherwise>
												</c:choose></td>
										</tr>

									</tbody>
								</table>
								<button type="submit" form="ratingPostForm"
									class="btn btn-warning table-btn">Post Rating</button>
							</form>
						</c:if>
					</c:otherwise>
				</c:choose>

			</tbody>
		</table>
		<c:if test="${not empty songRatings }">
			<form class="" action="songRatings.do" method="GET">
				<input type="hidden" name="songId" value="${Song.id}">
				<button type="submit" class="btn btn-warning table-btn">Show
					All Ratings</button>
			</form>
			</c:if>


	</div>
</div>



<jsp:include page="../scripts/tooltipScript.jsp" />
<jsp:include page="../bootstrapFooter.jsp" />
