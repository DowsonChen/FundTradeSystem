<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top-employee.jsp" />
<h1>&nbsp</h1>
<h3>&nbsp&nbspDeposit Check: </h3>
<h4 class="col-sm-10 col-sm-offset-2" style="color:red;">Note: Maximum deposit amount is $1,000,000</h4>
<form class="form-horizontal" role="form" action="depositCheck.do"
	method="POST">
	<div class="form-group">
		<label class="control-label col-sm-2" for="userName">User
			Name:</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="userName" name="userName"
				placeholder="User Name" value="${form.userName}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="depositAmount">Amount:</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="depositAmount"
				name="depositAmount" placeholder="Dollar in two decimal places"
				value="${form.depositAmount}">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" id="action" name="action" value="Deposit Money"
				class="btn btn-success pull-left">Deposit Money</button>
		</div>
	</div>
	<jsp:include page="error.jsp" />
</form>
<jsp:include page="template-bottom.jsp" />