<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../css/Home.css" />
<meta charset="UTF-8">
<title>Logged In</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-2 p-2 mb-5 rounded bg-white border border-primary text-center">
				<a  href="../Home.html"> &#x21A9; Log Out</a>
			</div>
			<div class="col-8">
				<h1 class="m-3">Hello ${username}!</h1>
			</div>
			<div class="col-2"></div>
		</div>
	</div>
	<h3 class="m-2">Welcome to your attendence registry</h3>
				<h5 class="m-3">
					<i>Here is the summary of your registrations:</i>
				</h5>
	<div class="container">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<table class="table table-striped">
					<thead class="thead-light">
						<tr>
							<th scope="col">Date</th>
							<th scope="col">Morning E</th>
							<th scope="col">Afternoon U</th>
							<th scope="col">Description</th>
							<th scope="col">Signature</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reg" items="${registrations}">
							<tr>
								<td>${reg.regDate}</td>
								<td>${reg.mEntryTime}</td>
								<td>${reg.aExitTime}</td>
								<td>${reg.description}</td>
								<td>${reg.signCand}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-2"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<a href="../DBInteract/DailyRegistration.html"
				class="btn btn-primary btn-block" role="button">Register Today's
				Attendence</a>

		</div>
		<div class="col-3"></div>
	</div>


</body>
</html>