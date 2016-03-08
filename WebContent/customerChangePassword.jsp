<jsp:include page="template-top-customer.jsp" />
<h1>&nbsp</h1>
<h3>&nbspChange Password :</h3>
<br>
<form class="form-horizontal" role="form" action="changePassword.do"
	method="POST">
	<div class="form-group">
		<label class="control-label col-sm-2" for="currentPassword">Current
			Password:</label>
		<div class="col-sm-4">
			<input type="password" class="form-control" id="currentPassword"
				name="currentPassword" placeholder="Current Password">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="newPassword">New
			Password:</label>
		<div class="col-sm-4">
			<input type="password" class="form-control" id="newPassword"
				name="newPassword" placeholder="New Password">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="confirmPassword">Confirm
			Password:</label>
		<div class="col-sm-4">
			<input type="password" class="form-control" id="confirmPassword"
				name="confirmPassword" placeholder="Confirm Password">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" id="type" name="type" value="change password"
				class="btn btn-success pull-left">Change Password</button>
		</div>
	</div>
	<jsp:include page="error.jsp" />
</form>


<jsp:include page="template-bottom.jsp" />