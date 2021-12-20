<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="bootstrapHead.jsp" />

<jsp:include page="musicBetterHeader.jsp" />

 <%-- ------------------------------------------------
                Albums Table
------------------------------------------------ --%>
<div class="container-fluid">

    <div class="table-responsive">
        <div class="table-wrapper table-body">
            <div class="table-title">
                <div class="row">
                    <h2> Trending Albums</h2>
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
                    <c:forEach var="album" items="${trendingAlbums}" begin="0" end="19">
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
                                            <a class="albumTable-artistText" href="search?keyword=${genre.name}&searchType=Genre">${genre.name}</a>
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
 <%-- ------------------------------------------------
                Songs Table
------------------------------------------------ --%>

<div class="container-fluid">

    <div class="table-responsive">
        <div class="table-wrapper table-body">
            <div class="table-title">
                <div class="row">
                    <h2>Trending Songs</h2>
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
                    <c:forEach var="song" items="${trendingSongs}" begin="0" end="19">
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
                            <td> <a href="getSongId.do?songId=${song.id}">${song.name}</a> </td>


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
                                            <a class="albumTable-artistText" href="artistProfile?id=${artist.id}">, ${artist.name}</a>
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
 <%-- ------------------------------------------------
                Artists Table
------------------------------------------------ --%>
<div class="container-fluid">

    <div class="table-responsive">
        <div class="table-wrapper table-body">
            <div class="table-title">
                <div class="row">
                    <h2>Trending Artists</h2>
                </div>
            </div>
            <table class="music-table table-hover">

                <thead>
                    <tr>
                        <th class="artistResults-table-albumImage"></th>
                        <th class="artistResults-table-artistName">Name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="artist" items="${trendingArtists}" begin="0" end="19">
                        <tr>
                            <%-- ------------------------------------------------
                                Aritst Image
                            ------------------------------------------------ --%>
                            <td>
                                <a href="artistProfile?id=${artist.id}">
                                    <img class="artistImage-md" src="${artist.imageUrl}" alt="image of artist">
                                </a>
                            </td>


                            <%-- ------------------------------------------------
                                Album name
                            ------------------------------------------------ --%>
                            <td> <a href="artistProfile?id=${artist.id}">${artist.name}</a> </td>

                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
    </div>
</div>
<jsp:include page="bootstrapFooter.jsp"/>
