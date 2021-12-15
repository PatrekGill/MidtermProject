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
                <div class="col-xs-10 col-sm-3 col-md-4 albumText">
                    <h1 class="albumText-title">${album.title}</h1>
                    <c:if test="${not empty album.artist}">
                        <%-- Must be changed to artist page --%>
                        <a class="albumText-artist" href="album.do?albumId=${album.id}">${album.artist.name}</a>
                    </c:if>
                </div>
            </div>

        </div>

        <div class="table-responsive">
            <div class="table-wrapper table-body">
                <div class="table-title">
					<div class="row">
						<div class="col-xs-1 col">
							<h2>Songs</h2>
						</div>
					</div>
				</div>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Song</th>
                            <th>Artist</th>
                            <th>Length</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Anna</td>
                            <td>Pitt</td>
                            <td>35</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    </c:otherwise>
</c:choose>


<jsp:include page="bootstrapFooter.jsp" />
