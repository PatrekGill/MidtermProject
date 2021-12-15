<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>Page Title</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="css/main.css" />
	<link rel="stylesheet" href="css/sidebars.css" />
	<%-- <link rel="stylesheet" href="css/headers.css" /> --%>
</head>

<body>

	<header class="p-0.5 bg-dark text-white">
		<div class="p-3 bg-dark text-white">
			<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

				<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li><a href="#" class="nav-link px-2 text-secondary">Home</a></li>
					<li><a href="#" class="nav-link px-2 text-white">Features</a></li>
					<li><a href="#" class="nav-link px-2 text-white">Pricing</a></li>
					<li><a href="#" class="nav-link px-2 text-white">FAQs</a></li>
					<li><a href="#" class="nav-link px-2 text-white">About</a></li>
				</ul>

				<form class="col-12 col-lg-5 mb-3 mb-lg-0 me-lg-3" action="search.do" method="GET">
					<div class="input-group">
						<div class="col-sm-3">
							<select class="form-select" id="searchType" name="searchType">
								<option value="All" selected>All</option>
								<option value="Albums">Albums</option>
								<option value="Artists">Artists</option>
								<option value="Lyrics">Lyrics</option>
								<option value="Songs">Songs</option>
								<option value="Users">Users</option>
							</select>
						</div>

						<input type="search" class="form-control form-control-dark" placeholder="Search..." aria-label="Search">
					</div>
				</form>

				<div class="text-end">
					<button type="button" class="btn btn-outline-light me-2">Login</button>
					<button type="button" class="btn btn-warning">Sign-up</button>
				</div>


			</div>
		</div>
	</header>

	<sidebar>
		<div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="width: 280px;">

			<a href="#" class="d-flex align-items-center text-white text-decoration-none" id="dropdownUser1" aria-expanded="false">
				<img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
				<strong>Profile</strong>
			</a>

            <hr>
            <ul class="nav nav-pills flex-column mb-auto">
                <li class="nav-item">
                    <a href="#" class="nav-link active" aria-current="page">
                        <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#home" />
                        </svg>
                        Home
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link text-white">
                        <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#speedometer2" />
                        </svg>
                        Dashboard
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link text-white">
                        <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#table" />
                        </svg>
                        Orders
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link text-white">
                        <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#grid" />
                        </svg>
                        Products
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link text-white">
                        <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#people-circle" />
                        </svg>
                        Customers
                    </a>
                </li>
            </ul>
        </div>

        <div class="b-example-divider"></div>
	</sidebar>



	<%-- <footer class="container-fluid text-center page-footer">
		<div class="footer-contents">
			<a href="#">Subscribe</a>
		</div>
	</footer> --%>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
