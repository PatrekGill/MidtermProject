<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<link rel="shortcut icon" type="image/png"
	href="logosAndGraphics/monkeyVector.png" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/main.css" />
</head>
<body>
	<div class="mainContent">
		<div class="content-wrap">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#myNavbar">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="home"><img
							src="logosAndGraphics/monkeyVector.png" class="img-fluid"
							alt="..." width="24" height="30"></a>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">Home</a></li>
							<li><a href="myProfile">Profile</a></li>
							<li><a href="trending">Trending</a></li>

							<li><div class="dropdown-padder"></div>
								<div class="dropdown">
									<div class="dropdown-padder">

										<button class="btn btn-link"
											id="dropdownMenuButton" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false">Create</button>
										<div class="dropdown-menu"
											aria-labelledby="dropdownMenuButton">
											<ul>
												<li><a class="dropdown-item" href="addAlbum">Add an Album</a></li>
												<li><a class="dropdown-item" href="addArtist">Add an Artist</a></li>
												<li><a class="dropdown-item" href="addSong">Add a Song</a></li>
											</ul>
										</div>
									</div>
								</div></li>

						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><form class="form-inline my-2 my-lg-0" action="search"
									method=GET>
									<div class="search-design">
										<input class="form-control mr-sm-2" type="search"
											placeholder="search" aria-label="search" name="keyword">
										<button class="btn btn-link"
											type="submit">Search</button>
									</div></li>
							<li>
								<div class="select-padder">
									<select class="form-select form-select-sm"
										aria-label=".form-select-sm example" name="searchType">
										<option selected value="All">Search by</option>
								</div>
								<option value="All">All</option>
								<option value="Album">Album</option>
								<option value="Artist">Artist</option>
								<option value="Genre">Genre</option>
								<option value="Song">Song</option> </select>
								</form>
							</li>
							<c:choose>
								<c:when test="${empty user }">
									<li><a href="login"><span
											class="glyphicon glyphicon-log-in"></span> Login</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="logout"><span
											class="glyphicon glyphicon-log-out"></span> Logout</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</nav>

			<div class="container-fluid text-center">
				<div class="row content">
					<div class="col-sm-2 sidenav">

						<div id="headingTopAlbums">
							<p>
								<button class="btn btn-link" data-toggle="collapse"
									data-target="#collapseTopAlbums" aria-expanded="false"
									aria-controls="collapseTopAlbums">Top Albums</button>
							</p>
						</div>
						<div id="collapseTopAlbums" class="collapse"
							aria-labelledby="headingTopAlbums" data-parent="#accordion">
							<c:forEach var="album" items="${topAlbumsSideBar }">
								<li><a href="album.do?albumId=${album.id }">${album.title }</a></li>
							</c:forEach>


						</div>
						<p>
							<button class="btn btn-link" data-toggle="collapse"
								data-target="#collapseTopArtists" aria-expanded="false"
								aria-controls="collapseTopArtists">Top Artists</button>
						</p>
						<div id="collapseTopArtists" class="collapse"
							aria-labelledby="headingTopArtists" data-parent="#accordion">
							<c:forEach var="artist" items="${topArtistsSideBar }">
								<li><a href="artistProfile?id=${artist.id }">${artist.name }</a></li>
							</c:forEach>
						</div>
						<p>
							<button class="btn btn-link" data-toggle="collapse"
								data-target="#collapseTopSongs" aria-expanded="false"
								aria-controls="collapseTopSongs">Top Songs</button>
						</p>
						<div id="collapseTopSongs" class="collapse"
							aria-labelledby="headingTopSongs" data-parent="#accordion">
							<c:forEach var="song" items="${topSongsSideBar }">
								<li><a href="searchBySongName.do?songName=${song.name }">${song.name }</a></li>
							</c:forEach>
						</div>

					</div>
