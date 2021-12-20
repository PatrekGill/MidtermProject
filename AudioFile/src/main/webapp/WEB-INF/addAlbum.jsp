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
									Edit <b>Album</b>
								</h2>
							</c:when>
							<c:otherwise>
								<h2>
									Add <b>Album</b>
								</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>

			<%-- ------------------------------------------------
				Song duration field
			------------------------------------------------ --%>
			<form action="editAlbum" method="POST">
				<table class="music-table table-hover">
					<tbody>
						<tr>
							<td>
								<label for="name">Title:</label>
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-pencil"></i>
									</span>
                                    <c:choose>
                                        <c:when test="${editing}">
                                            <input type="text" value="${album.title}" class="form-control" name="title" placeholder="Title" value=""/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="title" placeholder="Title" value=""/>
                                        </c:otherwise>
                                    </c:choose>

								</div>
							</td>
						</tr>

						<tr>
							<td>
								<label for="lyrics">Description:</label>
								<div class="input-group editing-table-textarea">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-pencil"></i>
									</span>
                                    <c:choose>
                                        <c:when test="${editing}">
                                            <textarea class="form-control" name="description" placeholder="Description...">${album.description}</textarea>
                                        </c:when>
                                        <c:otherwise>
                                            <textarea class="form-control" name="description" placeholder="Description..."></textarea>
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
                                                <c:when test="${not empty album && fncust:contains( song.artists, artist)}">
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
                                <label for="songs">Songs:</label>
								<div class="editing-table-checkboxes">

                                    <c:forEach items="${songs }" var="song">
                                        <div>
                                            <c:choose>
                                                <c:when test="${not empty song && fncust:contains( album.songs, song)}">
                                                    <input type="checkbox" checked id="${song}" name="songIds" value="${song.id}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" id="${song}" name="songIds" value="${song.id}">
                                                </c:otherwise>
                                            </c:choose>
                                            <label for="${song}">${song.name}</label>
                                        </div>
                                    </c:forEach>

								</div>

							</td>
						</tr>


						<tr>
							<td>
                                <label for="songs">Songs:</label>
								<div class="editing-table-checkboxes">

                                    <c:forEach items="${songs }" var="song">
                                        <div>
                                            <c:choose>
                                                <c:when test="${not empty song && fncust:contains( album.songs, song)}">
                                                    <input type="checkbox" checked id="${song}" name="songIds" value="${song.id}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" id="${song}" name="songIds" value="${song.id}">
                                                </c:otherwise>
                                            </c:choose>
                                            <label for="${song}">${song.name}</label>
                                        </div>
                                    </c:forEach>

								</div>

							</td>
						</tr>

						<tr>
							<td>

								<div>

                                    <c:choose>
                                        <c:when test="${editing}">
                                            <button type="submit" class="btn btn-warning table-btn">Update Album</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="submit" class="btn btn-warning table-btn">Add Album</button>
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





<div class="col-sm-8 text-left">
<table class="createAccount" id="create-account">
	<thead>
		<tr>
			<th>Field</th>
			<th>Value</th>
		</tr>
	</thead>
	<tbody>
		<form action="addAlbum" method="post">
			<tr>
				<th>Title</th>
				<td><input type="text" id="title" name="title" required
				style = "color:black"
				></td>
			</tr>
			<tr>
				<th>Description</th>
				<td><input type="text" id="description" name="description" style ="color:black"></td>
			</tr>
			<tr>
				<th>Release Date</th>
				<td><input type="date" id="releaseDate" name="releaseDate"
					required style ="color:black"></td>
			</tr>
			<tr>
				<th>Image Url</th>
				<td><input type="text" id="imageURL" name="imageURL" style ="color:black"></td>
			</tr>
			<div class ="spacer" >
			<tr>
			<td>
				<label for="names">Artist Name</label></td>
				<td>
				<select name="names" id="names">
					<option value="Jimmy Buffett">Jimmy Buffett</option>
					<option value="Mac DeMarco">Mac DeMarco</option>
					<option value="Adele">Adele</option>
					<option value="Miles Davis">Miles Davis</option>
				</select></td>

			</tr>
			</div>
		<tr>
			<td><input type="submit" type="Create Album" style ="color:black"/></td>
		</tr>
		</form>
	</tbody>
</table>
</div>








<jsp:include page="bootstrapFooter.jsp" />
