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
					<div class="">
						<h2>
							Add <b>Song</b>
						</h2>
					</div>
				</div>
			</div>

			<form action="editSong" id="filmDetails" method="POST">
				<table class="music-table table-hover">
					<tbody>
						<tr>
							<td>
								<label for="name">Name:</label>
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-pencil"></i>
									</span>
                                    <c:choose>
                                        <c:when test="${not empty song}">
                                            <input type="text" value="${song.name}" class="form-control" name="name" placeholder="Name" value=""/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="name" placeholder="Name" value=""/>
                                        </c:otherwise>
                                    </c:choose>

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
                                    <c:choose>
                                        <c:when test="${not empty song}">
                                            <textarea class="form-control" name="lyrics" placeholder="Lyrics...">${song.lyrics}</textarea>
                                        </c:when>
                                        <c:otherwise>
                                            <textarea class="form-control" name="lyrics" placeholder="Lyrics..."></textarea>
                                        </c:otherwise>
                                    </c:choose>

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
                                    <c:choose>
                                        <c:when test="${not empty song}">
                                            <input type="number" value="${song.durationInSeconds}" name="durationInSeconds" min="0" max="9999" step="1" value="0" class="form-control" placeholder="Duration in seconds..."/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="number" value="${song.durationInSeconds}" name="durationInSeconds" min="0" max="9999" step="1" value="0" class="form-control" placeholder="Duration in seconds..."/>
                                        </c:otherwise>
                                    </c:choose>
								</div>
							</td>
						</tr>

						<tr>
							<td>
                                <label for="artists">Artists:</label>
								<div class="editing-table-checkboxes">

                                    <c:forEach items="${artists }" var="artist">
                                        <div>
                                            <c:choose>
                                                <c:when test="${not empty song && fncust:contains( song.artists, artist)}">
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
                                <label for="albums">Albums:</label>
								<div class="editing-table-checkboxes">

                                    <c:forEach items="${albums }" var="album">
                                        <div>
                                            <c:choose>
                                                <c:when test="${not empty song && fncust:contains( song.albums, album)}">
                                                    <input type="checkbox" checked id="${album}" name="albumIds" value="${album.id}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" id="${album}" name="albumIds" value="${album.id}">
                                                </c:otherwise>
                                            </c:choose>
                                            <label for="${album}">${album.name}</label>
                                        </div>
                                    </c:forEach>

								</div>

							</td>
						</tr>

						<tr>
							<td>

								<div>
                                    
                                    <c:choose>
                                        <c:when test="${not empty song}">
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


<script type="text/javascript">
    window.onLoad = onPageLoad();

    function onPageLoad() {
        var elements = document.getElementsByClassName("checkboxCheck");
        for (var i = 0; i < elements.length; i++) {

        }
    }
</script>


<jsp:include page="bootstrapFooter.jsp" />
