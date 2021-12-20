<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="fncust" %>


<jsp:include page="bootstrapHead.jsp" />

<jsp:include page="musicBetterHeader.jsp" />


<div class="container-fluid">
	<div class="table-responsive">
		<div class="table-wrapper table-body editing-table">

            <div class="table-title">
				<div class="row">
                    <div>
						<c:choose>
							<c:when test="${editing}">
								<h2>
									Edit <b>Song</b>
								</h2>
							</c:when>
							<c:otherwise>
								<h2>
									Add <b>Song</b>
								</h2>
							</c:otherwise>
						</c:choose>
					</div>

                    <c:if test="${not empty Song}">
                        <div class="">
                            <%-- FIX ME --%>
    						<a href="getSongId.do?songId=${Song.id}" class="btn btn-primary">
    							<i class="glyphicon glyphicon-circle-arrow-left"></i>
    							<span>Back To Song</span>
    						</a>
    					</div>
                    </c:if>
				</div>
			</div>

			<form action="editSong" method="POST">
                <c:if test="${editing}">
                    <input type="hidden" name="songId" value="${song.id}">
                </c:if>

				<table class="music-table table-hover">
					<tbody>

                        <%-- ------------------------------------------------
                            Song name field
                        ------------------------------------------------ --%>
						<tr>
							<td>
								<label for="name">Name:</label>
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-pencil"></i>
									</span>
                                    <c:choose>
                                        <c:when test="${editing}">
                                            <input type="text" value="${song.name}" class="form-control" name="name" placeholder="Name" value=""/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="name" placeholder="Name" value=""/>
                                        </c:otherwise>
                                    </c:choose>

								</div>
							</td>
						</tr>

                        <%-- ------------------------------------------------
                            Song lyrics field
                        ------------------------------------------------ --%>
						<tr>
							<td>
								<label for="lyrics">Lyrics:</label>
								<div class="input-group editing-table-textarea">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-pencil"></i>
									</span>
                                    <c:choose>
                                        <c:when test="${editing}">
                                            <textarea class="form-control" name="lyrics" placeholder="Lyrics...">${song.lyrics}</textarea>
                                        </c:when>
                                        <c:otherwise>
                                            <textarea class="form-control" name="lyrics" placeholder="Lyrics..."></textarea>
                                        </c:otherwise>
                                    </c:choose>

								</div>
							</td>
						</tr>

                        <%-- ------------------------------------------------
                            Song duration field
                        ------------------------------------------------ --%>
						<tr>
							<td>
								<label for="duration">Duration (seconds)</label>
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-time"></i>
									</span>
                                    <c:choose>
                                        <c:when test="${editing}">
                                            <input type="number" value="${song.durationInSeconds}" name="durationInSeconds" min="0" max="9999" step="1" value="0" class="form-control" placeholder="Duration in seconds..."/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="number" value="${song.durationInSeconds}" name="durationInSeconds" min="0" max="9999" step="1" value="0" class="form-control" placeholder="Duration in seconds..."/>
                                        </c:otherwise>
                                    </c:choose>
								</div>
							</td>
						</tr>

                        <%-- ------------------------------------------------
                            Song Artists list
                        ------------------------------------------------ --%>
						<tr class="editing-table-checkboxes-hover">
							<td>
                                <label for="artists">Artists:</label>
								<div class="editing-table-checkboxes">

                                    <c:forEach items="${artists }" var="artist">
                                        <div>
                                            <c:choose>
                                                <c:when test="${editing && fncust:contains( song.artists, artist)}">
                                                    <input type="checkbox" checked name="artistIds" value="${artist.id}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" name="artistIds" value="${artist.id}">
                                                </c:otherwise>
                                            </c:choose>
                                            <label for="${artist}">${artist.name}</label>
                                        </div>
                                    </c:forEach>

								</div>

							</td>
						</tr>

                        <%-- ------------------------------------------------
                            Song Albums list
                        ------------------------------------------------ --%>
						<tr class="editing-table-checkboxes-hover">
							<td>
                                <label for="albums">Albums:</label>
								<div class="editing-table-checkboxes">

                                    <c:forEach items="${albums }" var="album">
                                        <div>

                                            <c:choose>
                                                <c:when test="${editing && fncust:contains( artist.albums, album)}">
                                                    <input type="checkbox" checked name="albumIds" value="${album.id}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" name="albumIds" value="${album.id}">
                                                </c:otherwise>
                                            </c:choose>
                                            <label for="${album}">${album.title}</label>

                                        </div>
                                    </c:forEach>

								</div>

							</td>
						</tr>

                        <%-- ------------------------------------------------
                            Update/Add Button
                        ------------------------------------------------ --%>
						<tr>
							<td>

								<div>

                                    <c:choose>
                                        <c:when test="${editing}">
                                            <button type="submit" class="btn btn-warning table-btn">Update Song</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="submit" class="btn btn-warning table-btn">Add Song</button>
                                        </c:otherwise>
                                    </c:choose>

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
