<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="bootstrapHead.jsp" />
<div class="col-sm-8 text-left">
 <%-- ------------------------------------------------
                Albums Table
------------------------------------------------ --%>
<h1>Trending Albums</h1>
<c:forEach var="album" items="${trendingAlbums }">
	<form action="album.do" method="GET"></form>
	<li><a href="album.do?albumId=${album.id }">${album.title }</a></li>
</c:forEach>
<br>
 <%-- ------------------------------------------------
                Songs Table
------------------------------------------------ --%>
<h1>Trending Songs</h1>
<c:forEach var="song" items="${trendingSongs }">
	<form action="searchBySongName.do" method="GET"></form>
	<li><a href="searchBySongName.do?songName=${song.name }">${song.name }</a></li>
</c:forEach>
<br>
 <%-- ------------------------------------------------
                Artists Table
------------------------------------------------ --%>
<h1>Trending Artists</h1>
<c:forEach var="artist" items="${trendingArtists }">
	<form action="artistProfile" method="GET"></form>
	<li><a href="artistProfile?id=${artist.id }">${artist.name }</a>
</c:forEach>
</div>
<jsp:include page="bootstrapFooter.jsp"/>
