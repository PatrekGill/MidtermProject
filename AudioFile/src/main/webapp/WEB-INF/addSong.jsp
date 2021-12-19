<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="bootstrapHead.jsp" />

<jsp:include page="musicBetterHeader.jsp" />


<div class="container-fluid">
	<div class="table-responsive">
		<div class="table-wrapper table-body editing-table">

            <div class="table-title">
				<div class="row">
					<div class="">
						<h2>
							Add <b>Song</b>
						</h2>
					</div>
				</div>
			</div>

			<form action="addSong" id="filmDetails" method="POST">
				<table class="music-table table-hover">
					<tbody>
						<tr>
							<td>
								<label for="name">Name:</label>
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-pencil"></i>
									</span>
									<input type="text" class="form-control" name="name" placeholder="Name" value=""/>
								</div>
							</td>
						</tr>

						<tr>
							<td>
								<label for="lyrics">Lyrics:</label>
								<div class="input-group editing-table-textarea">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-pencil"></i>
									</span>
                                    <textarea class="form-control" name="lyrics" placeholder="Lyrics..."></textarea>
								</div>
							</td>
						</tr>

						<tr>
							<td>
								<label for="duration">Duration (seconds)</label>
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-time"></i>
									</span>
									<input type="number" name="durationInSeconds" min="0" max="9999" step="1" value="0" class="form-control" placeholder="Duration in seconds..."/>
								</div>
							</td>
						</tr>

						<tr>
							<td>
                                <label for="artists">Artists</label>
								<div class="input-group container-fluid">

                                    <c:forEach items="${artists }" var="artist">
                                        <div>
                                            <c:choose>
                                                <c:when test="${not empty song}">
                                                    <input type="checkbox" checked id="${artist}" name="artistIds" value="${artist.id}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" id="${artist}" name="artistIds" value="${artist.id}">
                                                </c:otherwise>
                                            </c:choose>
                                            <label for="${artist}">${artist.name}</label>
                                        </div>


                                    </c:forEach>

								</div>

							</td>
						</tr>

						<tr>
							<td>

								<div>
									<button type="submit" class="btn btn-warning table-btn">Add Song</button>
								</div>

							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>


<jsp:include page="bootstrapFooter.jsp" />
