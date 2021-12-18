<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="row">
    <div class="container">
        <c:if test="${not empty album.imageURL}">
            <div class="col-xs-6 col-sm-7 col-md-6 col-lg-5 albumImage-div">
                    <a href="album.do?albumId=${album.id}">
                        <img class="albumImage-md" src="${album.imageURL}" alt="image of album">
                    </a>
            </div>
        </c:if>
        <div class="col-xs-10 col-sm-6 col-md-4 col-lg-5 albumText">
            <h1 class="albumText-title">
                <a href="album.do?albumId=${album.id}">${album.title}</a>
            </h1>

            <c:if test="${not empty album.artist}">
                <a class="albumText-artist" href="artistProfile?id=${album.artist.id}">${album.artist.name}</a>
                <br>
            </c:if>

            <a class="albumText-artist-sm" href="albumRatings.do?albumId=${album.id}">Average Rating: ${averageRating} / 10</a>
            <br>
            <p>${album.description}</p>
        </div>
    </div>
</div>
