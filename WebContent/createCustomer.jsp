<jsp:include page="template-top-employee.jsp" />
<h1>&nbsp</h1>
<h3>&nbspCreate Customer :</h3>
<form class="form-horizontal" role="form" action="createCustomer.do"
	method="POST">
	<div class="form-group">
		<label class="control-label col-sm-2" for="firstName">First
			Name:</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="firstName"
				name="firstName" placeholder="First Name" value="${form.firstName}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="lastName">Last
			Name:</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="lastName" name="lastName"
				placeholder="Last Name" value="${form.lastName}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="userName">User
			Name:</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="userName" name="userName"
				placeholder="User Name" value="${form.userName}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="password">Password:</label>
		<div class="col-sm-4">
			<input type="password" class="form-control" id="password"
				name="password" placeholder="Password" value="${form.password}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="confirmPassword">Confirm
			Password:</label>
		<div class="col-sm-4">
			<input type="password" class="form-control" id="confirmPassword"
				name="confirmPassword" placeholder="Confirm Password"
				value="${form.confirmPassword}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="userName">Address
			1:</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="address1" name="address1"
				placeholder="Address 1" value="${form.address1}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="userName">Address
			2:</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="address2" name="address2"
				placeholder="Address 2" value="${form.address2}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="userName">City:</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="city" name="city"
				placeholder="City" value="${form.city}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="userName">State:</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="state" name="state"
				placeholder="State" value="${form.state}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="userName">Zip:</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="zip" name="zip"
				placeholder="zip" value="${form.zip}">
		</div>
	</div>
	<div class="form-group">
		<!--<label class="control-label col-sm-2" for="userName">Cash:</label>  -->
		<div class="col-sm-4">
			<input type="hidden" class="form-control" id="cash" name="cash"
				placeholder="cash" value="0">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" id="type" name="type" value="create account"
				class="btn btn-success pull-left">Create Account</button>
		</div>
	</div>
	<jsp:include page="error.jsp" />
</form>


<jsp:include page="template-bottom.jsp" />