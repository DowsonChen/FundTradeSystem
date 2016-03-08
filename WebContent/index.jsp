<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<title>Welcome to CFS</title>
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
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="login.do" class="pull-left"><img src="images/logo.png"
					style="max-width: 100px"></a>
			</div>

			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#about">ABOUT COMPANY</a></li>
					<li><a href="#value">OUR VALUES</a></li>
					<li><a href="#contact">CONTACT US</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<br></br>
	<br></br>
	<div class="text-center myclass">

		<h1>Carnegie Financial Services</h1>

		<p>We specialize in mutual fund investments.</p>

		<form class="form-horizontal" role="form" method="POST"
			action="login.do">

			<div class="form-group">
				<label class="control-label col-sm-4" for="email"></label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="email"
						placeholder="Enter Username" name="userName">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-4" for="pwd"></label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="pwd"
						placeholder="Enter password" name="password">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-4">
					<button type="submit" class="btn btn-primary" name="type"
						value="customer">Customer Login</button>
					<button type="submit" class="btn btn-success" name="type"
						value="employee">Employee Login</button>
				</div>
			</div>
			<br></br>
			<c:if test="${!(empty errors)}">
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4 alert alert-warning">
						<h4>Warning!</h4>
						<c:forEach var="error" items="${errors}">
				${error}
				<br>
						</c:forEach>
					</div>
				</div>
			</c:if>
		</form>

	</div>
	<jsp:include page="template-bottom.jsp" />

</body>

</html>