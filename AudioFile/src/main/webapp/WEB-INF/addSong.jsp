<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="bootstrapHead.jsp" />

<jsp:include page="musicBetterHeader.jsp" />


<div class="container-fluid">
	<div class="table-responsive">
		<div class="table-wrapper table-body editing-table">

            <div class="table-title">
				<div class="row">
					<div class="">
						<h2>
							Add <b>Song</b>
						</h2>
					</div>
				</div>
			</div>

			<form action="addSong" id="filmDetails" method="POST">
				<table class="music-table table-hover">
					<tbody>
						<tr>
							<td>
								<label for="name">Name:</label>
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-pencil"></i>
									</span>
									<input type="text" class="form-control" name="name" placeholder="Name" value=""/>
								</div>
							</td>
						</tr>

						<tr>
							<td>
								<label for="lyrics">Lyrics:</label>
								<div class="input-group editing-table-textarea">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-pencil"></i>
									</span>
                                    <textarea class="form-control" name="lyrics" placeholder="Lyrics..."></textarea>
								</div>
							</td>
						</tr>

						<tr>
							<td>
								<label for="duration">Duration (seconds)</label>
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-time"></i>
									</span>
									<input type="number" name="duration" min="0" max="9999" step="1" value="0" class="form-control" placeholder="Duration in seconds..."/>
								</div>
							</td>
						</tr>

						<tr>
							<td>

								<select class="editing-table-selectBox" multiple>
                                    <option value="1" data-mdb-icon="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-1.jpg">
                                        One
                                    </option>

								</select>

							</td>
						</tr>

						<tr>
							<td>

								<div>
									<button type="submit" class="btn btn-success">
										<i class="glyphicon glyphicon-plus-sign" style="font-size:22px; vertical-align: middle;"></i>
										<span style="font-size:15px; vertical-align: middle;">Add Song</span>
									</button>
								</div>

							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>







<%-- <div class="col-sm-8 text-left">
<table class="createAccount" id="create-account">
	<thead>
		<tr>
			<th>Field</th>
			<th>Value</th>
		</tr>
	</thead>
	<tbody>
		<form action="addSong" method="post">
			<tr>
				<th>Name</th>
				<td><input type="text" id="name" name="name" required style ="color:black"></td>
			</tr>
			<tr>
				<th>Lyrics</th>
				<td><input type="text" id="lyrics" name="lyrics" style ="color:black"></td>
			</tr>
			<tr>
				<th>Duration Seconds</th>
				<td><input type="number" id="durationInSeconds" name="durationInSeconds" style ="color:black"></td>
			</tr>
			<tr>
				<td><input type="submit" type="Create Song" style ="color:black" /></td>
			</tr>
		</form>
	</tbody>
</table>
</div> --%>
<jsp:include page="bootstrapFooter.jsp" />
