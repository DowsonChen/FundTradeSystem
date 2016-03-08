<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!doctype html>
<html lang="en">
<head>

<title>Employee</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-2.2.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Custom CSS -->
<link rel="stylesheet" href="css/style.css">

</head>
<body id="myPage" data-spy="scroll" data-target=".navbar">
	<div id="wrapper">
		<div id="header">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<nav class="navbar navbar-inverse navbar-fixed-top">
							<div class="container-fluid">
								<div class="navbar-header">
									<a href="login.do" class="pull-left"><img
										src="images/logo.png" style="max-width: 100px"></a>
								</div>
								<ul class="nav navbar-nav">
									<li class="dropdown"><a class="dropdown-toggle"
										data-toggle="dropdown" href="#">CUSTOMER ACCOUNT <span
											class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="createCustomer.do">CREATE CUSTOMER
													ACCOUNT</a></li>
											<li><a href="reset.do">RESET CUSTOMER
													PASSWORD</a></li>
											<li><a href="viewCustomer.do">VIEW CUSTOMER
													ACCOUNT</a></li>
											<li><a href="viewCustomerTransaction.do">VIEW CUSTOMER TRANSACTION HISTORY
													</a></li>
										</ul></li>
									<li class="dropdown"><a class="dropdown-toggle"
										data-toggle="dropdown" href="#">EMPLOYEE OPERATION <span
											class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="createEmployee.do">CREATE EMPLOYEE ACCOUNT</a></li>
											<li><a href="depositCheck.do">DEPOSIT CHECK</a></li>
											<li><a href="createFund.do">CREATE FUND</a></li>
											<li><a href="transitionDay.do">TRANSITION DAY</a></li>
											<li><a href="changePassword.do">CHANGE EMPLOYEE PASSWORD</a></li>
										</ul>
									</li>
									<li><a href="logout.do" class="pull-right">LOG OUT</a></li>
								</ul>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>