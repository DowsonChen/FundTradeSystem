<jsp:include page="template-top-employee.jsp" />
<h1>&nbsp</h1>
<h3>&nbspCreate Fund :</h3>
<br>
<form class="form-horizontal" role="form" action="createFund.do"
	method="POST">
	<div class="form-group">
		<label class="control-label col-sm-2">Fund Name :</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="name" name="name"
				placeholder="Put Fund Name here" value="${form.name}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2">Fund Symbol :</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="symbol" name="symbol"
				placeholder="Put Symbol here" value="${form.symbol}">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" id="type" name="type" value="create account"
				class="btn btn-success pull-left">Create Fund</button>
		</div>
	</div>
	<jsp:include page="error.jsp" />
</form>
<jsp:include page="template-bottom.jsp" />