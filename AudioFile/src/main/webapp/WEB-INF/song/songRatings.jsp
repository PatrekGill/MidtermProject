<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../bootstrapHead.jsp" />

<c:choose>
	<c:when test="${empty song}">
		<h1>Could Not Locate Song, Sorry</h1>
	</c:when>
	<c:otherwise>

		<%-- picture, album, artist, and description --%>
		<div class="container-fluid">


			<%-- ------------------------------------------------
                Add Rating
            ------------------------------------------------ --%>
			<c:if test="${sessionScope.user != null}">
				<%-- testing code --%>
				<%-- <c:if test="${true}"> --%>
				<div class="table-responsive">
					<div class="table-wrapper table-body">
						<div class="table-title">
							<div class="row">
								<h2>Leave A Rating</h2>
							</div>
						</div>
						<form id="ratingPostForm" action="songRatings.do" method="POST">
							<input type="hidden" name="songId" value="${song.id }">
							<table class="music-table table-hover album-comment-box">
								<tbody>
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
						</form>


						<%-- ------------------------------------------------
                            Delete, Update, Or Post Button
                        ------------------------------------------------ --%>
						<form action="deleteRating.do" id="deleteRatingForm" method="POST">
							<input type="hidden" name="songId" value="${song.id }">
						</form>
						<c:choose>
							<c:when test="${userHasRating}">
								<button type="submit" form="ratingPostForm"
									class="btn btn-warning table-btn-major">Update Rating</button>
								<button type="submit" form="deleteRatingForm"
									class="btn btn-danger table-btn-minor">Delete Rating</button>
							</c:when>
							<c:otherwise>
								<button type="submit" form="ratingPostForm"
									class="btn btn-warning table-btn">Post Rating</button>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
			</c:if>


			<%-- ------------------------------------------------
                Ratings Table
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
									<c:forEach items="${songRatings}" var="rating">
										<tr>
											<td><a href="profile?id=${rating.user.id}"> <img
													class="user-image-md" src="${rating.user.imageURL}"
													alt="Profile Image">
											</a></td>
											<td class="ratingTable-score-text">${rating.rating} / 10
											</td>
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
                                                        ${rating.updateDateTime.dayOfMonth})
                                                    </c:if>
												</p>
												<p>${rating.description}</p>
											</td>
										</tr>

									</c:forEach>
									<c:if test="${ empty sessionScope.user }">
										<tr>
											<h4>
												<a href="login">To leave a rating please login or create
													an account</a>
											</h4>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr>No Ratings...
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>

					</table>
				</div>
			</div>

		</div>
	</c:otherwise>
</c:choose>


<jsp:include page="../scripts/tooltipScript.jsp" />

<jsp:include page="../bootstrapFooter.jsp" />
