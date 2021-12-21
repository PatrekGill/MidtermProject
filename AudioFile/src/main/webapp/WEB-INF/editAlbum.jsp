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

                    <c:if test="${not empty album}">
                        <div class="">
    						<a href="album.do?albumId=${album.id}" class="btn btn-primary">
    							<i class="glyphicon glyphicon-circle-arrow-left"></i>
    							<span>Back To Album</span>
    						</a>
    					</div>
                    </c:if>
				</div>
			</div>


			<form action="editAlbum" method="POST">
				<c:if test="${editing}">
					<input type="hidden" name="albumId" value="${album.id}">
				</c:if>

				<table class="music-table table-hover">
					<tbody>

						<%-- ------------------------------------------------
							Album Title
						------------------------------------------------ --%>
						<tr>
							<td>
								<label for="title">Title:</label>
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

						<%-- ------------------------------------------------
							Album Description
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
                                            <textarea class="form-control" name="description" rows="7" placeholder="Description...">${album.description}</textarea>
                                        </c:when>
                                        <c:otherwise>
                                            <textarea class="form-control" name="description" rows="7" placeholder="Description..."></textarea>
                                        </c:otherwise>
                                    </c:choose>

								</div>
							</td>
						</tr>

						<%-- ------------------------------------------------
							Album Primary Artist
						------------------------------------------------ --%>
						<tr>
							<td>
								<label for="artist">Primary Artist:</label>

								<div class="input-group">
									<span class="input-group-addon">
								    	<i class="glyphicon glyphicon-user"></i>
								    </span>

									<select class="form-control" name="artistId">

										<c:forEach items="${artists }" var="artist">
											<c:choose>
												<c:when test="${editing && artist.id == album.artist.id}">
													<option value="${artist.id}" selected>${artist.name}</option>
												</c:when>
												<c:otherwise>
													<option value="${artist.id}">${artist.name}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>

									</select>
								</div>

							</td>
						</tr>

						<%-- ------------------------------------------------
							Album imageURL
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
                                            <input type="text" value="${album.imageURL}" class="form-control" name="imageURL" placeholder="Link..."/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="imageURL" placeholder="Link..."/>
                                        </c:otherwise>
                                    </c:choose>
								</div>
							</td>
						</tr>

						<%-- ------------------------------------------------
							Album Release Date
						------------------------------------------------ --%>
						<tr>
							<td>
								<label for="releaseDate">Release Date:</label>
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
                                    <c:choose>
                                        <c:when test="${editing}">
                                            <input type="date" value="${releaseDate}" class="form-control" name="releaseDate" placeholder="MM/DD/YYYY"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="date" class="form-control" name="releaseDate" placeholder="MM/DD/YYYY"/>
                                        </c:otherwise>
                                    </c:choose>
								</div>
							</td>
						</tr>

						<%-- ------------------------------------------------
							Album Songs List
						------------------------------------------------ --%>
						<tr class="editing-table-checkboxes-hover">
							<td>
                                <label for="songs">Songs:</label>
								<div class="editing-table-checkboxes">

                                    <c:forEach items="${songs }" var="song">
                                        <div>
                                            <label>
                                            <c:choose>
                                                <c:when test="${editing && fncust:contains( album.songs, song)}">
                                                    <input class="editing-table-checkbox-song" type="checkbox" name="songIds" value="${song.id}" checked>
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="editing-table-checkbox-song" type="checkbox" name="songIds" value="${song.id}">
                                                </c:otherwise>
                                            </c:choose>

												${song.name}
												<c:if test="${not empty song.artists}">
													<p class="editing-table-songBy-text">
														By
														<c:forEach items="${song.artists }" var="artist" varStatus="i">
															<c:choose>
		                                                        <c:when test="${i.index == 0}">
		                                                            ${artist.name}
		                                                        </c:when>
		                                                        <c:otherwise>
		                                    						, ${artist.name}
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
							Album Genres List
						------------------------------------------------ --%>
						<tr class="editing-table-checkboxes-hover">
							<td>
                                <label for="genres">Genres:</label>
								<div class="editing-table-checkboxes">

                                    <c:forEach items="${genres }" var="genre">
                                        <div>
                                            <label>
                                            <c:choose>
                                                <c:when test="${editing && fncust:contains( album.genres, genre)}">
                                                    <input type="checkbox" checked name="genreIds" value="${genre.id}">
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" name="genreIds" value="${genre.id}">
                                                </c:otherwise>
                                            </c:choose>
                                            ${genre.name}</label>
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



<jsp:include page="bootstrapFooter.jsp" />
