<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>

<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

.search-design {
	padding-top: 10px;
	height: 100%;
	margin: auto;
	color: black;
}

.text-manipulator {
	margin-top: 50px;
	font-size: 1000%;
	font: Helvetica;
	line-height: 60%;
}
/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 100%
}

/* Set gray background color and 100% height */
.sidenav {
	padding-top: 20px;
	background-color: #242424;
	height: 100%;
}

a {
	color: white;
	font: Helvetica;
}

a:hover {
	color: #B8860B;
}

body {
	background-image: url(logosAndGraphics/monkeyHalf.png);
	background-size: 300px;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-color: #B8860B;
	background-position: right bottom;
	color: white;
}
/* Set black background color, white text and some padding */
footer {
	background-color: #242424;
	color: white;
	padding: 15px;
}

.page-footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	justify-content: center;
}

.footer-contents {
	margin: auto;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>
</head>
<body>
	<div class="mainContent">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="home.do"><img
						src="logosAndGraphics/monkeyVector.png" class="img-fluid"
						alt="..." width="24" height="30"></a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#">Profile</a></li>
						<li><a href="#">Trending</a></li>
						<li><a href="#">Recent Messages</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><form class="form-inline my-2 my-lg-0">
								<div class="search-design">
									<input class="form-control mr-sm-2" type="search"
										placeholder="Search" aria-label="Search">
									<button class="btn btn-outline-success my-2 my-sm-0"
										type="submit">Search</button>
								</div>
							</form></li>
						<c:choose>
							<c:when test="${ empty user}">
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
					<p>
						<a href="#">Top Albums</a>
					</p>
					<p>
						<a href="#">Top Artists</a>
					</p>
					<p>
						<a href="#">Top Songs</a>
					</p>
				</div>
				<div class="col-sm-8 text-left text-manipulator">
					<p>Music</p>
					<p>Just</p>
					<p>Got</p>
					<p>Better</p>
					</div>
					</div>

