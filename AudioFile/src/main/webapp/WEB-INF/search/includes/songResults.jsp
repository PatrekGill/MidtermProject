<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container-fluid">

    <div class="table-responsive">
        <div class="table-wrapper table-body">
            <div class="table-title">
                <div class="row">
                    <h2>Songs</h2>
                </div>
            </div>
            <table class="music-table table-hover">

                <thead>
                    <tr>
                        <th class="songResults-table-albumImage"></th>
                        <th class="songResults-table-songName">Song</th>
                        <th class="songResults-table-artists">Artist(s)</th>
                        <th class="songResults-table-albums">Album(s)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="song" items="${Songs}">
                        <tr>
                            <%-- ------------------------------------------------
                                Album Image
                            ------------------------------------------------ --%>
                            <td>
                                <a href="album.do?albumId=${song.albums[0].id}">
                                    <img class="albumImage-thumb" src="${song.albums[0].imageURL}" alt="image of first album">
                                </a>
                            </td>


                            <%-- ------------------------------------------------
                                Song name
                            ------------------------------------------------ --%>
                            <td> <a href="song.do?songId=${song.id}">${song.name}</a> </td>


                            <%-- ------------------------------------------------
                                Song Artists
                            ------------------------------------------------ --%>
                            <td>
                                <c:forEach items="${song.artists}" var="artist" varStatus="j">
                                    <c:choose>
                                        <c:when test="${j.index == 0}">
                                            <a class="albumTable-artistText" href="artistProfile?id=${artist.id}">${artist.name}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="albumTable-artistText" href="artistProfile?id=${artist.id}">,${artist.name}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </td>


                            <%-- ------------------------------------------------
                                Song Albums
                            ------------------------------------------------ --%>
                            <td>
                                <c:forEach items="${song.albums}" var="album" varStatus="i">
                                    <c:choose>
                                        <c:when test="${i.index == 0}">
                                            <a class="albumTable-artistText" href="album.do?albumId=${album.id}">${album.title}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="albumTable-artistText" href="album.do?albumId=${album.id}">,${album.title}</a>
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
