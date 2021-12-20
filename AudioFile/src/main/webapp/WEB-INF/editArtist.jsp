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
									Edit <b>Artist</b>
								</h2>
							</c:when>
							<c:otherwise>
								<h2>
									Add <b>Artist</b>
								</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>


			<form action="editArtist" method="POST">
				<c:if test="${editing}">
					<input type="hidden" name="artistId" value="${artist.id}">
				</c:if>

				<table class="music-table table-hover">
					<tbody>

						<%-- ------------------------------------------------
							Artist Name
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
                                            <input type="text" value="${artist.name}" class="form-control" name="name" placeholder="Name"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="name" placeholder="Name"/>
                                        </c:otherwise>
                                    </c:choose>

								</div>
							</td>
						</tr>

						<%-- ------------------------------------------------
							Artist Description
						------------------------------------------------ --%>
						<tr>
							<td>
								<label for="description">Description:</label>
								<div class="input-group editing-table-textarea">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-pencil"></i>
									</span>
                                    <c:choose>
                                        <c:when test="${editing}">
                                            <textarea class="form-control" name="description" placeholder="Description...">${artist.description}</textarea>
                                        </c:when>
                                        <c:otherwise>
                                            <textarea class="form-control" name="description" placeholder="Description..."></textarea>
                                        </c:otherwise>
                                    </c:choose>

								</div>
							</td>
						</tr>

						<%-- ------------------------------------------------
							Artist imageURL
						------------------------------------------------ --%>
						<tr>
							<td>
								<label for="image">Link To Image:</label>
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-pencil"></i>
									</span>
                                    <c:choose>
                                        <c:when test="${editing}">
                                            <input type="text" value="${artist.imageUrl}" class="form-control" name="imageURL" placeholder="Link..."/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="imageURL" placeholder="Link..."/>
                                        </c:otherwise>
                                    </c:choose>
								</div>
							</td>
						</tr>

						<%-- ------------------------------------------------
							Artist Songs List
						------------------------------------------------ --%>
						<tr class="editing-table-checkboxes-hover">
							<td>
                                <label for="songs">Songs:</label>
								<div class="editing-table-checkboxes">

                                    <c:forEach items="${songs }" var="song">
                                        <div>
                                            <c:choose>
                                                <c:when test="${editng && fncust:contains( artist.songs, song)}">
                                                    <input class="editing-table-checkbox-song" type="checkbox" checked name="songIds" value="${song.id}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="editing-table-checkbox-song" type="checkbox" name="songIds" value="${song.id}">
                                                </c:otherwise>
                                            </c:choose>
                                            <label for="${song}">
												${song.name}
												<c:if test="${not empty song.artists}">
													<p class="editing-table-songBy-text">
														By
														<c:forEach items="${song.artists }" var="_artist" varStatus="i">
															<c:choose>
		                                                        <c:when test="${i.index == 0}">
		                                                            ${_artist.name}
		                                                        </c:when>
		                                                        <c:otherwise>
		                                    						, ${_artist.name}
		                                    					</c:otherwise>
		                                                    </c:choose>
														</c:forEach>
													</p>
												</c:if>
											</label>
                                        </div>
                                    </c:forEach>

								</div>

							</td>
						</tr>

                        <%-- ------------------------------------------------
                            Artist Albums list
                        ------------------------------------------------ --%>
						<tr class="editing-table-checkboxes-hover">
							<td>
                                <label for="albums">Albums:</label>
								<div class="editing-table-checkboxes">

                                    <c:forEach items="${albums }" var="album">
                                        <div>
                                            <c:choose>
                                                <c:when test="${editing && fncust:contains( song.albums, album)}">
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
                                            <button type="submit" class="btn btn-warning table-btn">Update Artist</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="submit" class="btn btn-warning table-btn">Add Artist</button>
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
