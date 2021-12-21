<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container-fluid">

    <div class="table-responsive">
        <div class="table-wrapper table-body">
            <div class="table-title">
                <div class="row">
                    <h2>Albums</h2>
                </div>
            </div>
            <table class="music-table table-hover">

                <thead>
                    <tr>
                        <th class="albumResults-table-albumImage"></th>
                        <th class="albumResults-table-songName">Album Name</th>
                        <th class="albumResults-table-artist">Primary Artist</th>
                        <th class="albumResults-table-genres">Genre(s)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="album" items="${Albums}">
                        <tr>
                            <%-- ------------------------------------------------
                                Album Image
                            ------------------------------------------------ --%>
                            <td>
                                <a href="album.do?albumId=${album.id}">
                                    <img class="albumImage-sm" src="${album.imageURL}" alt="image of album">
                                </a>
                            </td>


                            <%-- ------------------------------------------------
                                Album name
                            ------------------------------------------------ --%>
                            <td> <a href="album.do?albumId=${album.id}">${album.title}</a> </td>


                            <%-- ------------------------------------------------
                                Album Primary Artist
                            ------------------------------------------------ --%>
                            <td>
                                <a class="albumTable-artistText" href="artistProfile?id=${album.artist.id}">${album.artist.name}</a>
                            </td>


                            <%-- ------------------------------------------------
                                Album Generes
                            ------------------------------------------------ --%>
                            <td>
                                <c:forEach items="${album.genres}" var="genre" varStatus="i">
                                    <c:choose>
                                        <c:when test="${i.index == 0}">
                                            <a class="albumTable-artistText" href="search?keyword=${genre.name}&searchType=Genre">${genre.name}</a>
                                        </c:when>
                                        <c:otherwise>
                                            , <a class="albumTable-artistText" href="search?keyword=${genre.name}&searchType=Genre">${genre.name}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
    </div>
</div>

<br>
