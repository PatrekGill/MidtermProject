<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="bootstrapHead.jsp" />



<c:choose>
    <c:when test="${empty album}">
        <h1>Could Not Locate Album, Sorry</h1>
    </c:when>
    <c:otherwise>

        <%-- picture, album, artist, and description --%>
        <div class="container-fluid">
            <jsp:include page="albumHeader.jsp"/>

            <%-- Song table --%>
            <div class="table-responsive">
                <div class="table-wrapper table-body">
                    <div class="table-title">
    					<div class="row">
    						<h2>Songs</h2>
    					</div>
    				</div>
                    <table class="music-table table-hover">
                        <c:choose>
                            <c:when test="${not empty album.songs}">
                                <thead>
                                    <tr>
                                        <th class="albumTable-songNumber">#</th>
                                        <th class="albumTable-songName">SONG</th>
                                        <th class="albumTable-artists">ARTIST(s)</th>
                                        <th class="albumTable-duration">LENGTH (seconds)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${album.songs}" var="song" varStatus="i">
                                        <tr>
                                            <td>${i.count}</td>
                                            <td> <a href="song.do?songId=${song.id}">${song.name}</a> </td>
                                            <td>
                                                <c:forEach items="${song.artists}" var="artist" varStatus="j">
                                                    <c:choose>
                                                        <c:when test="${j.index == 0}">
                                                            <a class="albumTable-artistText" href="artist.do?artistId=${artist.id}">${artist.name}</a>
                                                        </c:when>
                                                        <c:otherwise>
                                    						<a class="albumTable-artistText" href="artist.do?artistId=${artist.id}">,${artist.name}</a>
                                    					</c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </td>
                                            <td>${song.durationInSeconds}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </c:when>
                            <c:otherwise>
        						<tbody>
        							<tr>
        								<td>No songs...</td>
        							</tr>
        						</tbody>
        					</c:otherwise>
                        </c:choose>
                    </table>
                </div>
            </div>


            <br>

            <%-- 10 Comments Table --%>
            <div class="table-responsive">
                <div class="table-wrapper table-body">
                    <div class="table-title">
    					<div class="row">
    						<h2>Latest Comments</h2>
    					</div>
    				</div>
                    <table class="music-table table-hover">

                        <thead>
                            <tr>
                                <th class="commentTable-userImage"></th>
                                <th class="commentTable-comment"></th>
                                <%-- <th class="commentTable-date"></th> --%>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty albumComments}">
                                    <c:forEach items="${albumComments}" var="comment" begin="0" end="9">
                                        <tr>
                                            <td>
                                                <a href="profile.do?userId=${comment.user.id}">
                                                    <img class="user-image-md" src="${comment.user.imageURL}" alt="Profile Image">
                                                </a>
                                            </td>
                                            <td class="commentTable-commentText">
                                                <p class="commentTable-dateText">
                                                    Posted by: ${comment.user.username}
                                                    <br>
                                                    On:
                                                    ${comment.commentDate.year}
                                                    ${comment.commentDate.month}
                                                    ${comment.commentDate.dayOfMonth}
                                                    <br>
                                                    <c:if test="${comment.updateDateTime != null && comment.updateDateTime != comment.commentDate}">
                                                        Edited On:
                                                        ${comment.updateDateTime.year}
                                                        ${comment.updateDateTime.month}
                                                        ${comment.updateDateTime.dayOfMonth}
                                                    </c:if>
                                                </p>
                                                <br>
                                                <p>${comment.comment}</p>
                                            </td>
                                            <%-- <td>${comment.commentDate}</td> --%>
                                        </tr>

                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        No Comments...
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>

                    </table>
                    <c:if test="${not empty albumComments}">

                        <form class="" action="albumComments.do" method="GET">
                            <input type="hidden" name="albumId" value="${album.id}">
                            <button type="submit" class="btn btn-warning table-btn">Show All Comments</button>
                        </form>

                    </c:if>
                </div>
            </div>


            <%-- 10 Ratings Table --%>
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
                                <c:when test="${not empty albumRatings}">
                                    <c:forEach items="${albumRatings}" var="rating" begin="0" end="9">
                                        <tr>
                                            <td>
                                                <a href="profile.do?userId=${rating.user.id}">
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
                                                    <br>
                                                    <c:if test="${rating.updateDateTime != null && rating.updateDateTime != rating.ratingDate}">
                                                        Edited On:
                                                        ${rating.updateDateTime.year}
                                                        ${rating.updateDateTime.month}
                                                        ${rating.updateDateTime.dayOfMonth}
                                                    </c:if>
                                                </p>
                                                <br>
                                                <p>${rating.description}</p>
                                            </td>
                                            <%-- <td>${comment.commentDate}</td> --%>
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
                    <c:if test="${not empty albumRatings}">

                        <form class="" action="albumRatings.do" method="GET">
                            <input type="hidden" name="albumId" value="${album.id}">
                            <button type="submit" class="btn btn-warning table-btn">Show All Ratings</button>
                        </form>

                    </c:if>
                </div>
            </div>

        </div>

    </c:otherwise>
</c:choose>




<jsp:include page="bootstrapFooter.jsp" />
