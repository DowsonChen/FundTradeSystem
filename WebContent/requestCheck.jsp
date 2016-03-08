<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top-customer.jsp" />
<h1>&nbsp</h1>
<h1>&nbsp</h1>
<h3>&nbsp&nbspRequest Check:</h3>
<br>
<h4 class="col-sm-10 col-sm-offset-2" style="color: red;">Note: You
	can request no more than $1,000,000</h4>
<form class="form-horizontal" role="form" action="requestCheck.do"
	method="POST">
	<div class="form-group">
		<label class="control-label col-sm-2">Available Cash :</label>
		<div class="col-sm-4">
			<label class="control-label col-sm-4">$${avai_cash}</label>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="requestAmount">Amount:</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="requestAmount"
				name="requestAmount" placeholder="Dollar in two decimal places"
				value="${form.requestAmount}">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" id="action" name="action"
				value="Withdraw Money" class="btn btn-success pull-left">Withdraw
				Money</button>
		</div>
	</div>
	<jsp:include page="error.jsp" />
</form>
<jsp:include page="template-bottom.jsp" />

