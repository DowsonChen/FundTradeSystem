<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>

<html lang="en">
<head>
<title>Customer</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-2.2.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Custom CSS -->
<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<nav class="navbar navbar-inverse navbar-fixed-top">
							<div class="container-fluid">
								<div class="navbar-header">
									<a href="index.jsp" class="pull-left"><img
										src="images/logo.png" style="max-width: 100px"></a>
								</div>
								<ul class="nav navbar-nav">
									<li class="dropdown"><a class="dropdown-toggle"
										data-toggle="dropdown" href="#">ABOUT US <span
											class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="">About Carnegie Mutual Fund</a></li>
											<li><a href="">Our Leadership</a></li>
											<li><a href="">Why Invest with us?</a></li>
											<li><a href="">Our Success Story</a></li>
										</ul></li>
									<li class="dropdown"><a class="dropdown-toggle"
										data-toggle="dropdown" href="#">HELP <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="">Contact us</a></li>
											<li><a href="">Online Chat Assistance</a></li>
										</ul></li>
								</ul>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>

		<div class="text-center">
			<h3>&nbsp</h3>
			<h3>Hello ${user.getFirstName()} ${user.getLastName()}</h3>
			<h3>Your credentials have been used to log-in from another
				browser, or you did not log-out successfully last time.</h3>
			<h3>To Log out of all sessions, retype your password:</h3>
			<h3>&nbsp</h3>

			<c:forEach var="error" items="${errors}">
				<h3 style="color: red">${error}</h3>
			</c:forEach>

			<form role="form-horizontal" method="POST" action="logUserOut.do">
				<div class="row">
					<div class="col-lg-4 col-lg-offset-4">
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="warning">
									<th style="display: block; margin: auto;">User Name:
										${user.getUserName()}</th>
								</tr>
								<tr>
									<th><input name="passwd" type="password" required
										placeholder="Enter Password" class="form-control"></th>
								</tr>
								<tr>
									<th><button type="submit" name="action" value="create"
											class="btn btn-success" style="display: block; margin: auto;">Submit
											& Logout</button></th>
								</tr>

							</thead>
						</table>
					</div>
				</div>
			</form>

		</div>
		<jsp:include page="template-bottom.jsp" />