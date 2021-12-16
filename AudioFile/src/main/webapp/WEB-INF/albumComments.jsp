<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="bootstrapHead.jsp" />

<c:choose>
    <c:when test="${empty album}">
        <h1>Could Not Locate Album, Sorry</h1>
    </c:when>
    <c:otherwise>

        <%-- picture, album, artist, and description --%>
        <div class="container-fluid">
            <div class="row">
                <div class="container">
                    <c:if test="${not empty album.imageURL}">
                        <div class="col-xs-6 col-sm-7 col-md-6 col-lg-5 albumImage-div">
                                <a href="album.do?albumId=${album.id}">
                                    <img class="albumImage-md" src="${album.imageURL}" alt="image of album">
                                </a>
                        </div>
                    </c:if>
                    <div class="col-xs-10 col-sm-6 col-md-4 col-lg-5 albumText">
                        <h1 class="albumText-title">
                            <a href="album.do?albumId=${album.id}">${album.title}</a>
                        </h1>

                        <c:if test="${not empty album.artist}">
                            <%-- Must be changed to artist page --%>
                            <a class="albumText-artist" href="artist.do?artistId=${album.artist.id}">${album.artist.name}</a>
                            <br>
                        </c:if>

                        <a class="albumText-artist-sm" href="showAlbumRatings.do?artistId=${album.artist.id}">Average Rating: ${averageRating} / 10</a>

                        <p>${album.description}</p>
                    </div>
                </div>

            </div>

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
                                                <p class="commentTable-dateText" style="font-size:14px">
                                                    Posted by: ${comment.user.username} - On:
                                                    ${comment.commentDate.year}
                                                    ${comment.commentDate.month}
                                                    ${comment.commentDate.dayOfMonth}
                                                </p>
                                                <c:if test="${comment.updateDateTime != null}">
                                                    <p>
                                                        Edited On:
                                                        ${comment.updateDateTime.year}
                                                        ${comment.updateDateTime.month}
                                                        ${comment.updateDateTime.dayOfMonth}
                                                    </p>
                                                </c:if>
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
                </div>
            </div>
        </div>

    </c:otherwise>
</c:choose>




<jsp:include page="bootstrapFooter.jsp" />
