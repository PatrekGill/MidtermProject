<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../../bootstrapHead.jsp" />

<c:choose>
    <c:when test="${empty originalComment}">
        <h1>Could Not Locate Your Comment, Sorry</h1>
    </c:when>
    <c:otherwise>

        <%-- picture, album, artist, and description --%>
        <div class="container-fluid">

            <jsp:include page="../includes/albumHeader.jsp"/>

            <%-- ------------------------------------------------
                Original Comment
            ------------------------------------------------ --%>
            <div class="table-responsive">
                <div class="table-wrapper table-body">
                    <div class="table-title">
                        <div class="row">
                            <c:choose>
                                <c:when test="${userOwnsComment}">
                                    <h2>Edit Comment</h2>
                                </c:when>
                                <c:otherwise>
                                    <h2>Original Comment</h2>
                                </c:otherwise>
                            </c:choose>
                            <div class="">
        						<a href="albumComments.do?albumId=${originalComment.album.id}" class="btn btn-primary">
        							<i class="glyphicon glyphicon-circle-arrow-left"></i>
        							<span>Back To All Comments</span>
        						</a>
        					</div>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${userOwnsComment}">
                            <%-- ------------------------------------------------
                                Form to edit original comment if owned
                            ------------------------------------------------ --%>
                            <form id="commentEditForm" action="albumComments.do" method="POST">
                                <input type="hidden" name="editCommentId" value="${originalComment.id }">
                                <table class="music-table table-hover album-comment-box">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <textarea class="form-control" rows="5" name="commentText" placeholder="Comment text...">${originalComment.comment}</textarea>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>

                            <%-- ------------------------------------------------
                                Delete, update, or post button
                            ------------------------------------------------ --%>
                            <form action="deleteComment.do" id="deleteCommentForm" method="POST">
                                <input type="hidden" name="commentId" value="${originalComment.id }">
                            </form>
                            <%-- seperated this button so the two delete and update can be side by side --%>
                            <button type="submit" form="commentEditForm" class="btn btn-warning table-btn-major">Update Comment</button>
                            <button type="submit" form="deleteCommentForm" class="btn btn-danger table-btn-minor">Delete</button>

                        </c:when>
                        <c:otherwise>
                            <%-- ------------------------------------------------
                                display original comment
                            ------------------------------------------------ --%>
                            <table class="music-table table-hover album-comment-box">
                                <tbody>
                                    <tr>
                                        <td>
                                            ${originalComment.comment}
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>


                </div>
            </div>


            <%-- ------------------------------------------------
                Reply To Comment Box
            ------------------------------------------------ --%>
            <c:if test="${sessionScope.user != null}">
            <%-- testing code --%>
            <%-- <c:if test="${true}"> --%>
                <div class="table-responsive">
                    <div class="table-wrapper table-body">
                        <div class="table-title">
                            <div class="row">
                                <h2>Reply To Thread</h2>
                            </div>
                        </div>

                        <form action="commentThread.do" method="POST">
                            <input type="hidden" name="replyToId" value="${originalComment.id }">

                            <table class="music-table table-hover album-comment-box">
                                <tbody>
                                    <tr>
                                        <td>
                                            <textarea class="form-control" rows="5" name="commentText" placeholder="Reply To Comment..."></textarea>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>

                            <button type="submit" class="btn btn-warning table-btn">Post Reply</button>
                        </form>

                    </div>
                </div>
            </c:if>


            <%-- ------------------------------------------------
                Replies table
            ------------------------------------------------ --%>
            <div class="table-responsive">
                <div class="table-wrapper table-body">
                    <div class="table-title">
    					<div class="row">
    						<h2>Latest Replies</h2>
    					</div>
    				</div>
                    <table class="music-table table-hover">

                        <thead>
                            <tr>
                                <th class="commentTable-userImage"></th>
                                <th class="commentTable-comment"></th>
                                <th class="commentTable-icons"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty replyingComments}">
                                    <c:forEach items="${replyingComments}" var="comment">
                                        <tr>
                                            <td>
                                                <a href="profile?id=${comment.user.id}">
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

                                                    <c:if test="${comment.updateDateTime != null && comment.updateDateTime != comment.commentDate}">
                                                        (Edited On:
                                                        ${comment.updateDateTime.year}
                                                        ${comment.updateDateTime.month}
                                                        ${comment.updateDateTime.dayOfMonth})
                                                    </c:if>
                                                </p>
                                                <p>${comment.comment}</p>

                                                <c:if test="${not empty comment.replies}">
                                                    <a class="commentTable-dateText" href="commentThread.do?commentId=${comment.id}">
                                                        View Replies (${fn:length(comment.replies)})
                                                    </a>
                                                    <br>
                                                </c:if>
                                            </td>

                                            <td>
                                                <c:choose>
                                                    <c:when test="${comment.user.id == sessionScope.user.id}">
                                                        <form action="commentThread.do" method="GET">
                                                            <input type="hidden" name="commentId" value="${comment.id }">
            												<button type="submit" class="btn btn-warning btn-sm comment-icon-button">
            													<i class="glyphicon glyphicon-edit" data-toggle="tooltip" title="Edit Comment"></i>
            												</button>
                                                        </form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:if test="${sessionScope.user != null}">
                                                            <form action="commentThread.do" method="GET">
                												<input type="hidden" name="commentId" value="${comment.id }">
                												<button type="submit" class="btn btn-warning btn-sm comment-icon-button">
                													<i class="glyphicon glyphicon-share" data-toggle="tooltip" title="Reply To Comment"></i>
                												</button>
                											</form>
                                                        </c:if>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>

                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        No Replies...
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

<jsp:include page="../../scripts/tooltipScript.jsp" />


<jsp:include page="../../bootstrapFooter.jsp" />
