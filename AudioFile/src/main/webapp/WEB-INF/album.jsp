<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="bootstrapHead.jsp" />



<c:choose>
    <c:when test="${empty album}">
        <h1>Could Not Locate Album, Sorry</h1>
    </c:when>
    <c:otherwise>

        <div class="container-fluid">
            <div class="row">
                <div class="container">
                    <c:if test="${not empty album.imageURL}">
                        <div class="col-xs-6 col-sm-7 col-md-6 col-lg-5 albumImage-div">
                                <img class="albumImage-md" src="${album.imageURL}" alt="image of album">
                        </div>
                    </c:if>
                    <div class="col-xs-10 col-sm-6 col-md-4 col-lg-5 albumText">
                        <h1 class="albumText-title">${album.title}</h1>

                        <c:if test="${not empty album.artist}">
                            <%-- Must be changed to artist page --%>
                            <a class="albumText-artist" href="artist.do?artistId=${album.artist.id}">${album.artist.name}</a>
                        </c:if>

                        <p>${album.description}</p>
                    </div>
                </div>

            </div>

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
                                <th class="commentTable-date"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty albumComments}">
                                    <c:forEach items="${albumComments}" var="comment" varStatus="i">
                                        <tr>
                                            <td>
                                                <a href="profile.do?userId=${comment.user.id}">
                                                    <img class="user-image-md" src="${comment.user.imageURL}" alt="Profile Image">
                                                </a>
                                            </td>
                                            <td>${comment.comment}</td>
                                            <td>${comment.commentDate}</td>
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

                        <c:if test="${not empty albumComments}">
                            <tfoot>
                                <tr>
                                    <td>
                                        <form type=submit value="Show All" style="width:100%" action="index.html" method="GET"></form>
                                    </td>
                                </tr>
                            </tfoot>
                        </c:if>

                    </table>
                </div>
            </div>

        </div>

    </c:otherwise>
</c:choose>




<jsp:include page="bootstrapFooter.jsp" />
