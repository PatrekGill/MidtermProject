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

            <%-- Add Comment --%>
            <%-- <c:if test="${sessionScope.user != null}"> --%>
            <%-- testing code --%>
            <c:if test="${true}">
                <div class="table-responsive">
                    <div class="table-wrapper table-body">
                        <div class="table-title">
                            <div class="row">
                                <h2>Leave A Comment</h2>
                            </div>
                        </div>
                        <form action="albumComments.do" id="addAlbumComment" method="POST">
                            <input type="hidden" name="albumId" value="${album.id }">
                            <table class="music-table table-hover album-comment-box">
                                <tbody>
                                    <tr>
        								<td>
        									<textarea class="form-control" rows="5" id="comment" name="commentText" placeholder="Add Comment..."></textarea>
        								</td>
        							</tr>
                                </tbody>
                            </table>
                            <button type="submit" class="btn btn-warning table-btn">Post Comment</button>
                        </form>
                    </div>
                </div>
            </c:if>


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
